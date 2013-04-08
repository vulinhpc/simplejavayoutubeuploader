package org.chaosfisch.youtubeuploader.controller.renderer;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressBarBuilder;
import javafx.scene.layout.StackPane;

public class ProgressNodeRenderer extends StackPane {

	private final ProgressBar	progressBar		= ProgressBarBuilder.create()
													.scaleY(2)
													.prefWidth(500)
													.progress(0)
													.build();

	private final Label			progressInfo	= LabelBuilder.create()
													.build();
	private final Label			progressSpeed	= LabelBuilder.create()
													.build();
	private final Label			progressEta		= LabelBuilder.create()
													.build();

	public ProgressNodeRenderer() {
		super();

		progressInfo.textProperty()
			.bind(progressBar.progressProperty()
				.multiply(100)
				.asString("%.2f%%"));

		progressInfo.setAlignment(Pos.CENTER_LEFT);
		progressInfo.prefWidthProperty()
			.bind(progressBar.widthProperty()
				.subtract(6));

		progressEta.alignmentProperty()
			.set(Pos.CENTER_RIGHT);
		progressEta.prefWidthProperty()
			.bind(progressBar.widthProperty()
				.subtract(6));

		getChildren().addAll(progressBar, progressInfo, progressEta, progressSpeed);
	}

	public void setProgress(final double progress) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				progressBar.setProgress(progress);
			}
		});
	}

	public void setEta(final String eta) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				progressEta.setText(eta);
			}
		});
	}

	public void setSpeed(final String speed) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				progressSpeed.setText(speed);
			}
		});
	}
}