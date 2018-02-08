package com.travelex.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * All End points are stored here 
 * @author pantojis
 *
 */

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class RESTEndPointMapper {

	public static final String REGISTRATION = "/registration"; //ConsumerController
	
	public static final String LOGIN= "/login"; // ConsumerController
	
	public static final String CARD_DETAILS = "/cardDetails";
}