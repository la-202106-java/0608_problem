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
import la.dao.ItemDAO;

@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		try {
			request.setCharacterEncoding("UTF-8");
			// パラメータの解析
			String action = request.getParameter("action");
			// モデルのDAOを生成
			ItemDAO dao = new ItemDAO();
			// パラメータなしの場合は全レコード表示
			if (action == null || action.length() == 0) {
				List<ItemBean> list = dao.findAll();
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");
				// addは追加
			} else if (action.equals("add")) {
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				dao.addItem(name, price);
				// 追加後、全レコード表示
				List<ItemBean> list = dao.findAll();
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");
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
				} else if (list != null && key.equals("price_asc")) {
					list.sort(Comparator.comparingInt(ItemBean::getPrice));
				} else {
					list.sort(Comparator.comparingInt(ItemBean::getPrice).reversed());
				}

				// Listをリクエストスコープに入れてJSPへフォーワードする
				session.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");
			}

			//***************************STEP**************************
			// searchは検索
			else if (action.equals("search")) {
				String pname = request.getParameter("name");
				String minPrice = request.getParameter("minPrice");
				String maxPrice = request.getParameter("maxPrice");
				int min = 0;
				int max = 0;

				if (!minPrice.equals("")) {
					min = Integer.parseInt(minPrice);
				}
				if (!maxPrice.equals("")) {
					max = Integer.parseInt(maxPrice);
				}
				List<ItemBean> list = dao.findByPrice(pname, min, max);
				// Listをリクエストスコープに入れてJSPへフォーワードする

				//		ArrayList<String> log = (ArrayList<String>) session.getAttribute("");

				request.setAttribute("name", pname);
				request.setAttribute("minPrice", minPrice);
				request.setAttribute("maxPrice", maxPrice);

				session.setAttribute("items", list);

				gotoPage(request, response, "/showItem.jsp");
			}

			// deleteは削除
			else if (action.equals("delete")) {
				int code = Integer.parseInt(request.getParameter("code"));
				dao.deleteByPrimaryKey(code);
				// 削除後、全レコード表示
				List<ItemBean> list = dao.findAll();
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");

			} else if (action.equals("fix")) {
				int code = Integer.parseInt(request.getParameter("code"));
				int price = Integer.parseInt(request.getParameter("price"));
				dao.fixPrice(code, price);
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");

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
