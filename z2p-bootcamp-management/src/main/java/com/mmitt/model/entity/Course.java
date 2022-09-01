package com.mmitt.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.mmit.listener.CourseAuditListener;

/**
 * Entity implementation class for Entity: Course
 *
 */
@Entity
@Table(name = "courses")
@NamedQuery(name = "getAllCourse", query = "Select c from Course c")
@NamedQuery(name = "findByLevelId", query = "Select c from Course c, Level l Where c.level.id = :level_id And c.level.id = l.id")
@EntityListeners(CourseAuditListener.class)
public class Course implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "level_id")
	private Level level;

    @CreationTimestamp
    private LocalDate created_at;
    
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

	@UpdateTimestamp
    private LocalDate updated_at;
}
