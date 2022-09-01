package com.mmit.model.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mmitt.model.entity.Level;

public class LevelService
{
	private EntityManager em;
	
	
	public LevelService(EntityManager em)
	{
		this.em = em;
	}
	
	public void save(Level lvl)
	{
		em.getTransaction().begin();
		if(lvl.getId() == 0)
			em.persist(lvl);
		else
			em.merge(lvl);
		em.getTransaction().commit();
	}
	
	public void updateLevel(Level lvl)
	{
		Level levelToUpate = em.find(Level.class, lvl.getId());
		em.getTransaction().begin();
		levelToUpate.setName(lvl.getName());
		levelToUpate.setDuration(lvl.getDuration());
		levelToUpate.setFees(lvl.getFees());
		em.getTransaction().commit();
	}
	
	public void removeLevel(Level lvl)
	{
		em.getTransaction().begin();
		em.remove(lvl);
		em.getTransaction().commit();
	}

	public List<Level> getAllLevel()
	{
		List<Level> list = new ArrayList<Level>();
		
		TypedQuery<Level> query = em.createNamedQuery("getAllLevel", Level.class);
		list = query.getResultList();
		
		if(BatchService.isModelUpdated() || CourseService.isModelUpdated())
		{
			System.out.println("Batch/Course Model updated");
			refresh(list);
			BatchService.setModelUpdated(false);
			CourseService.setModelUpdated(false);
		}
		return list;
	}

	public Level findById(int lvlId)
	{
		return em.find(Level.class, lvlId);
	}
	
	public void refresh(List<Level> lvls)
	{
	    for (Level l: lvls)
		{
			em.refresh(l);
		}
	}
}
