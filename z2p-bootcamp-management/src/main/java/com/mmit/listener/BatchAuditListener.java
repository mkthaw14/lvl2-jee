package com.mmit.listener;


import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.mmit.model.service.BatchService;
import com.mmitt.model.entity.Batch;


public class BatchAuditListener  
{
	@PrePersist
	@PreUpdate
	@PreRemove
	private void beforeUpdate(Batch batch)
	{
		System.out.println("before update Batch ID : " + batch.getId());
	}
	
	@PostPersist
	@PostRemove
	@PostUpdate
	private void afterUpdate(Batch batch)
	{
		BatchService.setModelUpdated(true);
		System.out.println("after Add/Update batch: " + BatchService.isModelUpdated());
	}
	
	@PostLoad
	private void loadBatch(Batch batch)
	{
		System.out.println("load Batch ID : " + batch.getId());
	}

	public BatchAuditListener() {
		super();
	}
  
}
