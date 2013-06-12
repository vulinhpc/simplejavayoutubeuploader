/*
 * Copyright (c) 2013 Dennis Fischer.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0+
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors: Dennis Fischer
 */

package org.chaosfisch.google.auth;

import org.chaosfisch.exceptions.ErrorCode;

public enum AuthCode implements ErrorCode {
	AUTH_IO_ERROR(301), RESPONSE_NOT_200(302),;

	private final int number;

	AuthCode(final int number) {
		this.number = number;
	}

	@Override
	public int getNumber() {
		return number;
	}
}
