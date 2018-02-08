package com.travelex.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * This is basic user table that is to be done 
 * @author pantojis
 *
 */
@Entity
@Data
@NoArgsConstructor
@ToString ( callSuper = false )
@EqualsAndHashCode( callSuper = false)
@Table( uniqueConstraints = {
		@UniqueConstraint(columnNames = {"email"})
})
public class Consumer {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long userId;
	
	private String firstName;
	private String lastName;
	
	@Column( name="email")
	private String email;
	
	private String password;
	
}
