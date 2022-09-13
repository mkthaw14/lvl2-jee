package com.mmit.services;

import java.util.List;

import com.mmit.entities.Course;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class CourseService 
{
	@PersistenceContext
	private EntityManager em;

	public void save(Course course)
	{
		if(course.getId() == 0)
			em.persist(course);
		else
			em.merge(course);
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
		em.remove(course);
	}

	public List<Course> findByLevelId(int lvlId)
	{
		TypedQuery<Course> query = em.createNamedQuery("findByLevelId", Course.class);
		query.setParameter("level_id", lvlId);
		return query.getResultList();
	}

	public void removeById(int id) {
		Course course = em.find(Course.class, id);
		em.remove(course);
	}

}
