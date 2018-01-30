package com.travelex;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebMvcTest(value = GreetingController.class, secure = false)
public class TravelexTestApplicationTests {

	private static final Logger LOGGER = Logger.getLogger(TravelexTestApplicationTests.class);
	@Test
	public void contextLoads() {
		LOGGER.info("Inside Context loads ");
	}

}
