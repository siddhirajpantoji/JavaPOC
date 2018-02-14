package com.travelex.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
@Table( uniqueConstraints = {
		@UniqueConstraint(columnNames = {"card_number","consumer_id","card_type"})
})
@JsonIgnoreProperties(value = { "consumer" })
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "card_id")
	private Long cardId;

	@NotEmpty
//	@Size(max = 16, min = 16, message = "Card Number must be 16 digits ")
	@Column(name="card_number")
	private String cardNumber;

	private String expiryDate;

	private boolean isActive;

	@ManyToOne(optional = false)
	@JoinColumn(name = "consumer_id", nullable = false)
	@ApiModelProperty(hidden = true)
	private Consumer consumer;

	@Column(name = "card_type")
	@NotEmpty(message = "Card Type cannot be empty ")
	private String cardType;

}
