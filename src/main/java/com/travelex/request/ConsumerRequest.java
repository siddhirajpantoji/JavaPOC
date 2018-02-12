package com.travelex.request;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString ( callSuper = false )
@EqualsAndHashCode( callSuper = false)
public class ConsumerRequest {

	private Long userId;
	
//	@NotEmpty( message = "Name Cannot be Empty ")
	private String firstName;
	private String lastName;
	
	@NotEmpty( message = "Email Id Cannot be Empty ")
	private String email;
	
	@NotEmpty( message = "Password Cannot be Empty ")
	private String password;
		
}
