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
import la.bean.SearchBean;
import la.bean.SearchStringBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

/**
 * Servlet implementation class ItemServlet
 */
@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			request.getSession();
		}
		try {
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");
			ItemDAO dao = new ItemDAO();
			if (action == null || action.length() == 0) {
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");
			} else if (action.equals("add")) {
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				dao.addItem(name, price);
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");
			}
			//sort
			else if (action.equals("sort")) {
				SearchBean search = (SearchBean) (session.getAttribute("search"));
				String key = request.getParameter("key");
				List<ItemBean> list;
				if (search == null) {
					if (key.equals("price_asc")) {
						list = dao.sortPrice(true);
					} else {
						list = dao.sortPrice(false);
					}
				} else {
					if (key.equals("price_asc")) {
						list = dao.sortPrice(true, search);
					} else {
						list = dao.sortPrice(false, search);
					}
				}

				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");
			} else if (action.equals("search")) {
				String name = request.getParameter("name");
				String minprice = request.getParameter("minPrice");
				String maxprice = request.getParameter("maxPrice");

				SearchStringBean searchString = new SearchStringBean(name, minprice, maxprice);
				session.setAttribute("searchStr", searchString);

				if (minprice == null || minprice.length() == 0) {
					minprice = "0";
				}
				int minPrice = Integer.parseInt(minprice);
				if (maxprice == null || maxprice.length() == 0) {
					maxprice = "100000000";
				}
				int maxPrice = Integer.parseInt(maxprice);

				SearchBean search = new SearchBean(name, minPrice, maxPrice);
				session.setAttribute("search", search);

				List<ItemBean> list = dao.findByPrice(name, minPrice, maxPrice);
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");
			} else if (action.equals("delete")) {
				int code = Integer.parseInt(request.getParameter("code"));
				dao.deleteByPrimaryKey(code);
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");
			} else {
				request.setAttribute("message", "正しい操作をしてください。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました");
			gotoPage(request, response, "/errInternal.jsp");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
