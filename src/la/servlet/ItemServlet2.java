package la.servlet;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.ItemBean;
import la.dao.DAOException;
import la.dao.ItemDAO2;

@WebServlet("/ItemServlet2")
public class ItemServlet2 extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			// パラメータの解析
			String action = request.getParameter("action");
			HttpSession session = request.getSession();
			// モデルのDAOを生成
			ItemDAO2 dao = new ItemDAO2();
			// パラメータなしの場合は全レコード表示
			if (action == null || action.length() == 0) {
				List<ItemBean> list = dao.findAll();
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem2.jsp");
				// addは追加
			} else if (action.equals("add")) {
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				dao.addItem(name, price);
				// 追加後、全レコード表示
				List<ItemBean> list = dao.findAll();
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem2.jsp");
			}
			// sortはソート
			else if (action.equals("sort")) {
				String key = request.getParameter("key");
				@SuppressWarnings("unchecked")
				List<ItemBean> list = (List<ItemBean>) session.getAttribute("items");
				if (list == null) {
					if (key.equals("price_asc")) {
						list = dao.sortPrice(true);
					} else {
						list = dao.sortPrice(false);
					}
					// Listをリクエストスコープに入れてJSPへフォーワードする
					request.setAttribute("items", list);
				} else if (list != null && key.equals("price_asc")) {
					list.sort(Comparator.comparing(ItemBean::getPrice));
					session.setAttribute("items", list);
				} else {
					list.sort(Comparator.comparing(ItemBean::getPrice).reversed());
					session.setAttribute("items", list);
				}
				gotoPage(request, response, "/showItem2.jsp");
				// searchは検索
			} else if (action.equals("search")) {
				int minprice;
				int maxprice;
				String name;
				if (request.getParameter("minprice") == null || request.getParameter("minprice").length() == 0) {
					minprice = 0;
				} else {
					minprice = Integer.parseInt(request.getParameter("minprice"));
					request.setAttribute("minprice", minprice);
				}
				if (request.getParameter("maxprice") == null || request.getParameter("maxprice").length() == 0) {
					maxprice = 99999;
				} else {
					maxprice = Integer.parseInt(request.getParameter("maxprice"));
					request.setAttribute("maxprice", maxprice);
				}
				if (request.getParameter("name") == null || request.getParameter("name").length() == 0) {
					name = "";
				} else {
					name = request.getParameter("name");
					request.setAttribute("name", name);
				}
				session.setAttribute("name", name);
				session.setAttribute("minprice", minprice);
				session.setAttribute("maxprice", maxprice);

				List<ItemBean> list = dao.findByPrice(minprice, maxprice, name);
				// Listをリクエストスコープに入れてJSPへフォーワードする
				// requestをsessionに変更 step5
				session.setAttribute("items", list);
				gotoPage(request, response, "/showItem2.jsp");
			}
			// deleteは削除
			else if (action.equals("delete")) {
				int code = Integer.parseInt(request.getParameter("code"));
				dao.deleteByPrimaryKey(code);
				// 削除後、全レコード表示
				List<ItemBean> list = dao.findAll();
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem2.jsp");
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

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
