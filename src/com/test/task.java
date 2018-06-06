package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/task")
public class task extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		String uname=request.getParameter("username");
		String umobile=request.getParameter("mobilenumber");
		String umailid=request.getParameter("emailid");		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123456");
			PreparedStatement ps=con.prepareStatement("insert into employees values(s1.nextval,?,?,?)");
			ps.setString(1,uname);
			ps.setString(2, umobile);
			ps.setString(3, umailid);
			
			int i=ps.executeUpdate();
			
			if (i>0) {
				pw.println("Data Inserted Succesfully");
				RequestDispatcher rd=request.getRequestDispatcher("/insert.html");  
 	            rd.include(request, response);
				
			} else {
				pw.println("Faile to Insert Data");

			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		pw.close();
	}

}
