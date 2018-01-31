package com.travelex.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.travelex.entities.Card;

public class TravelexUtils {

	public static String encodeString(String toEncodeString, int lengthToEncode)
	{
		if(StringUtils.isEmpty(toEncodeString))
			return toEncodeString;
		
		StringBuffer buffer = new StringBuffer();
		for(int counter =0;counter<toEncodeString.length();counter++)
		{
			char c =toEncodeString.charAt(counter);
			if(counter<lengthToEncode)
			{
				c = (char) (c - (char)toEncodeString.length());	
			}			
			buffer.append(c);
		}
		return buffer.toString();
	}
	
	public static String decodeString(String toEncodeString,  int lengthToEncode)
	{
		if(StringUtils.isEmpty(toEncodeString))
			return toEncodeString;
		
		StringBuffer buffer = new StringBuffer();
		for(int counter =0;counter<toEncodeString.length();counter++)
		{
			char c =toEncodeString.charAt(counter);
			if(counter<lengthToEncode)
			{
				c = (char) (c + (char)toEncodeString.length());	
			}
			
			buffer.append(c);
		}
		return buffer.toString();
	}
	
	public static List<Card> getDecodedCards(List<Card> cards)
	{
		if(CollectionUtils.isEmpty(cards))
			return cards;
		
		List<Card> returnCards = new ArrayList<>();
		for(Card card : cards)
		{
			card.setCardNumber(decodeString(card.getCardNumber(), card.getCardNumber().length()-4));
			returnCards.add(card);
		}
		return returnCards;
	}
}
