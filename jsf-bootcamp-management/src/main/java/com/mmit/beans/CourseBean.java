package com.mmit.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.mmit.entities.Course;
import com.mmit.services.CourseService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.RequestParameterMap;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


@Named
@ViewScoped
public class CourseBean implements Serializable
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private Course course;
	private List<Course> courses;
	
	@Inject
	private CourseService service;
	
	@RequestParameterMap
	@Inject
	private Map<String, String> paramMap;
	
	
	@PostConstruct
	public void init()
	{
		String id = paramMap.get("courseId");
		course = id == null ? new Course() : service.findById(Integer.parseInt(id));
		System.out.println("Course Bean post construct");
	}


	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}


	public CourseService getService() {
		return service;
	}


	public void setService(CourseService service) {
		this.service = service;
	}


	public Map<String, String> getParamMap() {
		return paramMap;
	}


	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}


	public List<Course> getCourses() {
		courses = service.getAllCourse();
		return courses;
	}
	
	public String save() 
	{
		service.save(course);
		return "/course-list?faces-redirect=true";
	}
	
	public String delete(int id)
	{
		service.removeById(id);
		return "/course-list?faces-redirect=true";
	}

}
