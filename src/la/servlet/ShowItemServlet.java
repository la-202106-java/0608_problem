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
import la.bean.ItemBean2;
import la.dao.DAOException;
import la.dao.ItemDAO2;

@WebServlet("/ShowItemServlet")
public class ShowItemServlet extends HttpServlet {
	private static int limit = 10;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// パラメータの解析
			String action = request.getParameter("action");
			// topまたはパラメータなしの場合はトップページを表示
			if (action == null || action.length() == 0 || action.equals("top")) {
				gotoPage(request, response, "/top.jsp");
			} else if (action.equals("list")) {
				String categoryCode = request.getParameter("code");
				String page = request.getParameter("page");
				String keyword = request.getParameter("keyword");
				int curPage = 1;
				if (page != null && page != "") {
					curPage = Integer.parseInt(page);
				}
				if (categoryCode != null && categoryCode != "") {
					int catCode = Integer.parseInt(categoryCode);
					ItemDAO2 dao = new ItemDAO2();
					List<ItemBean2> list = dao.findByCategory(catCode);
					int number = list.size();
					int pageNum = (int) (number / 10) + 1;
					list = dao.findByCategory(catCode, limit, (curPage - 1) * 10);
					request.setAttribute("category", catCode);
					request.setAttribute("number", number);
					request.setAttribute("pageNum", pageNum);
					request.setAttribute("items", list);
					gotoPage(request, response, "/list.jsp");
				} else if (categoryCode == null || categoryCode == "") {
					ItemDAO2 dao = new ItemDAO2();
					List<ItemBean2> list = dao.findByName(keyword);
					int number = list.size();
					int pageNum = (int) (number / 10) + 1;
					list = dao.findByName(keyword, limit, (curPage - 1) * 10);
					request.setAttribute("number", number);
					request.setAttribute("pageNum", pageNum);
					request.setAttribute("keyword", keyword);
					request.setAttribute("items", list);
					gotoPage(request, response, "/list.jsp");
				} else {
					request.setAttribute("message", "正しく操作してください。");
					gotoPage(request, response, "/errInternal.jsp");
				}
			} else if (action.equals("detail")) {
				int code = Integer.parseInt(request.getParameter("code"));
				ItemDAO2 dao = new ItemDAO2();
				ItemBean2 list = dao.findByPrimaryKey(code);
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("item", list);
				gotoPage(request, response, "/item.jsp");
			} else if (action.equals("search")) {
				String keyword = request.getParameter("keyword");
				String page = request.getParameter("page");
				int curPage = 1;
				if (page != null && page != "") {
					curPage = Integer.parseInt(page);
				}
				if (page != null && page != "") {
					curPage = Integer.parseInt(page);
				}
				ItemDAO2 dao = new ItemDAO2();
				List<ItemBean2> list = dao.findByName(keyword);
				int number = list.size();
				int pageNum = (int) (number / 10) + 1;
				list = dao.findByName(keyword, limit, (curPage - 1) * 10);
				request.setAttribute("number", number);
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("keyword", keyword);
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
			ItemDAO2 dao = new ItemDAO2();
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
