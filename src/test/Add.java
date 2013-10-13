package test;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.Unit;
import test.Dao;

public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/Add.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Unit unit = new Unit();
		unit.setNimi(request.getParameter("name"));
		unit.setKood(request.getParameter("code"));

		try {
			new Dao().AddUnit(unit);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.sendRedirect("Search");
	}

}
