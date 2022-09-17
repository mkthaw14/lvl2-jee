package com.mmit.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import static jakarta.persistence.CascadeType.MERGE;

/**
 * Entity implementation class for Entity: Batch
 *
 */
@Entity
@Table(
		name = "batches",
		uniqueConstraints = {
			@UniqueConstraint(columnNames = {"name", "level_id"})
		} 
)
@NamedQuery(name = "getAllBatch", query = "Select b from Batch b")
@NamedQuery(name = "findByLevelID", query = "Select b from Batch b, Level l Where l.id = :lvl_Id And l.id = b.level.id")
@NamedQuery(name = "findBatchNameAndLevel", query = "Select b from Batch b Where b.name = :b_name And b.level.id = :b_level_id")
public class Batch implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private LocalDate startDate;
	
	@NotNull(message = "level must not be null")
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Batch other = (Batch) obj;
		return id == other.id;
	}
	
	
   
}
