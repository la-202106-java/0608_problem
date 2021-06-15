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
@WebServlet("/ItemServlet2")
public class ItemServlet2 extends HttpServlet {

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
				List<ItemBean> list = dao.findAll2();
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem2.jsp");

			} else if (action.equals("regist")) {

				gotoPage(request, response, "/addItem.jsp");

			} else if (action.equals("add")) {
				int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));

				dao.addItem2(categoryCode, name, price);
				List<ItemBean> list = dao.findAll2();
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem2.jsp");

			} else {
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
