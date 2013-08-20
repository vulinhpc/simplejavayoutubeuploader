/*
 * Copyright (c) 2013 Dennis Fischer.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0+
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors: Dennis Fischer
 */

package de.chaosfisch.uploader.persistence.dao;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import de.chaosfisch.google.account.Account;
import de.chaosfisch.google.youtube.playlist.Playlist;
import de.chaosfisch.google.youtube.upload.Upload;
import de.chaosfisch.uploader.template.Template;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.*;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.regex.Pattern;

class PersistenceService implements IPersistenceService {
	private static final Pattern STORAGE_PATTERN = Pattern.compile("data-[0-9]+.data");
	private static final int     ITERATIONS      = 42;

	private final IAccountDao  accountDao;
	private final IPlaylistDao playlistDao;
	private final ITemplateDao templateDao;
	private final IUploadDao   uploadDao;
	private final String       storage;
	private Data data = new Data();
	private String masterPassword;

	//Arbitrarily selected 8-byte salt sequence:
	private static final byte[] salt = {(byte) 0x43, (byte) 0x76, (byte) 0x95, (byte) 0xc7, (byte) 0x5b, (byte) 0xd7,
										(byte) 0x45, (byte) 0x17};

	@Inject
	public PersistenceService(final IAccountDao accountDao, final IPlaylistDao playlistDao, final ITemplateDao templateDao, final IUploadDao uploadDao, @Named(PERSISTENCE_FOLDER) final String storage) {
		this.accountDao = accountDao;
		this.playlistDao = playlistDao;
		this.templateDao = templateDao;
		this.uploadDao = uploadDao;
		this.storage = storage;
	}

	@Override
	public void saveToStorage() {
		data.playlists = new ArrayList<>(playlistDao.getPlaylists());
		data.accounts = new ArrayList<>(accountDao.getAccounts());
		data.uploads = new ArrayList<>(uploadDao.getUploads());
		data.templates = new ArrayList<>(templateDao.getTemplates());
		data.version++;

		final File storageFile = new File(storage + String.format("/data-%07d.data", data.version));

		try (final ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(storageFile))) {
			if (null == masterPassword) {
				objectOutputStream.writeObject(data);
			} else {
				final Cipher cipher = makeCipher(masterPassword, false);
				objectOutputStream.writeObject(new SealedObject(data, cipher));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean loadFromStorage() {
		final File storageDir = new File(storage);
		final List<File> list = Arrays.asList(storageDir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(final File dir, final String name) {
				return STORAGE_PATTERN.matcher(name).matches(); // or something else
			}
		}));
		if (list.isEmpty()) {
			return false;
		}
		Collections.sort(list, new Comparator<File>() {
			@Override
			public int compare(final File o1, final File o2) {
				return o1.getAbsolutePath().compareToIgnoreCase(o2.getAbsolutePath());
			}
		});

		final File storageFile = list.get(list.size() - 1);
		for (final File file : list) {
			if (!file.equals(storageFile)) {
				file.delete();
			}
		}

		try (final ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(storageFile))) {
			if (null == masterPassword) {
				data = (Data) objectInputStream.readObject();
			} else {
				final Cipher cipher = makeCipher(masterPassword, true);
				data = (Data) ((SealedObject) objectInputStream.readObject()).getObject(cipher);
			}
			loadPlaylists(data);
			loadAccounts(data);
			loadTemplates(data);
			loadUploads(data);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private Cipher makeCipher(final String pass, final boolean decryptMode) throws GeneralSecurityException {

		//Use a KeyFactory to derive the corresponding key from the passphrase:
		final PBEKeySpec keySpec = new PBEKeySpec(pass.toCharArray());
		final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		final SecretKey key = keyFactory.generateSecret(keySpec);

		//Create parameters from the salt and an arbitrary number of iterations:
		final PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, ITERATIONS);

		//Set up the cipher:
		final Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");

		//Set the cipher mode to decryption or encryption:
		if (decryptMode) {
			cipher.init(Cipher.DECRYPT_MODE, key, pbeParamSpec);
		} else {
			cipher.init(Cipher.ENCRYPT_MODE, key, pbeParamSpec);
		}

		return cipher;
	}

	@Override
	public void setMasterPassword(final String masterPassword) {
		this.masterPassword = masterPassword;
	}

	private void loadUploads(final Data data) {
		final List<Upload> uploads = data.uploads;

		for (final Upload upload : uploads) {
			if (null != upload.getAccount()) {
				upload.setAccount(accountDao.getAccounts().get(accountDao.getAccounts().indexOf(upload.getAccount())));
			}
			for (final Playlist playlist : data.playlists) {
				if (upload.getPlaylists().contains(playlist)) {
					upload.getPlaylists().remove(playlist);
					upload.getPlaylists().add(playlist);
				}
			}
		}
		uploadDao.setUploads(uploads);
	}

	private void loadTemplates(final Data data) {
		final List<Template> templates = data.templates;
		for (final Template template : templates) {
			if (null != template.getAccount()) {
				template.setAccount(accountDao.getAccounts()
						.get(accountDao.getAccounts().indexOf(template.getAccount())));
			}
			for (final Playlist playlist : data.playlists) {
				if (template.getPlaylists().contains(playlist)) {
					template.getPlaylists().remove(playlist);
					template.getPlaylists().add(playlist);
				}
			}
		}
		templateDao.setTemplates(templates);
	}

	private void loadAccounts(final Data data) {
		final List<Account> accounts = data.accounts;
		for (final Account account : accounts) {
			for (final Playlist playlist : playlistDao.getPlaylists()) {
				if (account.getPlaylists().contains(playlist)) {
					account.getPlaylists().remove(playlist);
					account.getPlaylists().add(playlist);
				}
			}
		}
		accountDao.setAccounts(accounts);
	}

	private void loadPlaylists(final Data data) {
		playlistDao.setPlaylists(data.playlists);
	}
}