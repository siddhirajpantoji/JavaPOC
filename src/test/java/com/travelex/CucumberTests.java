package com.travelex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.travelex.response.Greeting;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

//@ContextConfiguration(classes=TestrestApplication.class)
public class CucumberTests {

	ResponseEntity<Greeting> response = null;

	@Autowired
	protected TestRestTemplate restTemplate;

//	@LocalServerPort
//	int port;

	// private static final String BASE_URL = "http://localhost:" + APPLICATION_PORT + "/spring-rest";
	private void makeAPICall(String name) {
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Origin", "Test");
//		restTemplate = new TestRestTemplate();
//		//response = restTemplate.getForEntity("/api/greeting?name=World11234", Greeting.class, headers);
//		 RestAssured.given().when().get("http://localhost:8080/api/greeting").then().statusCode(200);
	}

	
	
	@When("^User Registers for First Time$")
	public void user_Registers_for_First_Time() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^Logs in with the same Email Id$")
	public void logs_in_with_the_same_Email_Id() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
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
