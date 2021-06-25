package la.servlet.book;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.BookBean;
import la.dao.BookDAO;
import la.dao.DAOException;
import la.dao.DiscardedBookDAO;
import la.dao.LendingDAO;

/**
 * Servlet implementation class BookDeleteServlet
 */
@WebServlet("/BookDeleteServlet")
public class BookDeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		int id = Integer.parseInt(request.getParameter("id"));

		try {
			BookDAO dao = new BookDAO();
			DiscardedBookDAO disdao = new DiscardedBookDAO();
			if (action == null || action.length() == 0) {

				BookBean book = dao.findByPrimaryKey(id);
				request.setAttribute("book", book);
				gotoPage(request, response, "/3_book/book_deleted_confirm.jsp");

			} else if (action.equals("delete")) {
				LendingDAO ldao = new LendingDAO();
				if (ldao.isLending(id)) {
					request.setAttribute("message", "貸出中の資料は削除できません");
					gotoPage(request, response, "/3_book/book_search.jsp");
				}
				int disrow = disdao.addDiscardedBook(dao.findByPrimaryKey(id));
				int row = dao.deleteByPrimaryKey(id);
				request.setAttribute("message", row + "件削除しました。");
				gotoPage(request, response, "/3_book/book_search.jsp");

			}

		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);

	}

}
