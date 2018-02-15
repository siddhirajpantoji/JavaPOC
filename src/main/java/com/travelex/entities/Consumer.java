package com.travelex.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * This is basic user table that is to be done
 * 
 * @author pantojis
 *
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
@SqlResultSetMappings({ @SqlResultSetMapping(name = "ConsumerMapping", entities = {
		@EntityResult(entityClass = Consumer.class, fields = {
				@FieldResult(name="cards", column="cards"),
				@FieldResult(name="first_name", column="first_name"),
				@FieldResult(name="last_name", column="last_name"),
				@FieldResult(name="user_id", column="user_id"),
				@FieldResult(name="email", column="email"),
		}) })

})
@Data
@NoArgsConstructor
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)

// @SqlResultSetMapping(name="OrderResults",
// entities={
// @EntityResult(entityClass=com.acme.Order.class, fields={
// @FieldResult(name="id", column="order_id"),
// @FieldResult(name="quantity", column="order_quantity"),
// @FieldResult(name="item", column="order_item")})},
// columns={
// @ColumnResult(name="item_name")}
// )

public class Consumer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	private String firstName;
	private String lastName;

	@Column(name = "email")
	private String email;

	private String password;

	@Transient
	List<Card> cards;

	public Consumer(Long userId, String firstName, String lastName, String email) {

		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Consumer( List<Card> cards ,String firstName, String lastName, Long userId, String email) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.cards = cards;
	}

}
