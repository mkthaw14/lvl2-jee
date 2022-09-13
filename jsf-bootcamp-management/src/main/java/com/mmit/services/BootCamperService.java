package com.mmit.services;

import java.security.Provider.Service;
import java.util.List;

import com.mmit.entities.Bootcamper;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class BootCamperService 
{
	@PersistenceContext
	private EntityManager em;
	
	public void save(Bootcamper btc)
	{
		if(btc.getId() == 0)
			em.persist(btc);
		else
			em.merge(btc);
	}
	
	public void remove(Bootcamper btc)
	{
		em.remove(btc);
	}
	
	
	
	public Bootcamper findById(int id)
	{
		return em.find(Bootcamper.class, id);
	}
	
	public List<Bootcamper> getAllBootcamper()
	{
		TypedQuery<Bootcamper> query = em.createNamedQuery("getAllBootcamper", Bootcamper.class);
		return query.getResultList();
	}

	public void removeById(int id) {
		Bootcamper bt = em.find(Bootcamper.class, id);
		em.remove(bt);
	}
}
