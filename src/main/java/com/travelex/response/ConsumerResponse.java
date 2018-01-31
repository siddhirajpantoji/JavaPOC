package com.travelex.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.travelex.constants.MessageConstants;
import com.travelex.entities.Card;
import com.travelex.entities.Consumer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString( callSuper = false)
@EqualsAndHashCode( callSuper = false)
@JsonInclude(value=Include.NON_NULL)
public class ConsumerResponse extends BaseResponse {

	private String firstName;
	
	private String lastName;
	
	private String email;

	private Long consumerId;
	
	private List<Card> cards;

	public ConsumerResponse(HttpStatus status, String message) {
		super(status, message);
	}

	public ConsumerResponse( Consumer consumer,List<Card> cards) {
		super(HttpStatus.OK, MessageConstants.EVERYTHING_LOOKS_GOOD);
		this.firstName =consumer.getFirstName();
		this.lastName = consumer.getLastName();
		this.email = consumer.getEmail();
		this.consumerId = consumer.getUserId();
		this.cards = cards;
	}

}
