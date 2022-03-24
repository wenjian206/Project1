package com.training.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.training.ers.dao.LoginDAO;
import com.training.ers.dao.LoginDAOImpl;
import com.training.ers.model.User;

/**
 * Servlet implementation class SignUpController
 */
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String notification[] = request.getParameterValues("notification");
		String qualification = request.getParameter("qualification");
		String finalNotification = "";
		
		for(String temp:notification) {
			finalNotification += temp +":";
		}
		
		User user = new User(-1, username, password, gender, finalNotification, qualification);
		
		LoginDAO loginDAO = new LoginDAOImpl();
		loginDAO.register(user);
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		out.println("<html>");
		out.println("<body text=\"white\" background=\"relogo.jpg\">");
		out.println("Welcome "+username);
		out.println("<br/>");
		out.println("<h1>Your are registerd successfully and your password is :"+password);
		out.println("<h1>Please <a href=login.html>Login</a>");
		out.println("</body></html>");	
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
