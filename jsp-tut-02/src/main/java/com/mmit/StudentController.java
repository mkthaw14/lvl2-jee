package com.mmit;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mmit.entity.Batch;
import com.mmit.entity.Student;

/**
 * Servlet implementation class StudentController
 */

@WebServlet(urlPatterns = {"/students", "/student-add", "/add-student" , "/batches" , "/add-batch" , "/home"})
public class StudentController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private ServletContext context;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentController()
	{
		super();
		// TODO Auto-generated constructor stub
    	System.out.println("This is contructor method");
	}
	
    @Override
    public void init(ServletConfig config) throws ServletException
    {
    	// TODO Auto-generated method stub
    	super.init(config);
    	context = config.getServletContext();
    	context.getAttribute("");
    	List<Batch> batches = (ArrayList)context.getAttribute("batches");
    	if(batches == null)
    	{
    		batches = new ArrayList<Batch>();
    	}
    	
    	batches.add(new Batch("Batch-1", LocalDate.parse("2022-06-15")));
    	batches.add(new Batch("Batch-2", LocalDate.parse("2022-12-15")));
    	
    	context.setAttribute("batches", batches);
    	System.out.println("This is init method");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
    	//PrintWriter writer = response.getWriter();
    	System.out.println("Path : " + request.getServletPath());
    	String path = request.getServletPath();
    	String page = "";
    	String title = "";
    	
    	if(path.equals("/students"))
    	{
    		title = "Students";
    		page = "/students.jsp";
    	}
    	else if(path.equals("/student-add"))
    	{
    		title = "Student|Add";
    		page = "/student-add.jsp";
    	}
    	else if(path.equals("/batches"))
    	{
    		title = "Batch";
    		page = "/batches.jsp";
    	}
    	else 
    	{
    		title = "Home";
    		page = "/index.jsp";
    	}
    	
    	request.setAttribute("title", title);
		getServletContext().getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String path = request.getServletPath();
		
		System.out.println("Context : " + context);
		System.out.println(path);
		if(path.equals("/add-student"))
		{
			addStudent(request, response);
		}
		else if(path.equals("/add-batch"))
		{
			addBatch(request, response);
		}
	}
	
	private void addBatch(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String batchName = request.getParameter("batch-name");
		String startDate = request.getParameter("start-date");
		
		Batch batch = new Batch(batchName, LocalDate.parse(startDate));
		List<Batch> list = (ArrayList<Batch>)context.getAttribute("batches");
		if(list == null)
			list = new ArrayList<Batch>();
		
		list.add(batch);
		context.setAttribute("batches", list);
		
		response.sendRedirect(request.getContextPath() + "/batches");
	}

	void addStudent(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		String name = req.getParameter("std-name");
		String phone = req.getParameter("std-phone");
		String email = req.getParameter("std-email");
		String batchName = req.getParameter("batch-name");
		String old_std = req.getParameter("std-old");
		
		Student std = new Student();
		std.setName(name);
		std.setPhone(phone);
		std.setEmail(email);
		std.setBatchName(batchName);
		std.setRegisteredDate(LocalDate.now());
		std.setOld_student(old_std != null ? true : false);
		
		std.getName();
		std.isOld_student();
		
		HttpSession session = req.getSession(true);
		List<Student> list = (List<Student>) session.getAttribute("students");
		
		if(list == null)
			list = new ArrayList<Student>();
		
		list.add(std);
		
		session.setAttribute("students", list);
		res.sendRedirect(req.getContextPath() + "/students"); // This string "students" is controller router name
	}

}
