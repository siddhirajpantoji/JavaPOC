package com.travelex.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@ApiModel(description="Single Detail of Card . This requiest is for Creating as well as updating Card ")
public class CardRequestSingle {
	private Long cardId;
	
	@NotNull( message = "user Id Cannot be null")
	@ApiModelProperty(required= true,value="user id of Card Holder ")
	private Long userId;
	
	@Size(max=16, min = 16, message= "Card Number must be 16 digits ")
//	@Pattern(regexp="[0-9]",message="Test ")
//	@MatchesPattern(value="[0-9]")
	private String cardNumber;
	
	private String expiryDate;
	
	private boolean isActive;
	
	@NotEmpty(message="Card Type cannot be empty")
	@ApiModelProperty(required=true)
	private String cardType;
}