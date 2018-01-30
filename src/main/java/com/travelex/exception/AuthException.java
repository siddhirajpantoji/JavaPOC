package com.travelex.exception;

import java.io.IOException;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper= false)
@EqualsAndHashCode(callSuper= false)
public class AuthException  extends IOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public AuthException(String message) {
		super();
		this.message = message;
	}

}
