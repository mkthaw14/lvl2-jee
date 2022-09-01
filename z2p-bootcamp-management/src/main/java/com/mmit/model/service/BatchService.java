package com.mmit.model.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mmitt.model.entity.Batch;

public class BatchService
{
	public static boolean isModelUpdated()
	{
		return modelUpdated;
	}

	public static void setModelUpdated(boolean modelUpdated)
	{
		BatchService.modelUpdated = modelUpdated;
	}

	static EntityManager em;
	private static boolean modelUpdated;
	
	public BatchService(EntityManager em)
	{
		this.em = em;
	}

	public void save(Batch batch)
	{
		em.getTransaction().begin();
		if(batch.getId() == 0)
			em.persist(batch);
		else 
			em.merge(batch);
		
		em.getTransaction().commit();
	}

	public List<Batch> getAllBatch()
	{
		TypedQuery<Batch> query = em.createNamedQuery("getAllBatch", Batch.class);

		return query.getResultList();
	}

	public Batch findById(int id)
	{
		return em.find(Batch.class, id);
	}

	public List<Batch> findAllByLevelId(int lvlId)
	{
		TypedQuery<Batch> query = em.createNamedQuery("findByLevelID", Batch.class);
		query.setParameter("lvl_Id", lvlId);
		return query.getResultList();
	}

}
