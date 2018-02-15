package com.travelex.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelex.constants.RESTEndPointMapper;
import com.travelex.entities.Card;
import com.travelex.entities.Consumer;
import com.travelex.repository.CardRepository;
import com.travelex.repository.ConsumerRepository;
import com.travelex.request.CardRequest;
import com.travelex.request.CardRequestSingle;
import com.travelex.response.ConsumerResponse;
import com.travelex.utils.TravelexUtils;

@RestController
@RequestMapping(value = RESTEndPointMapper.CARD_DETAILS)
public class CardController {

	@Autowired
	ConsumerRepository consumerRepository;

	@Autowired
	CardRepository cardRepository;

	@Autowired
	EntityManager em;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ConsumerResponse> getCardDetails(@RequestParam(value = "userId") Long userId) {

		List<Card> cards = cardRepository.findByConsumer(userId);
		Consumer consumer = null;
		consumer = cards.get(0).getConsumer();
		return new ResponseEntity<ConsumerResponse>(new ConsumerResponse(consumer, cards), HttpStatus.OK);
	}

	/**
	 * Api for Creating
	 * 
	 * @param consumerRequest
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Object addCardDetails(@Valid @RequestBody CardRequestSingle cardRequest) {

		cardRequest.setCardNumber(TravelexUtils.encodeString(cardRequest.getCardNumber(), 12));
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("addcard");
		query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(5, Boolean.class, ParameterMode.IN);

		query.setParameter(1, Integer.parseInt(cardRequest.getUserId().toString()));
		query.setParameter(2, cardRequest.getCardNumber());
		query.setParameter(3, cardRequest.getCardType());
		query.setParameter(4, cardRequest.getExpiryDate());
		query.setParameter(5, cardRequest.isActive());
		return query.getSingleResult();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Object updateCardDetails(@Valid @RequestBody CardRequest cardRequest) {
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("updatecarddetails");
		query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(5, Boolean.class, ParameterMode.IN);

		query.setParameter(1, Integer.parseInt(cardRequest.getUserId().toString()));
		query.setParameter(2, Integer.parseInt(cardRequest.getCardId().toString()));
		query.setParameter(3, cardRequest.getCardType());
		query.setParameter(4, cardRequest.getExpiryDate());
		query.setParameter(5, cardRequest.isActive());
		return query.getSingleResult();
	}
}
