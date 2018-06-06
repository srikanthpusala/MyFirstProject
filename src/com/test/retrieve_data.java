package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.font.CreatedFontTracker;

@WebServlet("/retrieve_data")
public class retrieve_data extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
      public retrieve_data() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123456");
			Statement stmt=con.createStatement();
			 ResultSet rs=stmt.executeQuery("select * from employees");
			 pw.println("<table border='1'><tr><td>ID</td><td>Name</td><td>E-Mail</td></tr>");
	        while(rs.next())
	        {
	        	pw.println("<tr>");
	        	pw.println("<td>"+rs.getInt(1)+"</td>");
	        	pw.println("<td>"+rs.getString(2)+"</td>");
	        	pw.println("<td>"+rs.getString(3)+"</td>");
	        	pw.println("</tr>");
	        	
	        }
	        pw.println("</table>");
	        RequestDispatcher rd=request.getRequestDispatcher("/retrieve.html");  
	        rd.include(request, response);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		pw.close();
	}

}
