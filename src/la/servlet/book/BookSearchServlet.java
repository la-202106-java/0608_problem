package la.servlet.book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.BookBean;
import la.dao.BookDAO;
import la.dao.DAOException;

/**
 * Servlet implementation class BookSearchServlet
 */
@WebServlet("/BookSearchServlet")
public class BookSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		try {
			BookDAO dao = new BookDAO();
			List<BookBean> list = new ArrayList<BookBean>();

			if (action == null || action.length() == 0) {

				gotoPage(request, response, "/3_book/book_search.jsp");

			} else if (action.equals("search_id")) {
				int bookID = 0;
				if (request.getParameter("bookID") != null && request.getParameter("bookID").length() != 0) {
					bookID = Integer.parseInt(request.getParameter("bookID"));
					request.setAttribute("bookID", bookID);
					list.clear();
					BookBean book = dao.findByPrimaryKeyStatus(bookID);
					if (!Objects.isNull(book)) {
						list.add(dao.findByPrimaryKeyStatus(bookID));
					}
				} else {
					list = dao.findAll();
				}

				request.setAttribute("books", list);
				gotoPage(request, response, "/3_book/book_search.jsp");

			} else if (action.equals("search_isbn")) {
				String isbn = "";
				if (request.getParameter("isbn") != null && request.getParameter("isbn").length() != 0) {
					isbn = request.getParameter("isbn");
					request.setAttribute("isbn", isbn);
					list = dao.findByISBNStatus(isbn);
				} else {
					list = dao.findAll();
				}

				request.setAttribute("books", list);
				gotoPage(request, response, "/3_book/book_search.jsp");

			} else if (action.equals("search_title")) {
				String title = "";
				if (request.getParameter("title") != null && request.getParameter("title").length() != 0) {
					title = request.getParameter("title");
					request.setAttribute("title", title);
					list = dao.findByTitle(title);
				} else {
					list = dao.findAll();
				}

				request.setAttribute("books", list);
				gotoPage(request, response, "/3_book/book_search.jsp");

			} else {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
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

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
