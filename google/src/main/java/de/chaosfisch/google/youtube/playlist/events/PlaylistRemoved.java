/*
 * Copyright (c) 2013 Dennis Fischer.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0+
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors: Dennis Fischer
 */

package de.chaosfisch.google.youtube.playlist.events;

import de.chaosfisch.google.youtube.playlist.Playlist;

public class PlaylistRemoved {
	private final Playlist playlist;

	public PlaylistRemoved(final Playlist playlist) {
		this.playlist = playlist;
	}

	public Playlist getPlaylist() {
		return playlist;
	}
}
