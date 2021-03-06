/*
 * Copyright (c) 2013 Dennis Fischer.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0+
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors: Dennis Fischer
 */

package de.chaosfisch.uploader;

import com.panayotis.jupidator.UpdatedApplication;
import com.panayotis.jupidator.Updater;
import com.panayotis.jupidator.UpdaterException;

import java.io.File;
import java.net.URISyntaxException;

class ApplicationUpdater implements UpdatedApplication {

	public ApplicationUpdater() {
		try {
			final Updater updater = new Updater(ApplicationData.BASEURL, getApplicationDirectory(), ApplicationData.DATA_DIR, ApplicationData.RELEASE, ApplicationData.VERSION, this);
			updater.actionDisplay();
		} catch (final URISyntaxException | UpdaterException ex) {
			ex.printStackTrace();
		}
	}

	private String getApplicationDirectory() throws URISyntaxException {

		final File file = new File(ApplicationUpdater.class.getProtectionDomain()
				.getCodeSource()
				.getLocation()
				.toURI());
		if (file.isDirectory()) {
			return file.getAbsolutePath();
		} else {
			return file.getParentFile().getAbsolutePath();
		}
	}

	@Override
	public boolean requestRestart() {
		return true;
	}

	@Override
	public void receiveMessage(final String message) {
		System.out.println(message);
	}
}