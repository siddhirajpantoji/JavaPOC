package com.travelex.response;


import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString( callSuper= false)
@EqualsAndHashCode( callSuper = false)
@JsonInclude(value=Include.NON_NULL)
public class ValidationResponse extends BaseResponse {

	private Map<String,String> reason;
	
	public ValidationResponse(HttpStatus status, String message) {
		super(status, message);
	}

	public ValidationResponse(HttpStatus status, String message, Map<String, String> messageMap) {
		super(status, message);
		this.reason = messageMap;
	}
}