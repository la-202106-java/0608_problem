package la.servlet.book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.LendingBean;
import la.dao.DAOException;
import la.dao.LendingSearchDAO;

@WebServlet("/LendingSearchServlet")
public class LendingSearchServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			LendingSearchDAO dao = new LendingSearchDAO();
			List<LendingBean> list = new ArrayList<LendingBean>();

			if (action == null || action.length() == 0) {
				gotoPage(request, response, "/4_lent_return/lending_history.jsp");
			} else if (action.equals("book")) {

			} else if (action.equals("user")) {

			} else if (action.equals("overdue")) {
				list = dao.findAllFromLending();
				request.setAttribute("books", list);
				gotoPage(request, response, "/4_lent_return/lending_history.jsp");
			}

		} catch (DAOException e) {

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
