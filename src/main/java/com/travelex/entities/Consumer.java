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
	
//	@NotEmpty( message = "Name Cannot be Empty ")
	private String firstName;
	private String lastName;
	
//	@NotEmpty( message = "Name Cannot be Empty ")
	@Column( name="email")
	private String email;
	
//	@NotEmpty( message = "Name Cannot be Empty ")
	private String password;

//	@OneToMany
//	private List<Card> cards;
	
}
