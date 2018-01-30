package com.travelex.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Data
@JsonInclude(value=Include.NON_NULL)
public class Greeting {

	@ApiModelProperty(notes = "This is counter of calls ")
	private  Long id;

	@ApiModelProperty(notes = " Content produced by the API ")
	private  String content;

	public Greeting(Long id, String content) {
		this.id = id;
		this.content = content;
	}

}
