package com.mmit.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.mmit.entities.Level;
import com.mmit.services.LevelService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.RequestParameterMap;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class LevelBean implements Serializable
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private Level level;
	private List<Level> levels;
	
	@RequestParameterMap
	@Inject
	Map<String, String> paramMap;
	
	@Inject
	private LevelService service;

	
	@PostConstruct
	public void init()
	{
		System.out.println("Level bean post construct method");
		
		String id = paramMap.get("levelId");
		level = id == null ? new Level() : service.findById(Integer.parseInt(id));
	}
	


	public Level getLevel() {
		return level;
	}


	public void setLevel(Level level) {
		this.level = level;
	}


	public LevelService getService() {
		return service;
	}


	public void setService(LevelService service) {
		this.service = service;
	}


	public List<Level> getLevels() {
		levels = service.getAllLevel();
		return levels;
	}
	
	public String save()
	{
		service.save(level);
		return "/index?faces-redirect=true";
	}
	
}
