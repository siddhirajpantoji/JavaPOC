package com.travelex.entities;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * This class is a Super Class for all entities that are to be created in DB 
 * It will automatically set the created as well as modified time stamp 
 * @author pantojis
 *
 */
@MappedSuperclass
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class BaseEntity {

	protected Date createdTs;
	
	protected Date modifiedTs;
	
	// This is setting created Time stamp before inserting of every row 
	@PrePersist
	void onCreate()
	{
		this.createdTs= new Date();
	}
	
	@PreUpdate
	void onUpdate()
	{
		this.modifiedTs = new Date();
	}
}
