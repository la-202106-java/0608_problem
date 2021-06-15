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
 * Servlet implementation class ItemServlet2
 */
@WebServlet("/ItemServlet2")
public class ItemServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemServlet2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		try {
			ItemDAO dao = new ItemDAO();

			if (action == null || action.length() == 0) {
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/WEB-INF/showItem.jsp");
			}
			//add
			else if (action.equals("add")) {
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				dao.addItem(name, price);
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/WEB-INF/showItem.jsp");
			}
			//sort
			else if (action.equals("sort")) {
				String key = request.getParameter("key");
				List<ItemBean> list;
				if (key.equals("price_asc")) {
					list = dao.sortPrice(true);
				} else {
					list = dao.sortPrice(false);
				}
				request.setAttribute("items", list);
				gotoPage(request, response, "/WEB-INF/showItem.jsp");
			}
			//search
			else if (action.equals("search")) {
				int minPrice;
				int maxPrice;

				String min = request.getParameter("min_price");
				if (min == null || min.length() == 0) {
					minPrice = 0;
				} else {
					minPrice = Integer.parseInt(min);
				}

				String max = request.getParameter("max_price");
				if (max == null || max.length() == 0) {
					maxPrice = Integer.MAX_VALUE;
				} else {
					maxPrice = Integer.parseInt(max);
				}

				String name = request.getParameter("name");
				if (name == null) {
					name = "";
				}
				List<ItemBean> list = dao.findByPriceAndName(minPrice, maxPrice, name);
				request.setAttribute("items", list);
				gotoPage(request, response, "/WEB-INF/showItem.jsp");
			}
			//delete
			else if (action.equals("delete")) {
				int code = Integer.parseInt(request.getParameter("code"));
				dao.deleteByPrimaryKey(code);
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/WEB-INF/showItem.jsp");
			} else {
				request.setAttribute("message", "正しく操作してください");
				gotoPage(request, response, "/errInternal.jsp");
			}

		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse responce, String page)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, responce);
	}
}
