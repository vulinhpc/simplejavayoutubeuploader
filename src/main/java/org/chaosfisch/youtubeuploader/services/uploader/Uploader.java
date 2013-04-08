/*******************************************************************************
 * Copyright (c) 2013 Dennis Fischer.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0+
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors: Dennis Fischer
 ******************************************************************************/
package org.chaosfisch.youtubeuploader.services.uploader;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import org.chaosfisch.util.Computer;
import org.chaosfisch.util.EventBusUtil;
import org.chaosfisch.youtubeuploader.ApplicationData;
import org.chaosfisch.youtubeuploader.db.dao.UploadDao;
import org.chaosfisch.youtubeuploader.db.generated.tables.pojos.Upload;
import org.chaosfisch.youtubeuploader.models.events.ModelAddedEvent;
import org.chaosfisch.youtubeuploader.services.uploader.events.UploadAbortEvent;
import org.chaosfisch.youtubeuploader.services.uploader.events.UploadProgressEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

public class Uploader {
	public static final String			ABORT					= "uploadAbort";
	public static final String			PROGRESS				= "uploadProgress";

	public SimpleIntegerProperty		actionOnFinish			= new SimpleIntegerProperty(0);
	public SimpleBooleanProperty		inProgressProperty		= new SimpleBooleanProperty(false);
	public SimpleIntegerProperty		maxUploads				= new SimpleIntegerProperty(1);

	private volatile short				runningUploads			= 0;
	private final boolean				startTimeCheckerFlag	= true;

	private final ExecutorService		executorService			= Executors.newFixedThreadPool(5);
	private final Logger				logger					= LoggerFactory.getLogger(getClass());
	@Inject
	private Injector					injector;
	@Inject
	private EventBus					eventBus;
	@Inject
	private UploadDao					uploadDao;
	@Inject
	@Named(value = ApplicationData.SERVICE_EXECUTOR)
	private ListeningExecutorService	pool;

	public Uploader() {
		EventBusUtil.getInstance()
			.register(this);
		maxUploads.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(final ObservableValue<? extends Number> observable, final Number oldValue, final Number newValue) {
				sendUpload();
			}
		});
	}

	public void abort(final Upload upload) {
		eventBus.post(new UploadAbortEvent(upload));
	}

	private boolean hasFreeUploadSpace() {
		return runningUploads < maxUploads.get();
	}

	public boolean isRunning() {
		return inProgressProperty.get() && runningUploads != 0;
	}

	public void exit() {
		executorService.shutdownNow();
		pool.shutdownNow();
	}

	public int getRunningUploads() {
		return runningUploads;
	}

	public void start() {
		inProgressProperty.set(true);
		pool.submit(new Callable<Boolean>() {

			@Override
			public Boolean call() {
				for (int i = 0; i < maxUploads.get() && hasFreeUploadSpace(); i++) {
					sendUpload();
					try {
						Thread.sleep(10000);
					} catch (final InterruptedException e) {
						Thread.currentThread()
							.interrupt();
					}
				}
				return true;
			}
		});
	}

	private synchronized void sendUpload() {
		if (!executorService.isShutdown() && inProgressProperty.get() && hasFreeUploadSpace()) {
			final Upload polled = uploadDao.fetchNextUpload();
			if (polled != null) {
				if (uploadDao.fetchOneAccountByUpload(polled) == null) {
					polled.setLocked(true);
					uploadDao.update(polled);
				} else {
					polled.setInprogress(true);
					uploadDao.update(polled);
					final UploadWorker uploadWorker = injector.getInstance(UploadWorker.class);
					uploadWorker.run(polled);
					executorService.submit(uploadWorker);
					runningUploads++;
				}
			}
		}
	}

	public void stop() {
		inProgressProperty.set(false);
	}

	public void stopStarttimeChecker() {
		Thread.currentThread()
			.interrupt();
	}

	private void uploadFinished(final Upload queue) {
		runningUploads--;

		final long leftUploads = uploadDao.countLeftUploads();
		logger.info("Upload finished: {}; {}", queue.getTitle(), queue.getVideoid());
		logger.info("Running uploads: {}", runningUploads);
		logger.info("Left uploads: {}", leftUploads);

		if (queue.getPauseonfinish()) {
			inProgressProperty.set(false);
		} else {
			sendUpload();
		}

		if (!inProgressProperty.get() || leftUploads == 0 && runningUploads <= 0) {
			inProgressProperty.set(false);
			logger.info("All uploads finished");
			switch (actionOnFinish.get()) {
				case 0:
					return;
				case 1:
					logger.info("CLOSING APPLICATION");
					Platform.exit();
				break;
				case 2:
					logger.info("SHUTDOWN COMPUTER");
					Computer.shutdownComputer();
				break;
				case 3:
					logger.info("HIBERNATE COMPUTER");
					Computer.hibernateComputer();
				break;
			}
		}
	}

	public void runStarttimeChecker() {
		pool.submit(new Callable<Boolean>() {

			@Override
			public Boolean call() {
				while (!Thread.interrupted() && startTimeCheckerFlag) {
					if (uploadDao.countAvailableStartingUploads() > 0) {
						start();
					}
					try {
						Thread.sleep(60000);
					} catch (final InterruptedException e) {
						Thread.currentThread()
							.interrupt();
					}
				}
				return true;
			}
		});
	}

	@Subscribe
	public void onUploadJobDoneAndFailed(final UploadProgressEvent uploadProgressEvent) {
		if (uploadProgressEvent.done || uploadProgressEvent.failed) {
			logger.info("Status: {}", uploadProgressEvent.status);
			uploadFinished(uploadProgressEvent.getUpload());
		}
	}

	@Subscribe
	public void onUploadSaved(final ModelAddedEvent modelPostSavedEvent) {
		if (modelPostSavedEvent.getModel() instanceof Upload && !((Upload) modelPostSavedEvent.getModel()).getInprogress()) {
			sendUpload();
		}
	}
}