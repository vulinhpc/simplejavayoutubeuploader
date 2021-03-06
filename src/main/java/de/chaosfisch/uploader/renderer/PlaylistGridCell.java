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

import de.chaosfisch.google.youtube.playlist.Playlist;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import jfxtras.labs.scene.control.grid.GridCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class PlaylistGridCell extends GridCell<Playlist> {
	private Image defaultThumbnail;
	private static final Logger                 logger = LoggerFactory.getLogger(PlaylistGridCell.class);
	private static final HashMap<String, Image> images = new HashMap<>(10);

	public PlaylistGridCell() {
		itemProperty().addListener(new ChangeListener<Playlist>() {

			@Override
			public void changed(final ObservableValue<? extends Playlist> observable, final Playlist oldValue, final Playlist playlist) {
				getChildren().clear();

				if (null == playlist) {
					setGraphic(null);
					return;
				}

				getStyleClass().add("image-grid-cell");

				final Tooltip tooltip = new Tooltip(playlist.getTitle());

				final Pane pane = new Pane();
				final ImageView imageView;
				if (null != playlist.getThumbnail()) {
					if (!images.containsKey(playlist.getThumbnail())) {
						final Image image = new Image(playlist.getThumbnail());
						images.put(playlist.getThumbnail(), image);
					}
					imageView = new ImageView(images.get(playlist.getThumbnail()));
					imageView.setPreserveRatio(true);
					final double width = 0 < imageView.getImage().getWidth() ? imageView.getImage().getWidth() : 0;
					final double height = 90 < imageView.getImage().getHeight() ?
										  imageView.getImage().getHeight() :
										  180;
					imageView.setViewport(new Rectangle2D(0, 45, width, height - 90));
				} else {
					imageView = new ImageView(getDefaultThumbnail());
				}

				imageView.fitHeightProperty().bind(heightProperty());
				imageView.fitWidthProperty().bind(widthProperty());

				pane.getChildren().add(imageView);
				setGraphic(pane);
				getGraphic().setOnMouseEntered(new EventHandler<MouseEvent>() {

					@Override
					public void handle(final MouseEvent event) {
						tooltip.show(getGraphic(), event.getScreenX(), event.getScreenY());
					}
				});
				getGraphic().setOnMouseExited(new EventHandler<MouseEvent>() {

					@Override
					public void handle(final MouseEvent event) {
						tooltip.hide();
					}
				});
			}

			private Image getDefaultThumbnail() {
				if (null == defaultThumbnail) {
					try (InputStream inputStream = getClass().getResourceAsStream("/de/chaosfisch/uploader/resources/images/thumbnail-missing.png")) {
						defaultThumbnail = new Image(inputStream);
					} catch (IOException e) {
						logger.warn("Default thumbnail load error", e);
					}
				}
				return defaultThumbnail;
			}
		});
	}
}
