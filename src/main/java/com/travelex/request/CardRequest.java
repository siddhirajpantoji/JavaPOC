package com.travelex.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class CardRequest {
	private Long cardId;

	@NotNull( message = "user Id Cannot be null")
	@ApiModelProperty(required= true,value="user id of Card Holder ")
	private Long userId;
		
	private String expiryDate;
	
	private boolean isActive;
	
	@NotEmpty(message="Card Type cannot be empty")
	@ApiModelProperty(required=true)
	private String cardType;
}
