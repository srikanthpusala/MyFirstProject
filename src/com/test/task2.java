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

/**
 * Servlet implementation class task2
 */
@WebServlet("/task2")
public class task2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		String uname=request.getParameter("username");
		String umobile=request.getParameter("mobilenumber");
		String umailid=request.getParameter("email");	
		String uid=request.getParameter("id");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123456");
			PreparedStatement ps=con.prepareStatement("update employees set name=?,mobile=?,email=? where id=?");
			ps.setString(1,uname);
			ps.setString(2, umobile);
			ps.setString(3, umailid);
			ps.setString(4, uid);
			
			int i=ps.executeUpdate();
			
			if (i>0) {
				pw.println("Updated Succesfully");
			RequestDispatcher rd=request.getRequestDispatcher("/update.html");  
 	       	rd.include(request, response);	
				
			} else {
				pw.println("Fail to Update");

			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		pw.close();
		
	}

}
