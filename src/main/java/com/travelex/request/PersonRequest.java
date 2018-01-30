package com.travelex.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString( callSuper = false)
@EqualsAndHashCode( callSuper = false)
@NoArgsConstructor
@ApiModel
public class PersonRequest {
	
	@ApiModelProperty(value ="Name of person ", required= true)
	@NotNull(message= "Name cannot be null")
	private String name;
	
	@NotNull(message= "Date of Birth cannot be null")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("Date of Birth ")
	private Date dob;
}