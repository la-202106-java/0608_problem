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

import la.bean.ItemBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

/**
 * Servlet implementation class ItemServlet
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
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");

			ItemDAO dao = new ItemDAO();
			if (action == null || action.length() == 0) {
				List<ItemBean> list = dao.findAll2();
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem2.jsp");
			} else if (action.equals("add")) {
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				dao.addItem(name, price);
				List<ItemBean> list = dao.findAll2();
				request.setAttribute("items", list);
				request.setAttribute("setNameAdd", name);
				request.setAttribute("setPriceAdd", price);
				gotoPage(request, response, "/showItem2.jsp");
			}

			else if (action.equals("sort")) {
				String key = request.getParameter("key");
				List<ItemBean> list = dao.findAll2();
				if (list == null) {
					if (key.equals("price_asc")) {
						list = dao.sortPrice(true);
					} else {
						list = dao.sortPrice(false);
					}
					request.setAttribute("items", list);
				} else if (list != null && key.equals("price_asc")) {
					list.sort(Comparator.comparing(ItemBean::getPrice));
					request.setAttribute("items", list);
				} else {
					list.sort(Comparator.comparing(ItemBean::getPrice).reversed());
					request.setAttribute("items", list);
				}

				gotoPage(request, response, "/showItem2.jsp");
			}

			else if (action.equals("search")) {
				String name;
				int price;
				int price2;
				if (request.getParameter("name") != null && request.getParameter("name").length() != 0) {
					name = request.getParameter("name");
					request.setAttribute("setName", name);
				} else {
					name = "";
				}
				if (request.getParameter("price") != null && request.getParameter("price").length() != 0) {
					price = Integer.parseInt(request.getParameter("price"));
					request.setAttribute("setPrice", price);
				} else {
					price = 99999;
				}
				if (request.getParameter("price2") != null && request.getParameter("price2").length() != 0) {
					price2 = Integer.parseInt(request.getParameter("price2"));
					request.setAttribute("setPrice2", price);
				} else {
					price2 = 0;
				}
				List<ItemBean> list = dao.findByPrice(price2, price, name);
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem2.jsp");
			}

			else if (action.equals("delete")) {
				int code = Integer.parseInt(request.getParameter("code"));
				dao.deleteByPrimaryKey(code);
				List<ItemBean> list = dao.findAll2();
				request.setAttribute("items", list);
				request.setAttribute("setCode", code);
				gotoPage(request, response, "/showItem2.jsp");
			}

			else if (action.equals("regist")) {
				gotoPage(request, response, "/addItem.jsp");
			}

			else if (action.equals("add2")) {
				int category_code = Integer.parseInt(request.getParameter("category_code"));
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				dao.addItem2(category_code, name, price);
				List<ItemBean> list = dao.findAll2();
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem2.jsp");
			} else {
				request.setAttribute("messgae", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("messgae", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
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
