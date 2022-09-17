package com.mmit.services;

import java.util.List;

import com.mmit.entities.Batch;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.metamodel.Type;


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

	public boolean batchNameAndLevelIdExist(Batch batch) {
	    TypedQuery<Batch> query = em.createNamedQuery("findBatchNameAndLevel", Batch.class);
	    query.setParameter("b_name", batch.getName());
	    query.setParameter("b_level_id", batch.getLevel().getId());
	     
	    try
	    {
	    	query.getSingleResult();
	    }
	    catch (NoResultException e)
	    {
	    	System.out.println(e.getMessage());
	    	return false;
	    }
		return true;
	}

}
