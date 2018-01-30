package com.travelex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelex.entities.Card;
import com.travelex.entities.Consumer;

public interface CardRepository extends JpaRepository<Card, Long> {
	
	List<Card> findByConsumer(Consumer consumer);
}
