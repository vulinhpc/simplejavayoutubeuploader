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
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

@XStreamAlias("media:restriction")
@XStreamConverter(value = ToAttributedValueConverter.class, strings = {"restriction"})
public class MediaRestriction {

	@XStreamAlias("relationship")
	@XStreamAsAttribute
	public String relationship;
	public String restriction;

	@XStreamAlias("type")
	@XStreamAsAttribute
	public String type;
}
