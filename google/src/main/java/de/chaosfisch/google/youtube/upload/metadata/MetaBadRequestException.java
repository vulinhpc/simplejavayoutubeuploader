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

public class MetaBadRequestException extends Exception {
	private static final long serialVersionUID = -2086228006554387997L;
	private static final int  SC_BAD_REQUEST   = 400;
	private final String atomData;
	private final int    statusCode;

	public MetaBadRequestException(final Exception e) {
		super(e);
		atomData = "empty";
		statusCode = SC_BAD_REQUEST;
	}

	public MetaBadRequestException(final String atomData, final int statusCode) {
		super("Code: " + statusCode + "; Data: " + atomData);
		this.atomData = atomData;
		this.statusCode = statusCode;
	}

	public String getAtomData() {
		return atomData;
	}

	public int getStatusCode() {
		return statusCode;
	}
}
