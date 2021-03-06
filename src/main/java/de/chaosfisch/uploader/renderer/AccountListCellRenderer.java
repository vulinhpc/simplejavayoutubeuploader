/*
 * Copyright (c) 2013 Dennis Fischer.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0+
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors: Dennis Fischer
 */

package de.chaosfisch.uploader.renderer;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import de.chaosfisch.google.account.Account;
import de.chaosfisch.google.account.IAccountService;
import de.chaosfisch.google.youtube.playlist.IPlaylistService;
import de.chaosfisch.google.youtube.playlist.Playlist;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.PaneBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class AccountListCellRenderer implements Callback<ListView<Account>, ListCell<Account>> {

	@Inject
	private IPlaylistService playlistService;
	@Inject
	@Named("i18n-resources")
	private ResourceBundle   resources;
	@Inject
	private IAccountService  accountService;

	@Override
	public ListCell<Account> call(final ListView<Account> listCell) {
		return new AccountCell();
	}

	private final class AccountCell extends ListCell<Account> {
		@Override
		protected void updateItem(final Account item, final boolean empty) {
			super.updateItem(item, empty);
			if (null == item) {
				return;
			}

			//Load data from database and construct checkboxes
			final ArrayList<CheckBox> children = new ArrayList<>(item.getPlaylists().size());
			for (final Playlist playlist : item.getPlaylists()) {
				children.add(CheckBoxBuilder.create()
						.text(playlist.getTitle())
						.selected(playlist.isHidden())
						.styleClass("accountCellCheckbox")
						.onAction(new AccountCellCheckboxHandler(playlist))
						.build());
			}

			//Create our main view elements
			final TextField nameTextField = TextFieldBuilder.create().text(item.getName()).prefWidth(300).build();
			final Button removeAccountButton = ButtonBuilder.create()
					.text(resources.getString("button.remove"))
					.styleClass("accountCellRemoveButton")
					.onAction(new AccountCellRemoveButtonHandler(item))
					.build();
			final Label playlistLabel = LabelBuilder.create()
					.text(resources.getString("label.hiddenplaylists"))
					.styleClass("accountCellHiddenPlaylistsLabel")
					.build();
			final VBox playlistContainer = VBoxBuilder.create()
					.children(children)
					.styleClass("accountCellHiddenPlaylistsContainer")
					.build();
			final Pane container = PaneBuilder.create()
					.children(nameTextField, removeAccountButton, playlistLabel, playlistContainer)
					.styleClass("accountCellContainer")
					.build();

			nameTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(final ObservableValue<? extends Boolean> observableValue, final Boolean oldFocusState, final Boolean newFocusState) {
					if (null != newFocusState && !newFocusState && !item.getName().equals(nameTextField.getText())) {
						item.setName(nameTextField.getText());
						accountService.update(item);
					}
				}
			});

			//Position our elements
			removeAccountButton.layoutXProperty()
					.bind(nameTextField.layoutXProperty().add(nameTextField.widthProperty()).add(10));
			playlistLabel.layoutXProperty()
					.bind(removeAccountButton.layoutXProperty().add(removeAccountButton.widthProperty()).add(10));
			playlistContainer.layoutXProperty()
					.bind(playlistLabel.layoutXProperty().add(playlistLabel.widthProperty()).add(10));
			setGraphic(container);
		}

		private class AccountCellRemoveButtonHandler implements EventHandler<ActionEvent> {
			private final Account item;

			public AccountCellRemoveButtonHandler(final Account item) {
				this.item = item;
			}

			@Override
			public void handle(final ActionEvent event) {
				accountService.delete(item);
			}
		}

		private class AccountCellCheckboxHandler implements EventHandler<ActionEvent> {

			private final Playlist playlist;

			public AccountCellCheckboxHandler(final Playlist playlist) {
				this.playlist = playlist;
			}

			@Override
			public void handle(final ActionEvent event) {
				playlist.setHidden(!playlist.isHidden());
				playlistService.update(playlist);
			}
		}
	}
}