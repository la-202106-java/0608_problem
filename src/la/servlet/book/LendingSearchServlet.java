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

import la.bean.ReturnedLendingBean;
import la.dao.DAOException;
import la.dao.LendingSearchDAO;

@WebServlet("/LendingSearchServlet")
public class LendingSearchServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			LendingSearchDAO dao = new LendingSearchDAO();
			List<ReturnedLendingBean> list = new ArrayList<ReturnedLendingBean>();

			if (action == null || action.length() == 0) {
				gotoPage(request, response, "/4_lent_return/lending_history.jsp");
			} else if (action.equals("book")) {
				String book_id = request.getParameter("book_id");
				if (book_id == "") {
					list = dao.findAllFromLending();
					request.setAttribute("books", list);
					gotoPage(request, response, "/4_lent_return/lending_history.jsp");
				}
				int bookId = Integer.parseInt(book_id);
				ReturnedLendingBean book = dao.findByBookId(bookId);
				list.add(book);
				request.setAttribute("books", list);
				gotoPage(request, response, "/4_lent_return/lending_history.jsp");
			} else if (action.equals("user")) {
				String user_id = request.getParameter("user_id");
				String check = request.getParameter("check");
				if (user_id == "") {
					list = dao.findAllFromLending();
					request.setAttribute("books", list);
					gotoPage(request, response, "/4_lent_return/lending_history.jsp");
				}

				int userId = Integer.parseInt(user_id);
				if (check != null) {
					list = dao.findAllByUserId(userId);
					request.setAttribute("books", list);
					gotoPage(request, response, "/4_lent_return/lending_history.jsp");
				} else {
					list = dao.findByUserId(userId);
					request.setAttribute("books", list);
					gotoPage(request, response, "/4_lent_return/lending_history.jsp");
				}
			} else if (action.equals("overdue")) {
				list = dao.findOverdue();
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
