package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ex3.SetupDao;


public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("do");
		if ("clear_data".equals(action)) {
			new Dao().DeleteAllUnits();
	        response.sendRedirect("Search");

		} else if ("insert_data".equals(action)) {
			new SetupDao().createExample();
	        response.sendRedirect("Search");

		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
