package com.lib;

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
 * Servlet implementation class ProcessSearch
 */
@WebServlet("/ProcessSearch")
public class ProcessSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START="<html> <body>";
	public static final String HTML_END="</body></html>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessSearch() {
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
			 PrintWriter out = response.getWriter();
			 out.println(HTML_START+"<form action='ProcessSel' method='GET' id='searchfield'>"+"<TABLE><TR>\r\n" + 
				 		"<TD>\r\n" );
				 out.println("Title</TD>\r\n"+ 
					 		"<TD>");
				 out.println("Author</TD>\r\n"+  
				 		"<TD>");
				 out.println("Status</TD>\r\n"+  
					 		"</TR>");
			 //2- query
			 Statement st=conn.createStatement();
				 ResultSet res=st.executeQuery("SELECT * FROM books WHERE Title ='"+request.getParameter("bookname")+"'" );
			//3- process
			while (res.next()) {
			out.println("<TR><TD>\r\n" +res.getString("Title" )+"</TD>\r\n"+
			"<TD>\r\n" +res.getString("Author" )+"</TD>\r\n");
			if (res.getInt("Status") == 1) {
				
				out.println("<TD> available <input type='hidden' NAME='book' value ='"+res.getString("Title" )+"'/></TD>");
				out.println("</TABLE>\r\n"+" <input type='hidden' name='studentNumber' value='"+request.getParameter("studentNumber")+"'/>" );
				out.println("<input type='submit' value = 'reserve' /></form>"+HTML_END);
			}
			else {
				out.println("<TD> rented </TD>");
				out.println("</TABLE>\r\n"+" <input type='hidden' name='studentNumber' value='"+request.getParameter("studentNumber")+"'/>" );
				out.println("</form>"+HTML_END);
			}}
			
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
