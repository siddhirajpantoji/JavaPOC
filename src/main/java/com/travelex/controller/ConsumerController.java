package com.travelex.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.travelex.constants.MessageConstants;
import com.travelex.constants.RESTEndPointMapper;
import com.travelex.entities.Card;
import com.travelex.entities.Consumer;
import com.travelex.repository.CardRepository;
import com.travelex.repository.ConsumerRepository;
import com.travelex.request.ConsumerRequest;
import com.travelex.request.LoginRequest;
import com.travelex.response.BaseResponse;
import com.travelex.response.ConsumerResponse;
import com.travelex.utils.TravelexUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Consumer Controller", description = "Login and Create of Consumer  ")
public class ConsumerController {

	private static final Logger LOGGER = LogManager.getLogger(ConsumerController.class);

	@Autowired
	private ConsumerRepository consumerRepository;

	@Autowired
	private CardRepository cardRepository;

	// API to Create Consumer
	@ApiOperation(value = "Registers a new user  ", response = BaseResponse.class)
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = RESTEndPointMapper.REGISTRATION)
	public ResponseEntity<BaseResponse> createConsumer(@RequestBody ConsumerRequest consumerRequest) {
		LOGGER.info("Entered into createConsumer");
		consumerRequest.setPassword(
				TravelexUtils.encodeString(consumerRequest.getPassword(), consumerRequest.getPassword().length()));
		Consumer consumer = new Consumer();
		consumer.setEmail(consumerRequest.getEmail());
		consumer.setPassword(consumerRequest.getPassword());
		consumer.setFirstName(consumerRequest.getFirstName());
		consumer.setLastName(consumerRequest.getLastName());
		// BeanUtils.copyProperties(consumerRequest, consumer);
		consumerRepository.save(consumer);
		LOGGER.info("Created Consumer Successfully ");
		return new ResponseEntity<BaseResponse>(new BaseResponse(HttpStatus.CREATED, MessageConstants.RECORD_CREATED),
				HttpStatus.CREATED);
	}

	@ApiOperation(value = "Checks Login for Each User  ", response = ConsumerResponse.class)
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = RESTEndPointMapper.LOGIN)
	public ResponseEntity<ConsumerResponse> login(@Valid @RequestBody LoginRequest consumerRequest) {
		consumerRequest.setPassword(
				TravelexUtils.encodeString(consumerRequest.getPassword(), consumerRequest.getPassword().length()));
		List<Consumer> consumers = consumerRepository.findByEmailAndPassword(consumerRequest.getEmailId(),
				consumerRequest.getPassword());
		if (CollectionUtils.isEmpty(consumers)) {
			return new ResponseEntity<ConsumerResponse>(
					new ConsumerResponse(HttpStatus.BAD_REQUEST, MessageConstants.INVALID_LOGIN),
					HttpStatus.BAD_REQUEST);
		}
		List<Card> cards = cardRepository.findByConsumer(consumers.get(0).getUserId());

		return new ResponseEntity<ConsumerResponse>(new ConsumerResponse(consumers.get(0), cards), HttpStatus.OK);
	}

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
