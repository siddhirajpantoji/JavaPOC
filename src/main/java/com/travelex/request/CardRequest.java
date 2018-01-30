package com.travelex.request;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class CardRequest {
	private Long consumerId;
	private List<CardRequestSingle> cards;
}
