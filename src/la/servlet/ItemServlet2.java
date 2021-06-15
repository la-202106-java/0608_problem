package la.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

				//sort
			} else if (action.equals("sort")) {
				String key = request.getParameter("key");
				List<ItemBean> list;

				//検索窓に入力値があったら
				HttpSession session = request.getSession(false);
				if (session != null) {
					@SuppressWarnings("unchecked")
					Map<String, String> search_items = (HashMap<String, String>) session.getAttribute("search_items");

					int min_price = 0;
					int max_price = Integer.MAX_VALUE;

					String part_name = search_items.get("name");
					String get_min_price = search_items.get("min_price");
					String get_max_price = search_items.get("max_price");

					if (get_min_price.length() != 0 && get_min_price != null) {
						min_price = Integer.parseInt(get_min_price);
					}
					if (get_max_price.length() != 0 && get_min_price != null) {
						max_price = Integer.parseInt(get_max_price);
					}

					if (key.equals("price_asc")) {
						list = dao.sortPrice(part_name, min_price, max_price, true);
					} else {
						list = dao.sortPrice(part_name, min_price, max_price, false);
					}
				} else {

					if (key.equals("price_asc")) {
						list = dao.sortPrice(true);
					} else {
						list = dao.sortPrice(false);
					}
				}
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem2.jsp");

				//search
			} else if (action.equals("search")) {
				int min_price = 0;
				int max_price = Integer.MAX_VALUE;

				String part_name = request.getParameter("name");
				String get_min_price = request.getParameter("min_price");
				String get_max_price = request.getParameter("max_price");

				if (get_min_price.length() != 0 && get_min_price != null) {
					min_price = Integer.parseInt(get_min_price);
				}
				if (get_max_price.length() != 0 && get_min_price != null) {
					max_price = Integer.parseInt(get_max_price);
				}

				List<ItemBean> list = dao.findByPrice(part_name, min_price, max_price);
				request.setAttribute("items", list);

				//kadai5.4
				HttpSession session = request.getSession();
				@SuppressWarnings("unchecked")
				Map<String, String> search_items = (HashMap<String, String>) session.getAttribute("search_items");
				if (search_items == null) {
					search_items = new HashMap<String, String>();
					session.setAttribute("search_items", search_items);
				}
				search_items.put("name", part_name);
				search_items.put("min_price", get_min_price);
				search_items.put("max_price", get_max_price);

				gotoPage(request, response, "/showItem2.jsp");

			} else if (action.equals("delete")) {
				int code = Integer.parseInt(request.getParameter("code"));
				dao.deleteByPrimaryKey(code);

				List<ItemBean> list = dao.findAll();
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
