package com.levelup;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.google.common.base.Stopwatch;

@Component
public class MySampleService {

	private final static Logger LOGGER = Logger
			.getLogger(MySampleService.class);

	@Async
	public Future<Long> callAsync(int taskCall) throws InterruptedException {

		Stopwatch stopwatch = Stopwatch.createStarted();

		LOGGER.info("task " + taskCall + " starting");

		Thread.sleep(500);

		stopwatch.elapsed(TimeUnit.MILLISECONDS);

		LOGGER.info("task " + taskCall + "completed in " + stopwatch);

		return new AsyncResult<Long>(stopwatch.elapsed(TimeUnit.MILLISECONDS));
	}

}
