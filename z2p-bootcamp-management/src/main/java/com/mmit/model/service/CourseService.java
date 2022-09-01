package com.mmit.model.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mmitt.model.entity.Course;

public class CourseService 
{
	static EntityManager em;
	private static boolean modelUpdated;
	
	public static boolean isModelUpdated()
	{
		return modelUpdated;
	}

	public static void setModelUpdated(boolean modelUpdated)
	{
		CourseService.modelUpdated = modelUpdated;
	}

	public CourseService(EntityManager em)
	{
		this.em = em;
	}

	public void save(Course course)
	{
		em.getTransaction().begin();
		if(course.getId() == 0)
			em.persist(course);
		else
			em.merge(course);
		em.getTransaction().commit();
	}

	public List<Course> getAllCourse()
	{
		TypedQuery<Course> query = em.createNamedQuery("getAllCourse", Course.class);
		return query.getResultList();
	}

	public Course findById(int id)
	{
		// TODO Auto-generated method stub
		return em.find(Course.class, id);
	}

	public void remove(Course course)
	{
		em.getTransaction().begin();
		em.remove(course);
		em.getTransaction().commit();
	}

	public List<Course> findByLevelId(int lvlId)
	{
		TypedQuery<Course> query = em.createNamedQuery("findByLevelId", Course.class);
		query.setParameter("level_id", lvlId);
		return query.getResultList();
	}

}
