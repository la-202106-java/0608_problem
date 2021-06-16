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
import la.dao.ItemDAO;

/**
 * Servlet implementation class ItemServlet
 */
@WebServlet("/ItemServlet")
public class ItemServlet extends HttpServlet {

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

			} else if (action.equals("add")) {
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				dao.addItem(name, price);
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");

			} else if (action.equals("sort")) {

				HttpSession session = request.getSession();

				String key = request.getParameter("key");
				String name = (String) session.getAttribute("Sgname");
				String tmpMIN = (String) session.getAttribute("Sminp");
				String tmpMAX = (String) session.getAttribute("Smaxp");
				int MINprice = Integer.parseInt(tmpMIN);
				int MAXprice = Integer.parseInt(tmpMAX);

				List<ItemBean> list;

				if (key.equals("price_asc")) {
					list = dao.sortPrice(true);
				} else {
					list = dao.sortPrice(false);
				}
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");

			} else if (action.equals("search")) {

				HttpSession session = request.getSession();

				String GoodsName = request.getParameter("Gname");
				String minP = request.getParameter("price1");
				String maxP = request.getParameter("price2");

				if (GoodsName == "") {
					GoodsName = null;
				} else {
					request.setAttribute("GNAME", GoodsName);
					session.setAttribute("Sgname", GoodsName);
				}

				if (minP == "") {
					minP = "-1";
				} else {
					request.setAttribute("MINP", minP);
					session.setAttribute("Sminp", minP);
				}

				if (maxP == "") {
					maxP = "-1";
				} else {
					request.setAttribute("MAXP", maxP);
					session.setAttribute("Smaxp", maxP);
				}

				int minPrice = Integer.parseInt(minP);
				int maxPrice = Integer.parseInt(maxP);
				List<ItemBean> list = dao.findByPrice(GoodsName, minPrice, maxPrice);

				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");

			} else if (action.equals("delete")) {
				int code = Integer.parseInt(request.getParameter("code"));
				dao.deleteByPrimaryKey(code);
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
			RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
			rd.forward(request, response);
		}

	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
