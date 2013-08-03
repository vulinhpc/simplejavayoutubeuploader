/*
 * Copyright (c) 2013 Dennis Fischer.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0+
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors: Dennis Fischer
 */

package de.chaosfisch.google.youtube.upload.resume;

import de.chaosfisch.google.youtube.upload.Upload;

public interface IResumeableManager {
	String parseVideoId(String atomData);

	boolean canContinue();

	ResumeInfo fetchResumeInfo(Upload upload) throws ResumeIOException, ResumeInvalidResponseException;

	void setRetries(int i);

	int getRetries();

	void delay();

}
