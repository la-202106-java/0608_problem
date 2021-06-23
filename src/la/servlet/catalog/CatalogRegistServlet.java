package la.servlet.catalog;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.dao.CatalogDAO;
import la.dao.DAOException;

/**
 * Servlet implementation class CatalogRegistServlet
 */
@WebServlet("/CatalogRegistServlet")
public class CatalogRegistServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");
			CatalogDAO dao = new CatalogDAO();

			if (action.equals("regist")) {
				String title = request.getParameter("title");
				String isbn = request.getParameter("isbn");
				String code = request.getParameter("code");
				String author = request.getParameter("author");
				String publisher = request.getParameter("publisher");
				String publicationDate = request.getParameter("publication_date");
				if (title == "" || isbn == "" || code == "" || author == "" || publisher == ""
						|| publicationDate == "") {
					gotoPage(request, response, "/3_book/catalog_register.jsp");
				}

				request.setAttribute("title", title);
				request.setAttribute("isbn", isbn);
				request.setAttribute("code", code);
				request.setAttribute("author", author);
				request.setAttribute("publisher", publisher);
				request.setAttribute("publicationDate", publicationDate);
				gotoPage(request, response, "/3_book/catalog_register_comfirm.jsp");

			} else if (action.equals("confirm")) {
				String title = request.getParameter("title");
				String isbn = request.getParameter("isbn");
				int code = Integer.parseInt(request.getParameter("code"));
				String author = request.getParameter("author");
				String publisher = request.getParameter("publisher");
				String publicationDate = request.getParameter("publicationDate");

				dao.addBooktoCatalog(isbn, title, code, author, publisher, publicationDate);
				gotoPage(request, response, "/3_book/register_confirm.jsp");
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
