package com.food;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	public static final String HTML_START="<html><body>";
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
			Connection conn=null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			 //conn= DriverManager.getConnection("jdbc:mysql://db_service_host:3306/cc_project_database?useLegacyDatetimeCode=false&serverTimezone=UTC ", "auth_service_user", "Auth_service@1397");
			 //conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/cc_project_database?useLegacyDatetimeCode=false&serverTimezone=UTC ", "root", "1989");
			 //2- query
			// Statement st=conn.createStatement();
			 //ResultSet res=st.executeQuery("select * from session where id ='"+request.getParameter("studentNumber")+"'");
			 PrintWriter out = response.getWriter();
			 Cookie[] cookies = request.getCookies();
			 if( cookies!=null) {
				 for (int i=0; i<cookies.length;i++)
					 response.addCookie(cookies[i]);
			//3- process
			// if (res==null) {
				// out.println(HTML_START+"<h1>invalid access</h1>"+HTML_END );	 
			// }
			 //else {
			 out.println(HTML_START+"<form action='Select' method='GET' id='searchfield'>" );
			 out.println(" <input type='hidden' name='studentNumber' value='"+request.getParameter("studentNumber")+"'>" );
		     out.println("<input type='submit' value = 'Select Meals' /></form>" );
			 out.println("<form action='Cancel' method='GET' id='searchfield'>" );
			 out.println(" <input type='hidden' name='studentNumber' value='"+request.getParameter("studentNumber")+"'>" );
		     out.println("<input type='submit' value = 'Cancel Reservation' /></form>" );
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
