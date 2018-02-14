package com.travelex.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString( callSuper = false)
@EqualsAndHashCode( callSuper = false)
@JsonInclude(value=Include.NON_NULL)
@NoArgsConstructor
public class BaseResponse {

	@ApiModelProperty(notes = "HttpStatus Code ")
	protected HttpStatus status;
	
	protected String message;

	public BaseResponse(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	
}
