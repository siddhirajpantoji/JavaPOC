package com.travelex.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelex.constants.MessageConstants;
import com.travelex.constants.RESTEndPointMapper;
import com.travelex.entities.Card;
import com.travelex.entities.Consumer;
import com.travelex.repository.CardRepository;
import com.travelex.repository.ConsumerRepository;
import com.travelex.request.CardRequest;
import com.travelex.response.BaseResponse;
import com.travelex.response.ConsumerResponse;
import com.travelex.utils.TravelexUtils;

@RestController
@RequestMapping(value=RESTEndPointMapper.CARD_DETAILS)
public class CardController {

	@Autowired
	ConsumerRepository consumerRepository;
	
	@Autowired
	CardRepository cardRepository;
	
	@RequestMapping(method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<ConsumerResponse> getCardDetails( @RequestParam(value="userId") Long userId)
	{
		if( null == userId)
		{
			return new ResponseEntity<ConsumerResponse>(new ConsumerResponse(HttpStatus.BAD_REQUEST, MessageConstants.INVALID_CONSUMER_ID), HttpStatus.BAD_REQUEST);
		}
		Consumer consumer = consumerRepository.findOne(userId);
		if( null == consumer)
		{
			return new ResponseEntity<ConsumerResponse>(new ConsumerResponse(HttpStatus.BAD_REQUEST, MessageConstants.INVALID_CONSUMER_ID), HttpStatus.BAD_REQUEST);
		}
		
		List<Card> cards = cardRepository.findByConsumer(consumer);
		cards= TravelexUtils.getDecodedCards(cards);
		
		return new ResponseEntity<ConsumerResponse>(new ConsumerResponse(consumer, cards),HttpStatus.OK);
	}
	
	/**
	 * Api for Creating 
	 * @param consumerRequest
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<BaseResponse> addCardDetails(@Valid @RequestBody CardRequest consumerRequest)
	{
		Consumer consumer = consumerRepository.findOne(consumerRequest.getConsumerId());
		if( null == consumer)
		{
			return new ResponseEntity<BaseResponse>(new BaseResponse(HttpStatus.BAD_REQUEST, MessageConstants.INVALID_CONSUMER_ID), HttpStatus.BAD_REQUEST);
		}
		
		// Create a new Card for Consumer
		if(!CollectionUtils.isEmpty(consumerRequest.getCards()))
		{
			List<Card> cards = new ArrayList<>();
			for(int counter =0;counter<consumerRequest.getCards().size();counter++)
			{
				consumerRequest.getCards().get(counter).setCardId(null);
				Card card = new Card();
				BeanUtils.copyProperties(consumerRequest.getCards().get(counter), card);
				card.setCardNumber(TravelexUtils.encodeString(card.getCardNumber(), card.getCardNumber().length()-4));
				card.setConsumer(consumer);
				cards.add(card);
			}
			
			cardRepository.save(cards);
		}
		
		return new ResponseEntity<BaseResponse>(new BaseResponse(HttpStatus.CREATED, MessageConstants
				.EVERYTHING_LOOKS_GOOD),HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT, produces="application/json")
	public ResponseEntity<BaseResponse> updateCardDetails(@Valid @RequestBody CardRequest consumerRequest)
	{
		Consumer consumer = consumerRepository.findOne(consumerRequest.getConsumerId());
		if( null == consumer)
		{
			return new ResponseEntity<BaseResponse>(new BaseResponse(HttpStatus.BAD_REQUEST, MessageConstants.INVALID_CONSUMER_ID), HttpStatus.BAD_REQUEST);
		}
		
		// Create a new Card for Consumer
		if(!CollectionUtils.isEmpty(consumerRequest.getCards()))
		{
			for(int counter =0;counter<consumerRequest.getCards().size();counter++)
			{
				Card card  = cardRepository.findOne(consumerRequest.getCards().get(counter).getCardId());
				if( null == card )
				{
					return new ResponseEntity<BaseResponse>(new BaseResponse(HttpStatus.BAD_REQUEST, MessageConstants
							.INVALID_CARD_ID),HttpStatus.BAD_REQUEST);
					// To be decided for Validation 
				}
				if( card.getConsumer().getUserId().longValue() != consumer.getUserId().longValue())
				{
					return new ResponseEntity<BaseResponse>(new BaseResponse(HttpStatus.BAD_REQUEST, MessageConstants
							.INVALID_CARD_ID),HttpStatus.BAD_REQUEST);
				}
				BeanUtils.copyProperties(consumerRequest.getCards().get(counter), card);
				card.setCardNumber(TravelexUtils.encodeString(card.getCardNumber(), card.getCardNumber().length()-4));
				cardRepository.save(card);
			}
		}
		else
		{
			//TODO 
		}
		
		return new ResponseEntity<BaseResponse>(new BaseResponse(HttpStatus.OK, MessageConstants
				.EVERYTHING_LOOKS_GOOD),HttpStatus.OK);
	}
}
