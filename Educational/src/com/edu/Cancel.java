package com.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Cancel
 */
@WebServlet("/Cancel")
public class Cancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START="<html> <body>";
	public static final String HTML_END="</body></html>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cancel() {
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
			 ResultSet res=st.executeQuery("select * from slessons where sid = '"+request.getParameter("studentNumber")+"'");
			//3- process
			 PrintWriter out = response.getWriter();	
			 out.println(HTML_START+"<form action='ProcessCancel' method='GET' id='searchfield'>"+"<TABLE><TR>\r\n" + 
			 		"<TD>\r\n" );
			 out.println("ID</TD>\r\n"+ 
				 		"<TD>");
			 out.println("Name</TD>\r\n"+  
			 		"</TR>");
			while (res.next()) {
			out.println("<TR><TD>\r\n" +res.getString("ID" )+"</TD>\r\n"+
			"<TD>\r\n" +res.getString("Name" )+"</TD>\r\n"
			+"<TD>\r\n" +"<input type='checkbox' NAME='lesson' value =' "+res.getString("ID" )+"'/>drop"+"</TD>\r\n</TR>");	
			}
			out.println(" <input type='hidden' name='studentNumber' value='"+request.getParameter("studentNumber")+"'/>" );
			out.println("</TABLE>\r\n"+"<input type='submit' value = 'ok' /></form>"+HTML_END);
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
