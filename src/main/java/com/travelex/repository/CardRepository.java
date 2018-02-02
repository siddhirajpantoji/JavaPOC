package com.travelex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.travelex.entities.Card;
import com.travelex.entities.Consumer;
import java.lang.String;

public interface CardRepository extends JpaRepository<Card, Long> {
	
	List<Card> findByConsumer(Consumer consumer);
	
	@Query( nativeQuery=true, value="Select count(*) from card where consumer_id=:consumerId and card_type=:cardType and card_number=:cardNumber")
	Long countByConsumerCardTypeAndCard(@Param("consumerId")Long consumerId, @Param("cardType") String cardType, @Param("cardNumber") String cardNumber);
	
//	List<Card> findByCardNumberAndConsumerAndCardType(String cardnumber, Consumer consumer, String cardType);
}
