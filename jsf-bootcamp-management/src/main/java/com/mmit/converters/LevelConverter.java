package com.mmit.converters;

import com.mmit.entities.Level;
import com.mmit.services.LevelService;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class LevelConverter implements Converter<Level>
{
	@Inject
	private LevelService service;
	
	@Override
	public Level getAsObject(FacesContext context, UIComponent component, String id) {
		
		if(id == null)
			return null;
	
		System.out.println("Value : " + id);
		try
		{
			return service.findById(Integer.parseInt(id));
		}
		catch(NumberFormatException e)
		{
			//throw new NumberFormatException(id + " is not valid");
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Level lvl) {
		if(lvl == null)
			return null;
		
		return String.valueOf(lvl.getId());
	}
	
}
