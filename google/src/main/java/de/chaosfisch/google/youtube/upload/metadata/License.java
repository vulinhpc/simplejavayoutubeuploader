/*
 * Copyright (c) 2013 Dennis Fischer.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0+
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors: Dennis Fischer
 */

package de.chaosfisch.google.youtube.upload.metadata;

import de.chaosfisch.util.TextUtil;

public enum License {
	YOUTUBE("licenselist.youtube", "youtube"), CREATIVE_COMMONS("licenselist.cc", "cc");

	private final String i18n;
	private final String metaIdentifier;

	License(final String i18n, final String metaIdentifier) {
		this.i18n = i18n;
		this.metaIdentifier = metaIdentifier;
	}

	@Override
	public String toString() {
		return TextUtil.getString(i18n);
	}

	public String getMetaIdentifier() {
		return metaIdentifier;
	}
}
