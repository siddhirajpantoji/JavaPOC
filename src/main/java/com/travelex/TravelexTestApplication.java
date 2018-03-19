package com.travelex;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.travelex.filter.BasicAuthFilter;
import com.travelex.utils.TestEnum;

//@ComponentScan(basePackages= {"com.example.testrest.testrest.config","com.example.testrest.testrest.entity"})
@ComponentScan(basePackageClasses = TravelexTestApplication.class)
@SpringBootApplication
@EnableCaching
public class TravelexTestApplication {

	public static void main(String[] args) {
		TestEnum s= TestEnum.PROP_NAME;
		for(TestEnum a:s.values())
		{
			List<String> b=a.getB();
			
			System.out.println(b);
		}
		SpringApplication.run(TravelexTestApplication.class, args);
		
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public FilterRegistrationBean someFilterRegistration() {

	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    BasicAuthFilter authFilter =new BasicAuthFilter();
	    registration.setFilter(authFilter);
	    registration.addUrlPatterns("/cardDetails");
	    registration.addInitParameter("paramName", "paramValue");
	    registration.setName("authFilter");
	    registration.setOrder(1);
	    return registration;
	} 
}