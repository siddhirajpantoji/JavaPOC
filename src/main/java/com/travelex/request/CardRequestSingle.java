package com.travelex.request;

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
	
	private String cardNumber;
	
	private String expiryDate;
	
	private boolean isActive;
}
