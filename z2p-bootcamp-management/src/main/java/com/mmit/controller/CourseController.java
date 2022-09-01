package com.mmit.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.bytebuddy.implementation.bytecode.Addition;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mmit.model.service.BatchService;
import com.mmit.model.service.CourseService;
import com.mmit.model.service.LevelService;
import com.mmitt.model.entity.Course;


/**
 * Servlet implementation class CourseController
 */
@WebServlet(urlPatterns = {"/courses", "/course-add", "/course-delete"})
public class CourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LevelService lvlService;
	private CourseService csService;
       
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
		csService = new CourseService(emf_obj.createEntityManager());
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
	
    public CourseController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		var action = request.getServletPath();
		if(action.equals("/courses"))
			goHomePage(request, response);
		else if(action.equals("/course-add"))
			goAddPage(request, response);
		else if(action.equals("/course-delete"))
			removeCourse(request, response);
	}

	private void goAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		var course_id = request.getParameter("CourseID");
		var levels = lvlService.getAllLevel();
		var title = "Add Course";
		Course course = new Course();
		if(course_id != null)
		{
			course = csService.findById(Integer.parseInt(course_id)); 
			title = "Edit Course";
		}

		request.setAttribute("Course", course);
		request.setAttribute("Levels", levels);
		request.setAttribute("Title", title);
		getServletContext().getRequestDispatcher("/course-add.jsp").forward(request, response);	
	}

	private void goHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		var lvlId = request.getParameter("course-lvl-id");
		var courses = lvlId == null ? csService.getAllCourse() : csService.findByLevelId(Integer.parseInt(lvlId));
		request.setAttribute("Title", "Course List");
		request.setAttribute("Courses", courses);
		getServletContext().getRequestDispatcher("/courses.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		var action = request.getServletPath();
		if(action.equals("/course-add"))
			saveCourse(request, response);
		//else
			//removeCourse(request, response);
	}

	private void removeCourse(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		var course_id = request.getParameter("course-id");
		System.out.println("Course for deletion : " + course_id);
		
		if(course_id != null && !course_id.isEmpty())
		{
			Course course = csService.findById(Integer.parseInt(course_id));
			csService.remove(course);
		}

		response.sendRedirect(getServletContext().getContextPath() + "/courses");
	}

	private void saveCourse(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		var id = request.getParameter("course-id");
		var course = id.equals("0") ? new Course() : csService.findById(Integer.parseInt(id));
		course.setLevel(lvlService.findById(Integer.parseInt(request.getParameter("level-id"))));
		course.setName(request.getParameter("course-name"));
		course.setContent(request.getParameter("course-content"));
		
		response.getWriter().write(" ID : " + course.getId());
		response.getWriter().write(" Level : " + course.getLevel());
		response.getWriter().write(" Name : " + course.getName());
		response.getWriter().write(" Content : " + course.getContent());
		
		csService.save(course);
		response.sendRedirect(getServletContext().getContextPath() + "/courses");
	}

}
