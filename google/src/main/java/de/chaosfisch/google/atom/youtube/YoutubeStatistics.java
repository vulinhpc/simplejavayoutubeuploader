/*
 * Copyright (c) 2013 Dennis Fischer.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0+
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors: Dennis Fischer
 */

package de.chaosfisch.google.atom.youtube;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("yt:statistics")
public class YoutubeStatistics {

	@XStreamAsAttribute
	public Integer favoriteCount;

	@XStreamAsAttribute
	public String lastWebAccess;

	@XStreamAsAttribute
	public Integer subscriberCount;

	@XStreamAsAttribute
	public Integer totalUploadViews;

	@XStreamAsAttribute
	public Integer videoWatchCount;

	@XStreamAsAttribute
	public Integer viewCount;
}
