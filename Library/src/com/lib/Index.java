package com.lib;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START="<html> <body>";
	public static final String HTML_END="</body></html>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
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
			 PrintWriter out = response.getWriter();
			 Cookie[] cookies = request.getCookies();
			 if( cookies!=null) {
				for (int i=0; i<cookies.length;i++)
				  response.addCookie(cookies[i]);
			 out.println(HTML_START+"<form action='Search' method='GET' id='searchfield'>" );
			 out.println(" <input type='hidden' name='studentNumber' value='"+request.getParameter("studentNumber")+"'>" );
		     out.println("<input type='submit' value = 'Search' /></form>" );
			 out.println("<form action='Reserved' method='GET' id='searchfield'>" );
			 out.println(" <input type='hidden' name='studentNumber' value='"+request.getParameter("studentNumber")+"'>" );
		     out.println("<input type='submit' value = 'Show reserved books' /></form>" );
			out.println(HTML_END);
		}
			else {
				 out.println(HTML_START+"<h1> Access denied </h1>" );	
				 out.println(HTML_END);
			 }
		} catch (Exception e) {
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
