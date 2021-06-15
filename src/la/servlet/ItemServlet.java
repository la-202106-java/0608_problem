package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.ItemBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

/**
 * Servlet implementation class ItemServlet
 */
@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//モデルを使って全商品を取得する
		try {
			request.setCharacterEncoding("UTF-8");
			//パラメータの解析
			String action = request.getParameter("action");
			ItemDAO dao = new ItemDAO();

			if (action == null || action.length() == 0) {
				List<ItemBean> list = dao.findAll();
				//Listをリクエストスコープに入れてJSPへフォワードする
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");
			} else if (action.equals("sort")) {
				String key = request.getParameter("key");
				List<ItemBean> list;
				if (key.equals("price_asc")) {
					list = dao.sortPrice(true);
				} else {
					list = dao.sortPrice(false);
				}
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");
			} else if (action.equals("add")) {
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				dao.addItem(name, price);
				// 追加後、全レコード表示
				List<ItemBean> list = dao.findAll();
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");
			} else if (action.equals("search")) {
				String getMinPrice = request.getParameter("minPrice");
				String getMaxPrice = request.getParameter("maxPrice");
				List<ItemBean> list;
				if (getMaxPrice == "") {
					int minPrice = Integer.parseInt(getMinPrice);
					list = dao.findByMinPrice(minPrice);
				} else if (getMinPrice == "") {
					int maxPrice = Integer.parseInt(getMaxPrice);
					list = dao.findByMaxPrice(maxPrice);
				} else {
					int minPrice = Integer.parseInt(getMinPrice);
					int maxPrice = Integer.parseInt(getMaxPrice);
					list = dao.findByPrice(minPrice, maxPrice);
				}
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");

			} else if (action.equals("delete")) {
				int code = Integer.parseInt(request.getParameter("code"));
				dao.deleteByPrimaryKey(code);
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");
			}

		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
			rd.forward(request, response);
		}
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
