package com.mjk.camunda.start.client;

import java.util.logging.Logger;
import org.camunda.bpm.client.ExternalTaskClient;

public class RomanInvaderWorker {

	private final static Logger LOGGER = Logger.getLogger(RomanInvaderWorker.class.getName());

	public static void main(String[] args) {
		ExternalTaskClient client = ExternalTaskClient.create().baseUrl("http://localhost:8080/engine-rest")
				.asyncResponseTimeout(10000) // long polling timeout
				.build();

		// subscribe to an external task topic as specified in the process
		client.subscribe("InvadeGaul").lockDuration(1000).handler((externalTask, externalTaskService) -> {
			// Put your business logic here

			LOGGER.info("Invading Gaul....");
			LOGGER.info("VICTORY!!!");

			// Complete the task
			externalTaskService.complete(externalTask);
		}).open();

		client.subscribe("InvadePersia").lockDuration(1000).handler((externalTask, externalTaskService) -> {
			// Put your business logic here


			LOGGER.info("Invading Persia....");
			LOGGER.info("VICTORY!!!");

			// Complete the task
			externalTaskService.complete(externalTask);
		}).open();
	}
}
