package com.food;

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
 * Servlet implementation class ProcessCancel
 */
@WebServlet("/ProcessCancel")
public class ProcessCancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START="<html> <head> <link rel='stylesheet' href='../../WebContent/uniform-default.css' type='text/css' media='screen'></head><body>";
	public static final String HTML_END="</body></html>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessCancel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	      String[] checkboxValues = request.getParameterValues("meal");
			
			try {
				//1- connect
				Connection conn=null;
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				 //conn= DriverManager.getConnection("jdbc:mysql://db_service_host:3306/cc_project_database?useLegacyDatetimeCode=false&serverTimezone=UTC ", "auth_service_user", "Auth_service@1397");
				 conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/cc_project_database?useLegacyDatetimeCode=false&serverTimezone=UTC ", "root", "1989");
				 PrintWriter out = response.getWriter();
				 out.println(HTML_START+"<h1>remaining days</h1><form action='ProcessCancel' method='GET' id='searchfield'>"+"<TABLE>" );
				 //2- query
				 Statement st=conn.createStatement();
				 for (int i=0;i<checkboxValues.length; i++) {
					st.executeUpdate("DELETE FROM selectedmeals WHERE studentnum='"+request.getParameter("studentNumber")+"' AND day='"+checkboxValues[i]+"'");
					  }
				 ResultSet res=st.executeQuery("SELECT * FROM selectedmeals WHERE studentnum='"+request.getParameter("studentNumber")+"'");
				
				//3- process
				while (res.next()) {
				out.println("<TR>\r\n" + 
				 		"<TD>\r\n"+res.getString("day" )+"</TD>\r\n"+
				"<TD>\r\n" +res.getString("meal" )+"</TD></TR>\r\n");	
				}
				out.println("</TABLE>\r\n"+"</form>"+HTML_END);
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
