package com.mmit.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.mmit.entities.Batch;
import com.mmit.entities.Bootcamper;
import com.mmit.services.BatchService;
import com.mmit.services.BootCamperService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.RequestParameterMap;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class BootcamperBean implements Serializable
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private Bootcamper bootcamper;
	private List<Bootcamper> bootcampers;
	
	@Inject
	private BootCamperService service;
	@Inject
	private BatchService batchService;
	
	@RequestParameterMap
	@Inject
	private Map<String, String> paramMap;
	
	private List<Batch> batchList;
	
	private int chosen_lvl_id;
	
	@PostConstruct
	public void Init()
	{
		String id = paramMap.get("bootcamperId");
		bootcamper = id == null ? new Bootcamper() : service.findById(Integer.parseInt(id));
		System.out.println("Bootcamper bean post construct method");
	}

	
	public int getChosen_lvl_id() {
		return chosen_lvl_id;
	}


	public void setChosen_lvl_id(int chosen_lvl_id) {
		this.chosen_lvl_id = chosen_lvl_id;
	}


	public Bootcamper getBootcamper() {
		return bootcamper;
	}

	public void setBootcamper(Bootcamper bootcamper) {
		this.bootcamper = bootcamper;
	}

	public BootCamperService getService() {
		return service;
	}

	public void setService(BootCamperService service) {
		this.service = service;
	}

	public Map<String, String> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}

	public List<Bootcamper> getBootcampers() {
		
		bootcampers = service.getAllBootcamper();
		return bootcampers;
	}
	
	

	public List<Batch> getBatchList() {
		return batchList;
	}


	public void retrieveBatchByLvlId()
	{
		System.out.println("Chosen lvl id : " + chosen_lvl_id);
		batchList = batchService.findAllByLevelId(chosen_lvl_id);
	}
	
	public String save()
	{
		service.save(bootcamper);
		return "/bootcamper-list?faces-redirect=true";
	}
	
	public String delete(int id)
	{
		System.out.println("Bootcamper delete");

		service.removeById(id);
		return "/bootcamper-list?faces-redirect=true";
	}
	
}
