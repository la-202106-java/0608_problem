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
import la.bean.ShopItemBean;
import la.dao.DAOException;
import la.dao.ShopItemDAO;

@WebServlet("/ShowItemServlet")
public class ShowItemServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// パラメータの解析
			String action = request.getParameter("action");
			ShopItemDAO dao = new ShopItemDAO();
			// topまたはパラメータなしの場合はトップページを表示
			if (action == null || action.length() == 0 || action.equals("top")) {
				gotoPage(request, response, "/top.jsp");
			} else if (action.equals("list")) {
				int categoryCode = Integer.parseInt(request.getParameter("code"));
				List<ShopItemBean> list = dao.findByCategory(categoryCode);
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("count", list.size());
				request.setAttribute("items", list);
				gotoPage(request, response, "/list.jsp");
			} else if (action.equals("detail")) {
				int code = Integer.parseInt(request.getParameter("code"));
				ShopItemBean item = dao.findByPrimaryKey(code);
				request.setAttribute("item", item);
				gotoPage(request, response, "/item.jsp");
			} else if (action.equals("search")) {
				String keyword = request.getParameter("keyword");
				List<ShopItemBean> list = dao.findByKeyword(keyword);
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("count", list.size());
				request.setAttribute("items", list);
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
			ShopItemDAO dao = new ShopItemDAO();
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
