package com.mmit.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mmit.entities.Level;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class LevelService
{
	@PersistenceContext
	private EntityManager em;
	
	public void save(Level lvl)
	{
		if(lvl.getId() == 0)
			em.persist(lvl);
		else
			em.merge(lvl);
	}
	

	
	public void removeLevel(Level lvl)
	{
		em.remove(lvl);
	}

	public List<Level> getAllLevel()
	{
		List<Level> list = new ArrayList<Level>();
		
		TypedQuery<Level> query = em.createNamedQuery("getAllLevel", Level.class);
		list = query.getResultList();
		
		//if(BatchService.isModelUpdated() || CourseService.isModelUpdated())
		//{
			//System.out.println("Batch/Course Model updated");
			//refresh(list);
			//BatchService.setModelUpdated(false);
			//CourseService.setModelUpdated(false);
		//}
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
