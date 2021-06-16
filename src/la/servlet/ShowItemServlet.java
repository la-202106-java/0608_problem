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
import la.bean.SampleItemBean;
import la.dao.DAOException;
import la.dao.SampleItemDAO;

@WebServlet("/ShowItemServlet")
public class ShowItemServlet extends HttpServlet {
	private static int limit = 10;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// パラメータの解析
			String action = request.getParameter("action");

			SampleItemDAO dao = new SampleItemDAO();
			// topまたはパラメータなしの場合はトップページを表示
			if (action == null || action.length() == 0 || action.equals("top")) {
				gotoPage(request, response, "/top.jsp");
			} else if (action.equals("list")) {
				//list
				String code = request.getParameter("code");
				String keyword = request.getParameter("keyword");
				String page = request.getParameter("page");
				int currentPage = 1;
				if (page != null && page.length() != 0) {
					currentPage = Integer.parseInt(page);
				}
				if (code != null && code.length() != 0) {
					int categoryCode = Integer.parseInt(code);
					int itemsNum = dao.countByCategory(categoryCode);
					List<SampleItemBean> list = dao.findByCategory(categoryCode, limit, (currentPage - 1) * 10);
					// Listをリクエストスコープに入れてJSPへフォーワードする
					request.setAttribute("items", list);
					request.setAttribute("items_number", itemsNum);
					request.setAttribute("page_max", Math.ceil(itemsNum / (double) limit));
					request.setAttribute("category_code", categoryCode);
					gotoPage(request, response, "/list.jsp");
				} else if (keyword != null && keyword.length() != 0) {
					int itemsNum = dao.countByName(keyword);
					List<SampleItemBean> list = dao.findByName(keyword, limit, (currentPage - 1) * 10);
					request.setAttribute("items", list);
					request.setAttribute("items_number", itemsNum);
					request.setAttribute("page_max", Math.ceil(itemsNum / (double) limit));
					request.setAttribute("keyword", keyword);
					gotoPage(request, response, "/list.jsp");
				} else {
					request.setAttribute("message", "正しく操作してください。");
					gotoPage(request, response, "/errInternal.jsp");
				}
			} else if (action.equals("detail")) {
				//商品詳細
				int code = Integer.parseInt(request.getParameter("code"));
				SampleItemBean item = dao.findByPrimaryKey(code);
				request.setAttribute("item", item);
				gotoPage(request, response, "/item.jsp");
			} else if (action.equals("search")) {
				//検索
				String keyword = request.getParameter("keyword");
				String page = request.getParameter("page");
				int currentPage = 1;
				if (page != null && page.length() != 0) {
					currentPage = Integer.parseInt(page);
				}
				int itemsNum = dao.countByName(keyword);
				List<SampleItemBean> list = dao.findByName(keyword, limit, (currentPage - 1) * 10);
				request.setAttribute("items", list);
				request.setAttribute("items_number", itemsNum);
				request.setAttribute("page_max", Math.ceil(itemsNum / (double) limit));
				request.setAttribute("keyword", keyword);
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
			SampleItemDAO dao = new SampleItemDAO();
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
