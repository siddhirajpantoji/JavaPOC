package com.travelex.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelex.constants.RESTEndPointMapper;
import com.travelex.repository.CardRepository;
import com.travelex.repository.ConsumerRepository;
import com.travelex.request.CardRequest;
import com.travelex.request.CardRequestSingle;
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
	public Object getCardDetails(@RequestParam(value = "userId") Long userId) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = TravelexUtils.getConnection();
			String query = "Select * from getcarddetails(?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(userId.toString()));
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String response = rs.getString(1);
				return response;
			}

		} finally {
			rs.close();
			preparedStatement.close();
			connection.close();
		}
		return null;
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
