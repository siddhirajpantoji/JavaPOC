package com.travelex;


import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CucumberHooks {

	ApplicationContext context;
	private static final Logger LOGGER  = Logger.getLogger(CucumberHooks.class);

	@Before
	public void beforeScenario() {
		LOGGER.info("Starting setup ");
		// Start Setup here
//		String[] args = { "" };
//		context = SpringApplication.run(TravelexTestApplication.class, args);
	}

	@After
	public void afterScenario() {
		LOGGER.info("Exiting from Setup ");
		// End Setup here
//		SpringApplication.exit(context);
	}
}
