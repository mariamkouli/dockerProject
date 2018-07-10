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
 * Servlet implementation class ProcessSel
 */
@WebServlet("/ProcessSel")
public class ProcessSel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START="<html> <head> <link href='css/style.css'uniform-default rel='stylesheet'></head><body>";
	public static final String HTML_END="</body></html>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessSel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String[] checkboxValues = request.getParameterValues("lesson");
		
		try {
			//1- connect
			Connection conn=null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			 //conn= DriverManager.getConnection("jdbc:mysql://db_service_host:3306/cc_project_database?useLegacyDatetimeCode=false&serverTimezone=UTC ", "auth_service_user", "Auth_service@1397");
			 conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/cc_project_database?useLegacyDatetimeCode=false&serverTimezone=UTC ", "root", "1989");
			 PrintWriter out = response.getWriter();
			 out.println(HTML_START+"<form action='ProcessSel' method='GET' id='searchfield'>"+"<TABLE><TR>\r\n" + 
				 		"<TD>\r\n" );
				 out.println("ID</TD>\r\n"+ 
					 		"<TD>");
				 out.println("Name</TD>\r\n"+  
				 		"</TR>");
			 //2- query
			 Statement st=conn.createStatement();
			 for (int i=0;i<checkboxValues.length; i++) {
				 ResultSet res1=st.executeQuery("SELECT Name FROM Courses WHERE ID ='"+checkboxValues[i]+"'" );
				 res1.next();
				 String l=res1.getString("Name" );
				st.executeUpdate("INSERT INTO slessons (Name,ID, sid )\r\n" + 
				 		"VALUES ('"+l+"','"+checkboxValues[i]+"','" +request.getParameter("studentNumber")+"')");
				  }
			 ResultSet res=st.executeQuery("SELECT * FROM slessons WHERE sid='"+request.getParameter("studentNumber")+"'");
			
			//3- process
			while (res.next()) {
			out.println("<TR><TD>\r\n" +res.getString("ID" )+"</TD>\r\n"+
			"<TD>\r\n" +res.getString("Name" )+"</TD>\r\n");	
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
