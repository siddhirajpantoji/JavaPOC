package com.travelex.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
/**
 * Filter for CORS 
 * This was introduced to recieved cross domain requests where origin has to be present 
 *  
 * @author pantojis
 *
 */
@Component
public class CORSFilter implements Filter {
	/**
	 * Default method of filter 
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
	
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		// Seting type of requests allowed by the application 
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT,PATCH");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");

		chain.doFilter(req, res);
	}

	/**
	 * This method must be used for additional configuration required.
	 * This is like contructor of 
	 */
	public void init(FilterConfig filterConfig) {
		//TODO Add impl
	}

	public void destroy() {
		//TODO Add impl
	}
}
