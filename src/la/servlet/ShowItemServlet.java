package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.CategoryBean;
import la.bean.ShoppingItemBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

@WebServlet("/ShowItemServlet")
public class ShowItemServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// パラメータの解析
			String action = request.getParameter("action");
			// topまたはパラメータなしの場合はトップページを表示
			if (action == null || action.length() == 0 || action.equals("top")) {
				gotoPage(request, response, "/top.jsp");
<<<<<<< HEAD

			} else if (action.equals("list")) {
				int categoryCode = Integer.parseInt(request.getParameter("code"));
				ItemDAO dao = new ItemDAO();
				List<ShoppingItemBean> list = dao.findByCategory(categoryCode);
				int count = dao.countByCategory(categoryCode);
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("items", list);
				request.setAttribute("length", count);
				//request.setAttribute("length", list.size());
				gotoPage(request, response, "/list.jsp");

			} else if (action.equals("detail")) {
				int code = Integer.parseInt(request.getParameter("code"));
				ItemDAO dao = new ItemDAO();
				ShoppingItemBean bean = dao.findByPrimaryKey(code);
				request.setAttribute("item", bean);
				gotoPage(request, response, "/item.jsp");

			} else if (action.equals("search")) {
				String keyword = request.getParameter("keyword");
				ItemDAO dao = new ItemDAO();
				List<ShoppingItemBean> list = dao.findByName(keyword);
				int count = dao.countByName(keyword);
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("items", list);
				request.setAttribute("length", count);
				//request.setAttribute("length", list.size());
=======
			} else if (action.equals("list")) {
				int categoryCode = Integer.parseInt(request.getParameter("code"));
				ItemDAO dao = new ItemDAO();
				List<ShoppingItemBean> list = dao.findByCategory(categoryCode);
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("items", list);
				gotoPage(request, response, "/list.jsp");

			} else if (action.equals("detail")) {
				int code = Integer.parseInt(request.getParameter("code"));
				ItemDAO dao = new ItemDAO();
				ShoppingItemBean bean = dao.findByPrimaryKey(code);
				request.setAttribute("item", bean);
				gotoPage(request, response, "/item.jsp");

			} else if (action.equals("search")) {
				String keyword = request.getParameter("keyword");
				ItemDAO dao = new ItemDAO();
				List<ShoppingItemBean> list = dao.findByName(keyword);
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("items", list);
>>>>>>> branch '20210615_Sato' of https://github.com/la-202106-java/0608_problem.git
				gotoPage(request, response, "/list.jsp");

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

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	public void init() throws ServletException {
		try {
			// カテゴリ一覧は最初にアプリケーションスコープへ入れる
			ItemDAO dao = new ItemDAO();
			List<CategoryBean> list = dao.findAllCategory();
			getServletContext().setAttribute("categories", list);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
