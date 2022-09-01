package com.mmit.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mmit.model.service.BatchService;
import com.mmit.model.service.LevelService;
import com.mmitt.model.entity.Batch;
import com.mmitt.model.entity.Level;


/**
 * Servlet implementation class BatchController
 */
@WebServlet(urlPatterns = {"/home", "/batches", "/batch-add"})
public class BatchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private BatchService btService;
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
		
		btService = new BatchService(emf_obj.createEntityManager());
		lvlService = new LevelService(emf_obj.createEntityManager());
	}
	
	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
		super.destroy();
		EntityManagerFactory emf_obj = (EntityManagerFactory) getServletContext().getAttribute("emf");
		System.out.println("Entit M : " + emf_obj);
		if(emf_obj != null && emf_obj.isOpen())
		{
			emf_obj.close();
		}
	}
	
    public BatchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		var action = request.getServletPath();	
		response.getWriter().write("Servlet path : " + action);	
		if(action.equals("/batches"))
		{
			removeEditSessionID(request.getSession());
			goBatchHomePage(request, response);
		}
		else if(action.equals("/batch-add"))
		{
			goAddPage(request, response);
		}
		else
		{
			removeEditSessionID(request.getSession());
			goHomePage(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		saveBatch(request, response);
	}
	
	private void removeEditSessionID(HttpSession session)
	{
		var editID = session.getAttribute("editID");
		if(editID != null)
			session.removeAttribute("editID");
	}
	
	private void saveBatch(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session = request.getSession();
		var editID = session.getAttribute("editID");
		
		Batch batch = editID == null ? new Batch() : btService.findById(Integer.parseInt(editID.toString()));
		if(batch.getId() != 0) // it will do update operation
			removeEditSessionID(session);
		
		System.out.println("session scope id " + session.getAttribute("editID") );
		var lvlId = request.getParameter("batch-level");
		System.out.println(lvlId);
		batch.setName(request.getParameter("batch-name").toString());
		batch.setStartDate(LocalDate.parse(request.getParameter("start-date")));
		batch.setLevel(lvlService.findById(Integer.parseInt(lvlId)));
		
		btService.save(batch);
		//response.getWriter().write("batch level id : " + lvlId);
		//response.getWriter().write("batch date : " + batch.getStartDate());

		response.sendRedirect(getServletContext().getContextPath() + "/batches");
	}

	private void goBatchHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		var lvlId = request.getParameter("batch-lvl-id");
		var batches = lvlId == null ? btService.getAllBatch() : btService.findAllByLevelId(Integer.parseInt(lvlId));
		System.out.println("batch home - batches size : " + batches.size());
		request.setAttribute("Batches", batches);
		request.setAttribute("Title", "Batch List");
		request.getServletContext().getRequestDispatcher("/batches.jsp").forward(request, response);
	}

	private void goHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("Title", "Home");
		request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	private void goAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		var title = "Add Batch";
		var batchID = request.getParameter("BatchID");
		var batch = batchID != null ? btService.findById(Integer.parseInt(batchID)) : new Batch();
		System.out.println("GetRequest Batch ID : " + batchID);
		
		if(batch.getId() != 0) //The user will do update operation
		{
			title = "Edit Batch";
			HttpSession session = request.getSession(true);
			session.setAttribute("editID",batchID);
		}

		var Levels = lvlService.getAllLevel();
		request.setAttribute("Title", title);
		request.setAttribute("Batch", batch);
		request.setAttribute("Levels", Levels);
		request.getServletContext().getRequestDispatcher("/batch-add.jsp").forward(request, response);
	}
}
