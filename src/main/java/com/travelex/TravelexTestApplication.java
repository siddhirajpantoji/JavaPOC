package com.travelex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.travelex.filter.BasicAuthFilter;

//@ComponentScan(basePackages= {"com.example.testrest.testrest.config","com.example.testrest.testrest.entity"})
@ComponentScan(basePackageClasses = TravelexTestApplication.class)
@SpringBootApplication
@EnableJpaRepositories( basePackages="com.travelex.repository")
@EntityScan(basePackages="com.travelex.entities")
@EnableCaching
public class TravelexTestApplication {

	public static void main(String[] args) {
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