package com.travelex.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelex.constants.MessageConstants;
import com.travelex.exception.AuthException;
import com.travelex.response.BaseResponse;

public class BasicAuthFilter implements Filter {

	private Map<String, String> authCred;

	private static final Logger LOGGER = Logger.getLogger(BasicAuthFilter.class);

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String authName = request.getHeader("key");
		String authPwd = request.getHeader("password");
		try {
			if (StringUtils.isEmpty(authName) || StringUtils.isEmpty(authPwd)) {
				LOGGER.info("key or password Missing / Empty ");
				throw new AuthException(MessageConstants.UNAUTH_USER);
			}
			String pwd = authCred.get(authName);
			if (null == pwd) {
				LOGGER.info(" Key Not Exists  ");
				throw new AuthException(MessageConstants.UNAUTH_USER);
			}
			if (!authCred.get(authName).equals(authPwd)) {
				LOGGER.info(" Password Mismatch ");
				throw new AuthException(MessageConstants.UNAUTH_USER);
			}
		} catch (AuthException e) {
			BaseResponse errorResponse = new BaseResponse(HttpStatus.FORBIDDEN, e.getMessage());
			byte[] responseToSend = new ObjectMapper().writeValueAsBytes(errorResponse);
			response.setHeader("Content-Type", "application/json");
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.getOutputStream().write(responseToSend);
			return;
		}

		chain.doFilter(req, res);
	}

	/**
	 * This method must be used for additional configuration required. This is like
	 * contructor of
	 */
	public void init(FilterConfig filterConfig) {
		// TODO Add impl
		authCred = new HashMap<>();
		authCred.put("Travelex", "Travelex");
	}

	public void destroy() {
		// TODO Add impl
	}
}
