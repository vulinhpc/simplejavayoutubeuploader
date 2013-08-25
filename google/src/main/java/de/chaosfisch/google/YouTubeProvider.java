/*
 * Copyright (c) 2013 Dennis Fischer.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0+
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors: Dennis Fischer
 */

package de.chaosfisch.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.inject.Inject;
import com.google.inject.Provider;
import de.chaosfisch.google.account.Account;

import java.util.HashMap;
import java.util.Map;

public class YouTubeProvider implements Provider<YouTube> {
	private Account account;
	private final Map<Account, YouTube> services = new HashMap<>(1);
	private final HttpTransport httpTransport;
	private final JsonFactory   jsonFactory;

	@Inject
	public YouTubeProvider(final HttpTransport httpTransport, final JsonFactory jsonFactory) {
		this.httpTransport = httpTransport;
		this.jsonFactory = jsonFactory;
	}

	@Override
	public YouTube get() {
		if (!services.containsKey(account)) {
			final Credential credential = new GoogleCredential.Builder().setJsonFactory(jsonFactory)
					.setTransport(httpTransport)
					.setClientSecrets(GDATAConfig.CLIENT_ID, GDATAConfig.CLIENT_SECRET)
					.build();
			credential.setRefreshToken(account.getRefreshToken());
			services.put(account, new YouTube.Builder(httpTransport, jsonFactory, credential).setApplicationName("simple-java-youtube-uploader")
					.build());
		}
		return services.get(account);
	}

	public YouTubeProvider setAccount(final Account account) {
		this.account = account;
		return this;
	}
}