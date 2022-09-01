package com.mmitt.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.CascadeType.MERGE;

/**
 * Entity implementation class for Entity: Level
 *
 */
@Entity
@Table(name = "levels")
@NamedQuery(name = "getAllLevel", query = "Select l from Level l")
public class Level implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int duration;
	private int fees;
	@OneToMany(mappedBy = "level", fetch = LAZY)
	private List<Batch> batches = new ArrayList<Batch>();
	
	@OneToMany(mappedBy = "level")
	private List<Course> courses = new ArrayList<Course>();
	
	public int getBatchCount()
	{
		return this.batches.size();
	}
	
	public int getCourseCount()
	{
		return this.courses.size();
	}
	
	public List<Batch> getBatches()
	{
		return batches;
	}
	public void setBatches(List<Batch> batches)
	{
		this.batches = batches;
	}
	@Override
	public String toString()
	{
		return "Level [id=" + id + ", name=" + name + ", duration=" + duration + ", fees=" + fees + "]";
	}
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
	public int getDuration()
	{
		return duration;
	}
	public void setDuration(int duration)
	{
		this.duration = duration;
	}
	public int getFees()
	{
		return fees;
	}
	public void setFees(int fees)
	{
		this.fees = fees;
	}

   
}
