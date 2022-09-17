package com.mmit.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.mmit.entities.Batch;
import com.mmit.services.BatchService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.RequestParameterMap;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class BatchBean implements Serializable
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Batch batch;
	private List<Batch> batches;
	
	@Inject
	private BatchService service;
	
	@Inject
	private FacesContext context;
	
	@RequestParameterMap
	@Inject
	private Map<String, String> paramMap;
	
	@PostConstruct
	public void init()
	{
		String id = paramMap.get("batch-id");
		batch = id == null ? new Batch() : service.findById(Integer.parseInt(id));
		System.out.println("Batch " + batch);
		System.out.println("Batch bean post construct method");
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public List<Batch> getBatches() {
		batches = service.getAllBatch();
		return batches;
	}

	public void setBatches(List<Batch> batches) {
		this.batches = batches;
	}
	
	public String save()
	{
		if(context.isValidationFailed())
			return null;
		
		service.save(batch);
		return "/batch-list?faces-redirect=true";
	}
	
	//Action Listener method
	public void checkBatchNameAndLevel()
	{
		if(service.batchNameAndLevelIdExist(batch))
		{
			System.out.println("Batch name and level id exist");
			FacesMessage msg = new FacesMessage("Batch name and level id exist");
			
			//Form id : component id
			context.addMessage("reg-batch:batch-name", msg);
			context.validationFailed();
		}
	}
}
