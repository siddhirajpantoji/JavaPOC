package com.travelex.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString ( callSuper = false )
@EqualsAndHashCode( callSuper = false)
@JsonIgnoreProperties(value= {"consumer"})
public class Card {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="card_id")
	private Long cardId;
	
	private String cardNumber;
	
	private String expiryDate;
	
	private boolean isActive;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="consumer_id", nullable= false)
	@ApiModelProperty(hidden=true)
	private Consumer  consumer;
}
