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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Select
 */
@WebServlet("/Select")
public class Select extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START="<html><body>";
	public static final String HTML_END="</body></html>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Select() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt);
		c.add(Calendar.DATE, 1);
		int dayOfWeek1 = c.get(Calendar.DAY_OF_WEEK);
		//c.add(Calendar.DATE, 1);
		//Date dt1= c.getTime();
		//c.add(Calendar.DATE, 1);
		//Date dt2= c.getTime();
		//c.add(Calendar.DATE, 1);
		//Date dt3= c.getTime();
		int i = 0;
		int j = 0;
		int k = 0;
		switch (dayOfWeek1) {
		case 2 :
			i=2;
			j=3;
			k=4;
			break;  
		case 3 :
			i=3;
			j=4;
			k=5;
			break;  
		case 4 :
			i=4;
			j=5;
			k=1;
			break;  
		case 5 :
			i=5;
			j=1;
			k=2;
			break;  
		case 6 :
			i=1;
			j=2;
			k=3;
			break;  
		case 7 :
			i=1;
			j=2;
			k=3;
			break;  
		case 1 :
			i=1;
			j=2;
			k=3;
			break;  
		}
		

		
	try {
		//1- connect
		Connection conn=null;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		 conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/cc_project_database?useLegacyDatetimeCode=false&serverTimezone=UTC ", "root", "1989");
		 //2- query
		 Statement st=conn.createStatement();
		 ResultSet res=st.executeQuery("select * from foods where ID='"+i+"'");
		 res.next();
		//3- process
		 PrintWriter out = response.getWriter();	
		 out.println(HTML_START+"<form action='ProcessSelect' method='GET' id='searchfield'>"+"<TABLE><TR>\r\n" + 
		 		"<TD>\r\n" );
		 out.println("<input type='checkbox' NAME='meal' value ='"+res.getString("ID" )+"'/>"+"</TD>\r\n<TD>");
		 
		 out.println(res.getString("DayOfWeek" )); 
		 out.println("</TD>\r\n" + 
		 		"<TD>");
			out.println(res.getString("Name" )+"</TD>\r\n");
			out.println("<TD>\r\n" +res.getString("Meal" )+"</TD>\r\n");
			out.println("<TD>\r\n" +res.getString("Price" )+"</TD></TR><TR><TD>\r\n");
			 ResultSet res2=st.executeQuery("select * from foods where ID='"+j+"'");
			 res2.next();
			out.println("<input type='checkbox' NAME='meal' value ='"+res2.getString("ID" )+"'/>"+"</TD>\r\n<TD>");
		 out.println(res2.getString("DayOfWeek" ));
		 out.println("</TD>\r\n"+ 
			 		"<TD>");
		 out.println(res2.getString("Name" )+"</TD>\r\n");
			out.println("<TD>\r\n" +res2.getString("Meal" )+"</TD>\r\n");
			out.println("<TD>\r\n" +res2.getString("Price" )+"</TD></TR><TR><TD>\r\n");
			 ResultSet res3=st.executeQuery("select * from foods where ID='"+k+"'");
			 res3.next();
			out.println("<input type='checkbox' NAME='meal' value ='"+res3.getString("ID" )+"'/>"+"</TD>\r\n<TD>");
		 out.println(res3.getString("DayOfWeek" ));
		 out.println("</TD><TD>");
		 out.println(res3.getString("Name" )+"</TD>\r\n");
			out.println("<TD>\r\n" +res3.getString("Meal" )+"</TD>\r\n");
			out.println("<TD>\r\n" +res3.getString("Price" )+"</TD></TR>\r\n");
		out.println("</TABLE>\r\n"+" <input type='hidden' name='studentNumber' value='"+request.getParameter("studentNumber")+"'/>" );
			out.println("<input type='submit' value = 'Select' />"+"</form>"+HTML_END);
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
