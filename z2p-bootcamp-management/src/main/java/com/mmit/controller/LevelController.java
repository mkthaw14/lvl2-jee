package com.mmit.controller;

import java.io.IOException;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mmit.model.service.LevelService;
import com.mmitt.model.entity.Level;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = { "/levels", "/level-add", "/level-update", "/level-delete"})

public class LevelController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	private LevelService lvlService;
	
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		// TODO Auto-generated method stub
		super.init(config);
		
		EntityManagerFactory emf_obj = (EntityManagerFactory) getServletContext().getAttribute("emf");
		if(emf_obj == null)
		{
			emf_obj = Persistence.createEntityManagerFactory("z2p-bootcamp-management");
			getServletContext().setAttribute("emf", emf_obj);
		}
		lvlService = new LevelService(emf_obj.createEntityManager());
	}
	
	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
		super.destroy();
		EntityManagerFactory emf_obj = (EntityManagerFactory) getServletContext().getAttribute("emf");
		if(emf_obj != null && emf_obj.isOpen())
		{
			emf_obj.close();
		}
	}
	
	public LevelController()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.getWriter().append("Served at: ").append(req.getContextPath());
		var action = req.getServletPath();
		
		System.out.println("level action " + action);
		
		if(action.equals("/level-add") || action.equals("/level-update"))
			goAddPage(req, resp);
		else
			goHomePage(req, resp);
	}
	


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		saveLevel(req, resp);
		resp.sendRedirect(req.getContextPath() + "/levels");
	}
	
	private void saveLevel(HttpServletRequest req, HttpServletResponse resp)
	{
		String lvlId = req.getParameter("lvl-id");
		System.out.println("Post Lvl Id " + lvlId);

		//var lvl = (lvlId.isEmpty() ? new Level() : lvlService.findById(Integer.parseInt(lvlId)));
		//System.out.println("Level " + lvl);
		Level lvl = new Level();
		lvl.setId(lvlId.isEmpty() ? 0 : Integer.parseInt(lvlId));	
		lvl.setName(req.getParameter("lvl-name"));
		lvl.setDuration(Integer.parseInt(req.getParameter("lvl-duration")));
		lvl.setFees(Integer.parseInt(req.getParameter("lvl-fees")));
		
		lvlService.save(lvl);
		//System.out.println("lvl name " + lvl.getName());
		//System.out.println("lvl duration " + lvl.getDuration());
		//System.out.println("lvl fee " + lvl.getFees());
	}
	
	private void goAddPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		var page = "/level-add.jsp";
		var title = "Add Level";	
		String lvlId = req.getParameter("id");
		
		if(lvlId != null) // It will go for update form
		{
			Level lvl = lvlService.findById(Integer.parseInt(lvlId));
			title = "Update Level";
			req.setAttribute("Level", lvl);
		}

		System.out.println("lvl Id " + lvlId);
		
		req.setAttribute("Title", title);
		getServletContext().getRequestDispatcher(page).forward(req, resp);
	}
	

	
	private void goHomePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		var page = "/levels.jsp";
		var title = "Level List";
		
		List<Level> levels = lvlService.getAllLevel();
		
		req.setAttribute("Title", title);
		req.setAttribute("Levels", levels);
		
		getServletContext().getRequestDispatcher(page).forward(req, resp);
	}
}
