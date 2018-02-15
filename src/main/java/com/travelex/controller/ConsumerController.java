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

	// private static final Logger LOGGER =
	// Logger.getLogger(ConsumerController.class);

	@Autowired
	EntityManager em;

	@Autowired
	private ConsumerRepository consumerRepository;
	@Autowired
	private CardRepository cardRepository;

	// API to Create Consumer
	@ApiOperation(value = "Registers a new user  ", response = BaseResponse.class)
	@RequestMapping(method = RequestMethod.POST, value = RESTEndPointMapper.REGISTRATION)
	public Object createConsumer(@RequestBody ConsumerRequest consumerRequest) {
		// LOGGER.info("Entered into createConsumer");
		consumerRequest.setPassword(
				TravelexUtils.encodeString(consumerRequest.getPassword(), consumerRequest.getPassword().length()));
		StoredProcedureQuery query = this.em.createStoredProcedureQuery("addconsumer");
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);

		query.setParameter(1, consumerRequest.getEmail());
		query.setParameter(2, consumerRequest.getFirstName());
		query.setParameter(3, consumerRequest.getLastName());
		query.setParameter(4, consumerRequest.getPassword());
		return query.getSingleResult();
	}

	@ApiOperation(value = "Checks Login for Each User  ", response = ConsumerResponse.class)
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", value = RESTEndPointMapper.LOGIN)
	public ResponseEntity<ConsumerResponse> login(@Valid @RequestBody LoginRequest consumerRequest) {
		consumerRequest.setPassword(
				TravelexUtils.encodeString(consumerRequest.getPassword(), consumerRequest.getPassword().length()));
		// consumerRequest.setPassword(TravelexUtils.encodeString(consumerRequest.getPassword(),
		// consumerRequest.getPassword().length()));
		Consumer consumer = consumerRepository.findByEmailAndPassword(consumerRequest.getEmailId(),
				consumerRequest.getPassword());
		if (null == consumer) {
			return new ResponseEntity<ConsumerResponse>(
					new ConsumerResponse(HttpStatus.BAD_REQUEST, MessageConstants.INVALID_LOGIN),
					HttpStatus.BAD_REQUEST);
		}
		List<Card> cards = cardRepository.findByConsumer(consumer.getUserId());
		
		return new ResponseEntity<ConsumerResponse>(new ConsumerResponse(consumer, cards),HttpStatus.OK);
//		// Session session = (Session) this.em.getDelegate();
//		// ProcedureCall procedureCall =
//		// session.createStoredProcedureCall("getconsumerdetails");
//		// procedureCall.registerParameter("emailid", String.class, ParameterMode.IN);
//		// procedureCall.registerParameter("pass", String.class, ParameterMode.IN);
//		// procedureCall.getParameterRegistration("emailid").bindValue(consumerRequest.getEmailId());
//		// procedureCall.getParameterRegistration("pass").bindValue(consumerRequest.getPassword());
//		// ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
//		// System.out.println(procedureOutputs);
//		// ResultSetOutput resultSetOutput = (ResultSetOutput)
//		// procedureOutputs.getCurrent();
//		// System.out.println(resultSetOutput);
//		// List results = resultSetOutput.getResultList();
//		// System.out.println(results);
//
//		javax.persistence.Query query1 = this.em.createNativeQuery(
//				"Select * from getconsumerdetails('" + consumerRequest.getEmailId() + "','Ka\\\\8)*+')",
//				"ConsumerMapping");
//		System.out.println(query1.getSingleResult());
//		// StoredProcedureQuery query = this.em.createStoredProcedureQuery(" Select *
//		// from getconsumerdetails()");
//		// // return new ResponseEntity<ConsumerResponse>(new ConsumerResponse(consumer,
//		// // cards), HttpStatus.OK);
//		// // query.registerStoredProcedureParameter(1, Object.class,
//		// ParameterMode.OUT);
//		// query.registerStoredProcedureParameter("emailid", String.class,
//		// ParameterMode.IN);
//		// query.registerStoredProcedureParameter("pass", String.class,
//		// ParameterMode.IN);
//		// // query.registerStoredProcedureParameter(3, JSONObject.class,
//		// // ParameterMode.OUT);
//		// JSONObject jsonObject = null;
//		// // Object object =null;
//		// // //query.setParameter(1,object );
//		// query.setParameter("emailid", consumerRequest.getEmailId());
//		// query.setParameter("pass", consumerRequest.getPassword());
//		// // query.setParameter(3, jsonObject);
//		// System.out.println(query.getSingleResult());
//		// jsonObject = (JSONObject) query.getOutputParameterValue(1);
//		// return jsonObject;
//		// return (JSONObject) query.getSingleResult();
//		// return object;
//		// return consumers;
//		return null;
	}

}
