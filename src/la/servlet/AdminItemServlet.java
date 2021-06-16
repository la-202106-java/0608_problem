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
import la.dao.AdminItemDAO;
import la.dao.DAOException;

/**
 * Servlet implementation class AdminItemServlet
 */
@WebServlet("/AdminItemServlet")
public class AdminItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminItemServlet() {
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
			AdminItemDAO dao = new AdminItemDAO();

			if (action == null || action.length() == 0) {
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/WEB-INF/adminItems.jsp");
			}

			//regist
			else if (action.equals("regist")) {
				gotoPage(request, response, "/WEB-INF/adminAddItem.jsp");
			}

			//add
			else if (action.equals("add")) {
				int categoryCode = Integer.parseInt(request.getParameter("category_code"));
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));

				dao.addItem(categoryCode, name, price);
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);

				gotoPage(request, response, "/WEB-INF/adminItems.jsp");
			}

			//edit
			else if (action.equals("edit")) {
				int code = Integer.parseInt(request.getParameter("code"));
				request.setAttribute("code", code);
				gotoPage(request, response, "/WEB-INF/adminEditItem.jsp");
			}

			//update
			else if (action.equals("update")) {
				int code = Integer.parseInt(request.getParameter("code"));
				int categoryCode = Integer.parseInt(request.getParameter("category_code"));
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));

				dao.updateItem(code, categoryCode, name, price);
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);

				gotoPage(request, response, "/WEB-INF/adminItems.jsp");
			}

			//delete
			else if (action.equals("delete")) {
				int code = Integer.parseInt(request.getParameter("code"));
				dao.deleteByPrimaryKey(code);
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);

				gotoPage(request, response, "/WEB-INF/adminItems.jsp");
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