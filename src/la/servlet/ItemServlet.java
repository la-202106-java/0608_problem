package la.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.ItemBean;
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
		// TODO Auto-generated method stub
		try {
			ItemDAO dao = new ItemDAO();
			String action = request.getParameter("action");
			if (action == null || action.trim().length() == 0) {
				List<ItemBean> list = dao.findAll();

				request.setAttribute("items", list);
			} else if (action.equals("update")) {
				int code = Integer.valueOf(request.getParameter("code"));
				int price = Integer.valueOf(request.getParameter("price"));
				dao.updatePriceByCode(code, price);
			} else if (action.equals("serach")) {
				int lower = 0;
				if (request.getParameter("lower") != null && request.getParameter("lower").trim().length() > 0) {
					lower = Integer.parseInt(request.getParameter("lower"));
					request.setAttribute("lower", lower);
				}

				int upper = Integer.MAX_VALUE;
				if (request.getParameter("upper") != null && request.getParameter("upper").trim().length() > 0) {
					upper = Integer.parseInt(request.getParameter("upper"));
					request.setAttribute("uppera", upper);
				}

				String name = "";

				if (request.getParameter("name") != null && request.getParameter("name").trim().length() > 0) {
					name = request.getParameter("name");
					request.setAttribute("name", name);
				}

				List<ItemBean> list = dao.findItemByPriceRangeAndName(lower, upper, name);
				String sort = request.getParameter("sort");
				if (sort.equals("0")) {
					Collections.sort(list);
				} else {

					Collections.sort(list, Collections.reverseOrder());
				}
				request.setAttribute("items", list);

			}

			RequestDispatcher rd = request.getRequestDispatcher("showItem.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("mseeage", "内部エラーが発生しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/errIternal.jsp");
			rd.forward(request, response);
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

}
