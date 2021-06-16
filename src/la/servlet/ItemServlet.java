package la.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.ItemBean;
import la.bean.ItemBeanPriceComparator;
import la.dao.DAOException;
import la.dao.ItemDAO;

/**
 * Servlet implementation class ItemServlet
 */
@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");

			ItemDAO dao = new ItemDAO();
			if (action == null || action.length() == 0) {
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");
			} else if (action.equals("sort")) {

				String key = request.getParameter("key");
				List<ItemBean> list = new ArrayList<ItemBean>();

				HttpSession session = request.getSession(false);
				if (session != null) {
					list = (List<ItemBean>) session.getAttribute("items");
				}

				if (list.isEmpty()) {
					if (key.equals("price_asc")) {
						list = dao.sortPrice(true);
					} else {
						list = dao.sortPrice(false);
					}

				} else {
					if (key.equals("price_asc")) {
						list.sort(new ItemBeanPriceComparator());
					} else {
						list.sort((new ItemBeanPriceComparator()).reversed());
					}

				}

				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");
			} else if (action.equals("add")) {
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));

				dao.addItem(name, price);
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");

			} else if (action.equals("search")) {
				String itemName = "";
				int minPrice = 0;
				int maxPrice = 0;
				HttpSession session = request.getSession(true);

				if (request.getParameter("itemName") != null && request.getParameter("itemName").length() != 0) {
					itemName = request.getParameter("itemName");
					request.setAttribute("itemName", itemName);
				}
				if (request.getParameter("minPrice") != null && request.getParameter("minPrice").length() != 0) {
					minPrice = Integer.parseInt(request.getParameter("minPrice"));
					request.setAttribute("minPrice", minPrice);
				}
				if (request.getParameter("maxPrice") != null && request.getParameter("maxPrice").length() != 0) {
					maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
					request.setAttribute("maxPrice", maxPrice);
				}

				List<ItemBean> list = dao.findBy3Inputs(itemName, minPrice, maxPrice);
				session.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");

			} else if (action.equals("delete")) {
				int code = Integer.parseInt(request.getParameter("code"));
				dao.deleteByPrimartKey(code);
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");

			}

			else {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
			}

		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました");
			RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
			rd.forward(request, response);
		}
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
