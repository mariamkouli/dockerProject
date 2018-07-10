package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProcessLogin
 */
@WebServlet("/ProcessLogin")
public class ProcessLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START="<html> <body>";
	public static final String HTML_END="</body></html>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			//1- connect
			Connection conn=null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			 //conn= DriverManager.getConnection("jdbc:mysql://db_service_host:3306/cc_project_database?useLegacyDatetimeCode=false&serverTimezone=UTC ", "auth_service_user", "Auth_service@1397");
			 conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/cc_project_database?useLegacyDatetimeCode=false&serverTimezone=UTC ", "root", "1989");
			 //2- query
			 Statement st=conn.createStatement();
			 ResultSet res=st.executeQuery("select * from students where student_number ='"+request.getParameter("studentNumber")+
					 "' And password ='"+ request.getParameter("password")+"'");
			 PrintWriter out = response.getWriter();
			//3- process
			 if (!res.next()) {
				 out.println(HTML_START+"<h1>invalid access</h1>"+HTML_END );	 
			 }
			 else {
		     HttpSession session = request.getSession(true);
		     session.setAttribute("sessionTest","Session Entry One");
		     ServletContext context = getServletContext();
		     context.setAttribute("servletContextTest",
		     "Servlet Context Entry One");
		     Cookie c1 = new Cookie("cookieTest1", "Cookie One");
		     c1.setMaxAge(3600); // One hour
		     response.addCookie(c1); // Default path
		     Cookie c2 = new Cookie("cookieTest2", "Cookie Two");
		     c2.setMaxAge(3600); // One hour
		     c2.setPath("/"); // Explicit path: all URLs
		     response.addCookie(c2);
			 
		     
		    // st.executeUpdate("INSERT INTO session (id,std)\r\n" + 
		     //		"VALUES ('"+request.getParameter("studentNumber")+"', '"+request.getParameter("studentNumber")
		     	//	+"');");
			 out.println(HTML_START+"<form action='http://localhost:8080/Educational/Index' method='GET' id='searchfield'>" );
			 out.println(" <input type='hidden' name='studentNumber' value='"+request.getParameter("studentNumber")+"'>" );
		     out.println("<input type='submit' value = 'Educational' /></form>" );
			 out.println("<form action='http://localhost:8080/Food/Index' method='GET' id='searchfield'>" );
			 out.println(" <input type='hidden' name='studentNumber' value='"+request.getParameter("studentNumber")+"'>" );
			 out.println("<input type='submit' value = 'Meals' /></form>" );
			 out.println("<form action='http://localhost:8080/Library/Index' method='GET' id='searchfield'>" );
			 out.println(" <input type='hidden' name='studentNumber' value='"+request.getParameter("studentNumber")+"'>" );
			 out.println("<input type='submit' value = 'Library' /></form>" );
			out.println(HTML_END);
		} }catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
