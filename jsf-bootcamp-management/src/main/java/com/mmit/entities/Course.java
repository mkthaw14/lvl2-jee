package com.mmit.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;



/**
 * Entity implementation class for Entity: Course
 *
 */
@Entity
@Table(name = "courses")
@NamedQuery(name = "getAllCourse", query = "Select c from Course c")
@NamedQuery(name = "findByLevelId", query = "Select c from Course c, Level l Where c.level.id = :level_id And c.level.id = l.id")

public class Course implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "level_id")
	@NotNull(message = "level must not be null")
	private Level level;

    private LocalDate created_at;
    private LocalDate updated_at;
    
    @PrePersist
    public void preInsert()
    {
    	created_at = LocalDate.now();
    }
    
    @PreUpdate
    public void preUpdate() 
    {
    	updated_at = LocalDate.now();
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

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public Level getLevel()
	{
		return level;
	}

	public void setLevel(Level level)
	{
		this.level = level;
	}

	public LocalDate getCreated_at()
	{
		return created_at;
	}

	public void setCreated_at(LocalDate created_at)
	{
		this.created_at = created_at;
	}

	public LocalDate getUpdated_at()
	{
		return updated_at;
	}

	public void setUpdated_at(LocalDate updated_at)
	{
		this.updated_at = updated_at;
	}



}
