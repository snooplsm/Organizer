package us.wmwm.meetup.organizer.util;

import java.util.concurrent.Executors;

import java.util.concurrent.ScheduledExecutorService;

public class ThreadHelper {

	private static ScheduledExecutorService POOL = Executors
			.newScheduledThreadPool(4);

	public static ScheduledExecutorService getScheduler() {

		if (POOL.isShutdown() || POOL.isTerminated()) {

			POOL = Executors.newScheduledThreadPool(4);

		}

		return POOL;

	}

	public static void cleanUp() {

		if (POOL != null && !POOL.isShutdown()) {

			POOL.shutdownNow();

		}

	}

}