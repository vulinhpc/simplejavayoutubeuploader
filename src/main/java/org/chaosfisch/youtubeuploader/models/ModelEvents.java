/*******************************************************************************
 * Copyright (c) 2013 Dennis Fischer.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0+
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors: Dennis Fischer
 ******************************************************************************/
package org.chaosfisch.youtubeuploader.models;

public interface ModelEvents {
	/**
	 * Event: After Model-object was saved
	 */
	public final static String	MODEL_POST_SAVED	= "modelPostSaved";

	/**
	 * Event: After Model-object was removed
	 */
	public final static String	MODEL_POST_REMOVED	= "modelPostRemoved";

	/**
	 * Event: Before Model-object is added
	 */
	public final static String	MODEL_PRE_SAVED		= "modelPreSaved";

	/**
	 * Event: Before Model-object is removed
	 */
	public final static String	MODEL_PRE_REMOVED	= "modelPreRemoved";

}