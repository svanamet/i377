package test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.Unit;
import test.Dao;

public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("do");

		if ("delete".equals(action)) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			try {
				new Dao().DeleteUnit(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		List<Unit> units = new ArrayList<Unit>();
		String filter = request.getParameter("searchString");
		if (filter != null) {
			units = new Dao().Filter(filter);

		} else {
			try {
				units = new Dao().AddAllUnits();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("units", units);
		request.getRequestDispatcher("WEB-INF/jsp/Search.jsp").forward(request,
				response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
