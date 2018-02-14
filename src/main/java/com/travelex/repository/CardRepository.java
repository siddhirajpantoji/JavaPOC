package com.travelex.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.travelex.entities.Card;

@Transactional
public interface CardRepository extends JpaRepository<Card, Long> {

	
	@Query( nativeQuery=true, value="Select * from card where consumer_id=:consumerId")
	List<Card> findByConsumer(@Param("consumerId")Long consumerId);
	
	@Query( nativeQuery=true, value="Select count(*) from card where consumer_id=:consumerId and card_type=:cardType and card_number=:cardNumber")
	Long countByConsumerCardTypeAndCard(@Param("consumerId")Long consumerId, @Param("cardType") String cardType, @Param("cardNumber") String cardNumber);
	
}
