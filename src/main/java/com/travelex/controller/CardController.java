package com.travelex.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
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

			Session session = em.unwrap(Session.class);
			SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
			connection = sfi.getJdbcServices().getBootstrapJdbcConnectionAccess().obtainConnection();
//			connection = TravelexUtils.getConnection();
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
	 * @throws SQLException 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Object addCardDetails(@Valid @RequestBody CardRequestSingle cardRequest) throws SQLException {
		cardRequest.setCardNumber(TravelexUtils.encodeString(cardRequest.getCardNumber(), 12));
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			Session session = em.unwrap(Session.class);
			SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
			connection = sfi.getJdbcServices().getBootstrapJdbcConnectionAccess().obtainConnection();
			String query = "Select * from addcard(?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(cardRequest.getUserId().toString()));
			preparedStatement.setString(2, cardRequest.getCardNumber());
			preparedStatement.setString(3, cardRequest.getCardType());
			preparedStatement.setString(4, cardRequest.getExpiryDate());
			preparedStatement.setBoolean(5, cardRequest.isActive());
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

	@RequestMapping(method = RequestMethod.PUT)
	public Object updateCardDetails(@Valid @RequestBody CardRequest cardRequest) throws SQLException {
		//cardRequest.setCardNumber(TravelexUtils.encodeString(cardRequest.getCardNumber(), 12));
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			Session session = em.unwrap(Session.class);
			SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
			connection = sfi.getJdbcServices().getBootstrapJdbcConnectionAccess().obtainConnection();
			String query = "Select * from updatecarddetails(?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(cardRequest.getUserId().toString()));
			preparedStatement.setInt(2, Integer.parseInt(cardRequest.getCardId().toString()));
			preparedStatement.setString(3, cardRequest.getCardType());
			preparedStatement.setString(4, cardRequest.getExpiryDate());
			preparedStatement.setBoolean(5, cardRequest.isActive());
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
}
