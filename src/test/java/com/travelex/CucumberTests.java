package com.travelex;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.travelex.request.ConsumerRequest;
import com.travelex.request.LoginRequest;
import com.travelex.response.BaseResponse;
import com.travelex.response.ConsumerResponse;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

//@ContextConfiguration(classes=TestrestApplication.class)
public class CucumberTests {

	private static final Logger LOGGER = Logger.getLogger(CucumberTests.class);

	private ResponseEntity<BaseResponse> registrationResponse = null;
	private ResponseEntity<ConsumerResponse> loginResponse = null;
	private static final String BASE_PATH = "http://localhost:8082/";
	@Autowired
	protected TestRestTemplate restTemplate;


	@When("^User Registers for First Time$")
	public void user_Registers_for_First_Time() throws Throwable {
	    // TODO Call for Registration API 
		LOGGER.info("Into user_Registers_for_First_Time ");
		ConsumerRequest consumerRequest = new ConsumerRequest();
		consumerRequest.setEmail("siddhirajpantoji1@gmail.com");
		consumerRequest.setPassword("Sidd@123");
		consumerRequest.setFirstName("Siddhiraj ");
		consumerRequest.setLastName("Pantoji");
		RestTemplate restTemplate = new RestTemplate();		
		registrationResponse= restTemplate.postForEntity(BASE_PATH+"/registration", consumerRequest, BaseResponse.class);
		LOGGER.info("Exit user_Registers_for_First_Time ");
	}

	@Then("^Logs in with the same Email Id$")
	public void logs_in_with_the_same_Email_Id() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		LOGGER.info("Into logs_in_with_the_same_Email_Id ");
		LoginRequest loginRequest=  new LoginRequest();
		loginRequest.setEmailId("siddhirajpantoji1@gmail.com");
		loginRequest.setPassword("Sidd@123");
		RestTemplate restTemplate = new RestTemplate();		
		loginResponse= restTemplate.postForEntity(BASE_PATH+"/login", loginRequest, ConsumerResponse.class);
		LOGGER.info("Exit logs_in_with_the_same_Email_Id ");
	}

	@Then("^Gets Card Details with the user Id$")
	public void gets_Card_Details_with_the_user_Id() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^Adds Card Details with user Id$")
	public void adds_Card_Details_with_user_Id() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^updates card Details$")
	public void updates_card_Details() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}
}
