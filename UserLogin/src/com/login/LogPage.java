package com.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogPage
 */
@WebServlet("/LogPage")
public class LogPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START="<html> <body>";
	public static final String HTML_END="</body></html>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		 PrintWriter out = response.getWriter();
		//out.println(HTML_START+"<form action='http://localhost:8080/authentication-service-master/student/login' method='GET' id='searchfield'>"+"<TABLE><TR>\r\n" + 
		 //		"<TD>\r\n" );
		 		out.println(HTML_START+"<form action='ProcessLogin' method='GET' id='searchfield'>"+"<TABLE><TR>\r\n" + 
				 		"<TD>\r\n" );
		 out.println("User name</TD>\r\n"+ 
			 		"<TD>");
		 out.println("<input type='text' name = 'studentNumber' /></TD>\r\n"+  
		 		"</TR>");
		out.println("<TR><TD>password" +"</TD>\r\n"+
		"<TD>\r\n" +"<input type='password' name = 'password' />" +"</TD>\r\n"+"</TR>");	
		out.println("</TABLE>\r\n"+"<input type='submit' value = 'ok' /></form>"+HTML_END);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
