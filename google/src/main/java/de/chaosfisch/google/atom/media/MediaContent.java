/*
 * Copyright (c) 2013 Dennis Fischer.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0+
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors: Dennis Fischer
 */

package de.chaosfisch.google.atom.media;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("media:content")
class MediaContent {

	@XStreamAsAttribute
	public Integer duration;

	@XStreamAsAttribute
	@XStreamAlias("yt:format")
	public String format;

	@XStreamAsAttribute
	public Boolean isDefault;

	@XStreamAsAttribute
	@XStreamAlias("yt:name")
	public String name;

	@XStreamAsAttribute
	public String type;

	@XStreamAsAttribute
	public String url;
}
