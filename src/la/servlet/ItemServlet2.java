package la.servlet;

import java.io.IOException;
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
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		try {
			request.setCharacterEncoding("UTF-8");

			String action = request.getParameter("action");

			ItemDAO2 dao = new ItemDAO2();

			if (action == null || action.length() == 0) {
				List<ItemBean> list = dao.findAll();

				request.setAttribute("items", list);

				gotoPage(request, response, "/showItem2.jsp");
			} else if (action.equals("add")) {
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				dao.addItem(name, price);

				List<ItemBean> list = dao.findAll();

				request.setAttribute("items", list);

				gotoPage(request, response, "/showItem2.jsp");

			} else if (action.equals("sort")) {
				String key = request.getParameter("key");
				String productName = (String) session.getAttribute("productName");
				String minPrice = (String) session.getAttribute("minPrice");
				String maxPrice = (String) session.getAttribute("maxPrice");

				List<ItemBean> list;
				if (key.equals("price_asc")) {
					list = dao.sortPrice(true, productName, minPrice, maxPrice);
				} else {
					list = dao.sortPrice(false, productName, minPrice, maxPrice);
				}
				request.setAttribute("items", list);

				gotoPage(request, response, "/showItem2.jsp");

			} else if (action.equals("search")) {
				List<ItemBean> list;

				String productName = request.getParameter("productName");
				String minPrice = request.getParameter("minPrice");
				String maxPrice = request.getParameter("maxPrice");

				// 商品名が与えられるか与えられないかで大別
				// 商品名（一部）と価格を指定した検索には未対応
				// if文のネストを解消したい
				// itemDAO2側で処理をするように変更したい
				// findByNamePrice()メソッドを定義し、条件に合わせてsql文の文字列を連結していく形にする
				/*				if (productName == null || productName.length() == 0) {
									if (minPrice == null || minPrice.length() == 0) {
										if (maxPrice == null || maxPrice.length() == 0) {
											list = dao.findAll();
										} else {
											list = dao.findByPrice(0, Integer.parseInt(maxPrice));
										}
									} else {
										if (maxPrice == null || maxPrice.length() == 0) {
											list = dao.findByPrice(Integer.parseInt(minPrice));
										} else {
											list = dao.findByPrice(Integer.parseInt(minPrice), Integer.parseInt(maxPrice));
										}
									}
								} else {
									list = dao.findByName(productName);
				
								}*/

				list = dao.findByNamePrice(productName, minPrice, maxPrice);

				// 検索ワードを保持するための処理
				session.setAttribute("maxPrice", maxPrice);
				session.setAttribute("minPrice", minPrice);
				session.setAttribute("productName", productName);

				request.setAttribute("items", list);

				gotoPage(request, response, "/showItem2.jsp");

			} else if (action.equals("delete")) {
				int code = Integer.parseInt(request.getParameter("code"));
				dao.deleteByPrimaryKey(code);

				List<ItemBean> list = dao.findAll();

				request.setAttribute("items", list);

				gotoPage(request, response, "/showItem2.jsp");

			} else {
				request.setAttribute("message", "正しく操作してください");
				gotoPage(request, response, "/errInternal.jsp");
			}

		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
}
