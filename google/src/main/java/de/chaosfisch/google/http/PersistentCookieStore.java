/*
 * Copyright (c) 2013 Dennis Fischer.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0+
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors: Dennis Fischer
 */

package de.chaosfisch.google.http;

import java.io.Serializable;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class PersistentCookieStore implements CookieStore {
	private final CookieStore store;

	public PersistentCookieStore() {
		// get the default in memory cookie store
		store = new CookieManager().getCookieStore();
	}

	public void setSerializeableCookies(final List<SerializableCookie> cookies) {
		for (final SerializableCookie cookie : cookies) {
			add(cookie.getURI(), cookie.getCookie());
		}
	}

	public List<SerializableCookie> getSerializeableCookies() {
		final List<SerializableCookie> cookies = new ArrayList<>(store.getCookies().size());

		for (final HttpCookie cookie : store.getCookies()) {
			final SerializableCookie serializableCookie = new SerializableCookie(cookie);
			cookies.add(serializableCookie);
		}
		return cookies;
	}

	@Override
	public void add(final URI uri, final HttpCookie cookie) {
		store.add(uri, cookie);
	}

	@Override
	public List<HttpCookie> get(final URI uri) {
		return store.get(uri);
	}

	@Override
	public List<HttpCookie> getCookies() {
		return store.getCookies();
	}

	@Override
	public List<URI> getURIs() {
		return store.getURIs();
	}

	@Override
	public boolean remove(final URI uri, final HttpCookie cookie) {
		return store.remove(uri, cookie);
	}

	@Override
	public boolean removeAll() {
		return store.removeAll();
	}

	public static class SerializableCookie implements Serializable {
		private static final long serialVersionUID = -2567907871765156956L;

		private final String  name;
		private final String  value;
		private final String  comment;
		private final String  commentUrl;
		private final String  domain;
		private final boolean discard;
		private final String  path;
		private final String  portList;
		private final long    maxAge;
		private final boolean secure;
		private final int     version;

		public SerializableCookie(final HttpCookie cookie) {
			name = cookie.getName();
			value = cookie.getValue();
			comment = cookie.getComment();
			commentUrl = cookie.getCommentURL();
			domain = cookie.getDomain();
			discard = cookie.getDiscard();
			maxAge = cookie.getMaxAge();
			path = cookie.getPath();
			portList = cookie.getPortlist();
			secure = cookie.getSecure();
			version = cookie.getVersion();
		}

		public URI getURI() {
			return URI.create(domain);
		}

		public HttpCookie getCookie() {
			final HttpCookie cookie = new HttpCookie(name, value);
			cookie.setComment(comment);
			cookie.setCommentURL(commentUrl);
			cookie.setDiscard(discard);
			cookie.setDomain(domain);
			cookie.setPath(path);
			cookie.setPortlist(portList);
			cookie.setMaxAge(maxAge);
			cookie.setSecure(secure);
			cookie.setVersion(version);
			return cookie;
		}
	}
}