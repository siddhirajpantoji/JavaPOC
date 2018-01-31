package com.travelex.request;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class CardRequestSingle {
	private Long cardId;

	@Size(max=16, min = 16, message= "Card Number must be 16 digits ")
//	@Pattern(regexp="[0-9]",message="Test ")
//	@MatchesPattern(value="[0-9]")
	private String cardNumber;
	
	private String expiryDate;
	
	private boolean isActive;
}