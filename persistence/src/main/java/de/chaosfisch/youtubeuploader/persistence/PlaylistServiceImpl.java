/*
 * Copyright (c) 2013 Dennis Fischer.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0+
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors: Dennis Fischer
 */

package de.chaosfisch.youtubeuploader.persistence;

import com.google.inject.Inject;
import de.chaosfisch.google.account.Account;
import de.chaosfisch.google.auth.IGoogleRequestSigner;
import de.chaosfisch.google.youtube.playlist.Playlist;
import de.chaosfisch.http.RequestBuilderFactory;
import de.chaosfisch.serialization.IXmlSerializer;

import java.util.List;

public class PlaylistServiceImpl extends de.chaosfisch.google.youtube.playlist.PlaylistServiceImpl {

	@Inject
	public PlaylistServiceImpl(final IGoogleRequestSigner requestSigner, final RequestBuilderFactory requestBuilderFactory, final IXmlSerializer xmlSerializer) {
		super(requestSigner, requestBuilderFactory, xmlSerializer);
	}

	@Override
	public List<Playlist> getAll(final Account account) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public Playlist get(final int id) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void insert(final Playlist playlist) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void update(final Playlist playlist) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void delete(final Playlist playlist) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void cleanByAccount(final Account account) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public List<Playlist> findByHidden(final Account account, final boolean hidden) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public List<Playlist> findByPkey(final String playlistKey) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}
}