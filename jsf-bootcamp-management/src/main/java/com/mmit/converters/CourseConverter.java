package com.mmit.converters;

import com.mmit.entities.Course;
import com.mmit.services.CourseService;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class CourseConverter implements Converter<Course>
{
	@Inject
	private CourseService service;
	
	@Override
	public Course getAsObject(FacesContext context, UIComponent component, String id) {
		// TODO Auto-generated method stub
		if(id == null) return null;
		
		try
		{
			return service.findById(Integer.parseInt(id));
		}
		catch (NumberFormatException e) {
			throw new NumberFormatException(id + " is not valid");
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Course course) {
		// TODO Auto-generated method stub
		return String.valueOf(course.getId());
	}

}
