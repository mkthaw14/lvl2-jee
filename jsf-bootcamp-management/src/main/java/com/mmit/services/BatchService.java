package com.mmit.services;

import java.util.List;

import com.mmit.entities.Batch;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


@Stateless
public class BatchService
{
	@PersistenceContext
	private EntityManager em;

	public void save(Batch batch)
	{
		if(batch.getId() == 0)
			em.persist(batch);
		else 
			em.merge(batch);
		
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
