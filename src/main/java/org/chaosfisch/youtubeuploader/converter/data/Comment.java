/*
 * Copyright (c) 2013 Dennis Fischer.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0+
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors: Dennis Fischer
 */

package org.chaosfisch.youtubeuploader.converter.data;

import org.chaosfisch.youtubeuploader.converter.util.TextUtil;

public enum Comment {
	ALLOWED("commentlist.allowed"), MODERATED("commentlist.moderated"), DENIED("commentlist.denied"), FRIENDS_ONLY("commentlist.friendsonly");

	private final String i18n;

	Comment(final String i18n) {
		this.i18n = i18n;
	}

	@Override
	public String toString() {
		return TextUtil.getString(i18n);
	}
}
