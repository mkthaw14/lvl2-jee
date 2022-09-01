package com.mmitt.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.mmit.listener.BatchAuditListener;

import static javax.persistence.CascadeType.MERGE;

/**
 * Entity implementation class for Entity: Batch
 *
 */
@Entity
@Table(name = "batches")
@NamedQuery(name = "getAllBatch", query = "Select b from Batch b")
@NamedQuery(name = "findByLevelID", query = "Select b from Batch b, Level l Where l.id = :lvl_Id And l.id = b.level.id")
@EntityListeners(BatchAuditListener.class)
public class Batch implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private LocalDate startDate;
	
	@ManyToOne
	@JoinColumn(name = "level_id")
	private Level level;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public LocalDate getStartDate()
	{
		return startDate;
	}

	public void setStartDate(LocalDate startDate)
	{
		this.startDate = startDate;
	}

	public Level getLevel()
	{
		return level;
	}

	public void setLevel(Level level)
	{
		this.level = level;
	}
	
	
   
}
