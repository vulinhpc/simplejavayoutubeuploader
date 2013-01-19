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

import org.bushe.swing.event.EventBus;
import org.chaosfisch.youtubeuploader.I18nHelper;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.Model;
import org.javalite.common.Convert;

public class Account extends Model implements ModelEvents {
	public enum Type {
		YOUTUBE("enum.youtube"), FACEBOOK("enum.facebook"), TWITTER("enum.twitter"), GOOGLEPLUS("enum.googleplus");

		private final String	name;

		Type(final String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return I18nHelper.message(name);
		}
	}

	@Override
	public String toString() {
		if (!isFrozen()) {
			return getString("name");
		}
		return super.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object object) {

		if (object == null || !(object instanceof Account)) {
			return false;
		} else if (((Account) object).getUnfrozen().equals(getUnfrozen())) {
			return true;
		}
		return false;
	}

	public Long getUnfrozen() {
		return Convert.toLong(getAttributes().get("id"));
	}

	/*
	 * (non-Javadoc)
	 * @see org.javalite.activejdbc.CallbackSupport#beforeSave()
	 */
	@Override
	protected void beforeSave() {
		super.beforeSave();
		EventBus.publish(MODEL_PRE_SAVED, this);
	}

	/*
	 * (non-Javadoc)
	 * @see org.javalite.activejdbc.CallbackSupport#afterSave()
	 */
	@Override
	protected void afterSave() {
		super.afterSave();
		Base.commitTransaction();
		EventBus.publish(MODEL_POST_SAVED, this);
	}

	/*
	 * (non-Javadoc)
	 * @see org.javalite.activejdbc.CallbackSupport#beforeDelete()
	 */
	@Override
	protected void beforeDelete() {
		super.beforeDelete();
		EventBus.publish(MODEL_PRE_REMOVED, this);
	}

	/*
	 * (non-Javadoc)
	 * @see org.javalite.activejdbc.CallbackSupport#afterDelete()
	 */
	@Override
	protected void afterDelete() {
		super.afterDelete();
		Base.commitTransaction();
		EventBus.publish(MODEL_POST_REMOVED, this);
	}
}