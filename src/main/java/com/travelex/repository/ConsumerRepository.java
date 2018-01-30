package com.travelex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.travelex.entities.Consumer;
import java.lang.String;
import java.util.List;

/**
 * This is database repository of User table for Basic CRUD Operations 
 * @author pantojis
 *
 */
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

	@Query( nativeQuery=true, value="Select count(*) from consumer where email=:emailId")
	Long countByEmailId(@Param("emailId")String emailId);

	List<Consumer> findByEmailAndPassword(String emailid, String password);
}
