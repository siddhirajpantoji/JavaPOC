package com.travelex;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources")
@ContextConfiguration(classes=TravelexTestApplication.class)
public class CucumberIntegrationTests {
	
}
