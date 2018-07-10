package com.lib;

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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProcessSel
 */
@WebServlet("/ProcessSel")
public class ProcessSel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START="<html> <body>";
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
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 30);
		Date dt1= c.getTime();
		String strDate = dateFormat.format(dt1);
 
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
				 out.println("Return Date</TD>\r\n"+  
				 		"</TR>");
			 //2- query
			 Statement st=conn.createStatement();
			     st.executeUpdate("UPDATE books SET Status = '0' WHERE Title ='"+request.getParameter("book")+"'");
				st.executeUpdate("INSERT INTO sbooks (Title,stdnum, returndate )\r\n" + 
				 		"VALUES ('"+request.getParameter("book")+"','"+request.getParameter("studentNumber")+"','" +strDate+"')");
			 ResultSet res=st.executeQuery("SELECT * FROM sbooks WHERE stdnum='"+request.getParameter("studentNumber")+"'");
			
			//3- process
			res.next();
			out.println("<TR><TD>\r\n" +res.getString("Title" )+"</TD>\r\n"+
			"<TD>\r\n" +res.getString("returndate" )+"</TD></TR>\r\n");	
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
