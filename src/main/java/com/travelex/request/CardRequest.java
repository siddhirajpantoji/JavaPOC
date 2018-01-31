package com.travelex.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class CardRequest {
	@NotNull( message = "Consumer Id is required ")
	private Long consumerId;
	private List<CardRequestSingle> cards;
}
