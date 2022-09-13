package com.mmit.converters;

import com.mmit.entities.Batch;
import com.mmit.services.BatchService;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class BatchConverter implements Converter<Batch>
{

	@Inject
	private BatchService service;
	
	@Override
	public Batch getAsObject(FacesContext context, UIComponent component, String id) {
		if(id == null)
			return null;
	    
		try
		{
			return service.findById(Integer.parseInt(id));
		}
		catch(NumberFormatException e)
		{
			throw new NumberFormatException(id + " is not valid");
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Batch bt) {
		// TODO Auto-generated method stub
		return String.valueOf(bt.getId());
	}

}
