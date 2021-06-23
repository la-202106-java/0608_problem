package la.servlet.book;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.dao.BookDAO;
import la.dao.CatalogDAO;
import la.dao.DAOException;

@WebServlet("/BookRegistServlet")
public class BookRegistServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");
			BookDAO dao = new BookDAO();
			CatalogDAO dao2 = new CatalogDAO();

			if (action.equals("regist")) {
				String title = request.getParameter("title");
				String isbn = request.getParameter("isbn");
				String arrivalDate = request.getParameter("arrival_date");

				if (title == "" || isbn == "" || arrivalDate == "") {
					request.setAttribute("title", title);
					request.setAttribute("isbn", isbn);
					request.setAttribute("arrivalDate", arrivalDate);
					request.setAttribute("message", "すべての項目を入力してください。");
					gotoPage(request, response, "/3_book/book_register.jsp");
				}
				if (!dao2.checkIsbn(isbn)) {
					request.setAttribute("title", title);
					request.setAttribute("isbn", isbn);
					gotoPage(request, response, "/3_book/book_register_confirm_catalog.jsp");
				}

				request.setAttribute("title", title);
				request.setAttribute("isbn", isbn);
				request.setAttribute("arrivalDate", arrivalDate);
				gotoPage(request, response, "/3_book/book_register_confirm.jsp");

			} else if (action.equals("confirm")) {
				String title = request.getParameter("title");
				String isbn = request.getParameter("isbn");
				String arrivalDate = request.getParameter("arrival_date");

				dao.addBook(title, isbn, arrivalDate);
				request.setAttribute("title", title);
				request.setAttribute("isbn", isbn);
				request.setAttribute("arrivalDate", arrivalDate);
				gotoPage(request, response, "/3_book/book_register_confirmed.jsp");
			} else if (action.equals("catalog_regist")) {
				String title = request.getParameter("title");
				String isbn = request.getParameter("isbn");
				request.setAttribute("title", title);
				request.setAttribute("isbn", isbn);
				gotoPage(request, response, "/3_book/catalog_register.jsp");
			}

		} catch (DAOException e) {

		}

	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
