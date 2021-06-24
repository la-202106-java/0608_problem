package la.servlet.book;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.BookBean;
import la.bean.CatalogBean;
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

			if (action == null || action.length() == 0) {
				gotoPage(request, response, "3_book/book_register.jsp");
			} else if (action.equals("regist")) {
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
				// ISBNあるかチェック
				if (!dao2.checkIsbn(isbn)) {
					request.setAttribute("title", title);
					request.setAttribute("isbn", isbn);
					request.setAttribute("arrivalDate", arrivalDate);
					gotoPage(request, response, "/3_book/book_catalog_register.jsp");
				}

				request.setAttribute("title", title);
				request.setAttribute("isbn", isbn);
				request.setAttribute("arrivalDate", arrivalDate);
				gotoPage(request, response, "/3_book/book_register_confirm.jsp");

			} else if (action.equals("confirm")) {
				String title = request.getParameter("title");
				String isbn = request.getParameter("isbn");
				String arrivalDate = request.getParameter("arrival_date");

				int id = dao.addBook(title, isbn, arrivalDate);
				request.setAttribute("id", id);
				request.setAttribute("title", title);
				request.setAttribute("isbn", isbn);
				request.setAttribute("arrivalDate", arrivalDate);
				gotoPage(request, response, "/3_book/book_register_confirmed.jsp");
			} else if (action.equals("back")) {
				String title = request.getParameter("title");
				String isbn = request.getParameter("isbn");
				String arrivalDate = request.getParameter("arrival_date");

				request.setAttribute("title", title);
				request.setAttribute("isbn", isbn);
				request.setAttribute("arrivalDate", arrivalDate);
				gotoPage(request, response, "/3_book/book_register.jsp");

			} else if (action.equals("catalog_regist")) {
				String title = request.getParameter("title");
				String isbn = request.getParameter("isbn");
				request.setAttribute("title", title);
				request.setAttribute("isbn", isbn);
				gotoPage(request, response, "/3_book/catalog_register.jsp");
			} else if (action.equals("bookConfirm")) {
				String title = request.getParameter("title");
				String isbn = request.getParameter("isbn");
				String arrivalDate = request.getParameter("arrivalDate");
				int code = Integer.parseInt(request.getParameter("code"));
				String author = request.getParameter("author");
				String publisher = request.getParameter("publisher");
				String publicationDate = request.getParameter("publicationDate");

				BookBean book = new BookBean(isbn, title, Date.valueOf(arrivalDate));
				CatalogBean catalog = new CatalogBean(isbn, title, code, author, publisher,
						Date.valueOf(publicationDate));
				request.setAttribute("book", book);
				request.setAttribute("catalog", catalog);
				gotoPage(request, response, "/3_book/book_catalog_comfirm.jsp");
			} else if (action.equals("bookRegist")) {
				String title = request.getParameter("title");
				String isbn = request.getParameter("isbn");
				String arrivalDate = request.getParameter("arrivalDate");
				int code = Integer.parseInt(request.getParameter("code"));
				String author = request.getParameter("author");
				String publisher = request.getParameter("publisher");
				String publicationDate = request.getParameter("publicationDate");

				BookDAO bookdao = new BookDAO();
				CatalogDAO catalogdao = new CatalogDAO();

				catalogdao.addBooktoCatalog(title, isbn, code, author, publisher, publicationDate);
				bookdao.addBook(title, isbn, arrivalDate);

				request.setAttribute("message", "資料を登録しました");
				gotoPage(request, response, "/3_book/book_register.jsp");
			} else if (action.equals("bookBack")) {
				String title = request.getParameter("title");
				String isbn = request.getParameter("isbn");
				String arrivalDate = request.getParameter("arrivalDate");
				int code = Integer.parseInt(request.getParameter("code"));
				String author = request.getParameter("author");
				String publisher = request.getParameter("publisher");
				String publicationDate = request.getParameter("publicationDate");

				request.setAttribute("title", title);
				request.setAttribute("isbn", isbn);
				request.setAttribute("arrivalDate", arrivalDate);
				request.setAttribute("code", code);
				request.setAttribute("author", author);
				request.setAttribute("publisher", publisher);
				request.setAttribute("publicationDate", publicationDate);
				gotoPage(request, response, "/3_book/book_catalog_register.jsp");
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
