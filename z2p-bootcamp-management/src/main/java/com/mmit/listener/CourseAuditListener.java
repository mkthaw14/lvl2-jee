package com.mmit.listener;


import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.mmit.model.service.BatchService;
import com.mmit.model.service.CourseService;
import com.mmitt.model.entity.Batch;
import com.mmitt.model.entity.Course;


public class CourseAuditListener  
{
	
	@PrePersist
	@PreUpdate
	@PreRemove
	private void beforeUpdate(Course course)
	{
		System.out.println("before update Course ID : " + course.getId());
	}
	
	@PostPersist
	@PostRemove
	@PostUpdate
	private void afterUpdate(Course course)
	{
		CourseService.setModelUpdated(true);
		System.out.println("after Add/Update course: " + BatchService.isModelUpdated());
	}
	
	@PostLoad
	private void loadBatch(Course course)
	{
		System.out.println("load Course ID : " + course.getId());
	}

	public CourseAuditListener() {
		super();
	}
   
}
