package com.travelex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.travelex.response.Greeting;

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

	@When("^the client calls /api/greeting with name=Sidd$")
	public void the_client_calls_api_greeting_with_name_Sidd() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		makeAPICall("Sidd");
		// throw new PendingException();
	}

	@Then("^the client receives status code of (\\d+)$")
	public void the_client_receives_status_code_of(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// throw new PendingException();
	}

	@Then("^the client receives server$")
	public void the_client_receives_server() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// throw new PendingException();
	}
}
