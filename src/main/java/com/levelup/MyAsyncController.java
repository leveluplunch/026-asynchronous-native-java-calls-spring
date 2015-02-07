package com.levelup;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Stopwatch;

@RestController
public class MyAsyncController {

	private final static Logger LOGGER = Logger
			.getLogger(MyAsyncController.class);

	@Autowired
	private MySampleService mySampleService;

	@RequestMapping(value = "/", produces = { MediaType.TEXT_HTML_VALUE }, method = RequestMethod.GET)
	public String taskExecutor() throws InterruptedException,
			ExecutionException {

		Stopwatch stopwatch = Stopwatch.createStarted();

		Future<Long> asyncResult1 = mySampleService.callAsync(1);
		Future<Long> asyncResult2 = mySampleService.callAsync(2);
		Future<Long> asyncResult3 = mySampleService.callAsync(3);
		Future<Long> asyncResult4 = mySampleService.callAsync(4);

		LOGGER.info("result 1 took: " + asyncResult1.get());
		LOGGER.info("result 2 took: " + asyncResult2.get());
		LOGGER.info("result 3 took: " + asyncResult3.get());
		LOGGER.info("result 4 took: " + asyncResult4.get());

		stopwatch.elapsed(TimeUnit.MILLISECONDS);

		return "time it took to perform work " + stopwatch;
	}

}
