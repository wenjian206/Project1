package com.training.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.training.ers.dao.LoginDAO;
import com.training.ers.dao.LoginDAOImpl;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private static Logger logger = Logger.getLogger(LoginController.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String uname = request.getParameter("username");
		String pwd = request.getParameter("password");
		
		HttpSession session = request.getSession();
		session.setAttribute("username", uname);
		
		LoginDAO loginDAO = new LoginDAOImpl();
		boolean result = loginDAO.validate(uname, pwd);
		
		out.println("<html>");
		out.println("<body text=\"white\" background=\"relogo.jpg\">");
		if(result) {
			session.setAttribute("message", "Valid User");
			out.println("<h1>Welcome "+uname);		
			//out.println("<h1><a href=welcome.html>Proceed to Home page</a>");
			RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
			dispatcher.include(request, response);
		}
		else {
			session.setAttribute("message", "Invalid User");
			out.println("Your Username/Password is incorrect, please <a href=login.html>Login</a> again");
		}		
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
