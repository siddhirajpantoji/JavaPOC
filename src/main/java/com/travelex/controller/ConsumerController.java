package com.travelex.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.travelex.constants.MessageConstants;
import com.travelex.constants.RESTEndPointMapper;
import com.travelex.response.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Consumer Controller", description = "Login and Create of Consumer  ")
public class ConsumerController {

	private static final Logger LOGGER = LogManager.getLogger(ConsumerController.class);


	@ApiOperation(value = "Checks Login for Each User  ", response = BaseResponse.class)
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = RESTEndPointMapper.PRINT_LOGGER)
	public ResponseEntity<BaseResponse> printLogs() {
		LOGGER.debug("This is Debug");
		LOGGER.info("This is Info ");
		LOGGER.warn("This is Warning ");
		LOGGER.error("This is Error ");
		LOGGER.fatal("This is Fatal ");
		return new ResponseEntity<BaseResponse>(new BaseResponse(HttpStatus.OK, MessageConstants.EVERYTHING_LOOKS_GOOD), HttpStatus.OK);
	}

}
