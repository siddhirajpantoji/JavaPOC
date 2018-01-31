package com.travelex.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
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

	private static final Logger LOGGER = Logger.getLogger(ConsumerController.class);

	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Autowired
	private CardRepository cardRepository;

	// API to Create Consumer
	@ApiOperation(value = "Registers a new user  ", response = BaseResponse.class)
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = RESTEndPointMapper.REGISTRATION)
	public ResponseEntity<BaseResponse> createConsumer(@RequestBody ConsumerRequest consumerRequest) {
		LOGGER.info("Entered into createConsumer");
		if (consumerRepository.countByEmailId(consumerRequest.getEmail()) > 0) {
			LOGGER.info("Existing User By Email ID ");
			return new ResponseEntity<BaseResponse>(
					new BaseResponse(HttpStatus.BAD_REQUEST, MessageConstants.DUPLICATE_ENTRY), HttpStatus.BAD_REQUEST);
		}
		Consumer consumer = new Consumer();
		BeanUtils.copyProperties(consumerRequest, consumer);
		consumer.setPassword(TravelexUtils.encodeString(consumer.getPassword(),consumer.getPassword().length()));
		consumer.setUserId(null);
		consumer = consumerRepository.save(consumer);
		LOGGER.info("Created Consumer Successfully ");
		return new ResponseEntity<BaseResponse>(new BaseResponse(HttpStatus.CREATED, MessageConstants.RECORD_CREATED),
				HttpStatus.CREATED);
	}
	
	
	@ApiOperation(value = "Checks Login for Each User  ", response = ConsumerResponse.class)
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = RESTEndPointMapper.LOGIN)
	public ResponseEntity<ConsumerResponse> login(@Valid @RequestBody LoginRequest consumerRequest) {
		if (consumerRepository.countByEmailId(consumerRequest.getEmailId()) <= 0) {
			LOGGER.info("Not Existing User By Email ID ");
			return new ResponseEntity<ConsumerResponse>(
					new ConsumerResponse(HttpStatus.BAD_REQUEST, MessageConstants.USER_NOT_EXISTS), HttpStatus.BAD_REQUEST);
		}
		consumerRequest.setPassword(TravelexUtils.encodeString(consumerRequest.getPassword(), consumerRequest.getPassword().length()));
		List<Consumer> consumers = consumerRepository.findByEmailAndPassword(consumerRequest.getEmailId(),consumerRequest.getPassword());
		if(CollectionUtils.isEmpty(consumers))
		{
			return new ResponseEntity<ConsumerResponse>(
					new ConsumerResponse(HttpStatus.BAD_REQUEST, MessageConstants.INVALID_LOGIN), HttpStatus.BAD_REQUEST);
		}
		List<Card> cards = cardRepository.findByConsumer(consumers.get(0));
		cards = TravelexUtils.getDecodedCards(cards);
		
		return new ResponseEntity<ConsumerResponse>(new ConsumerResponse(consumers.get(0), cards),HttpStatus.OK);
	}
	
}
