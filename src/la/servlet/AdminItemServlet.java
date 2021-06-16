package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.AdminItemBean;
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
		try {
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");
			AdminItemDAO dao = new AdminItemDAO();
			if (action == null || action.length() == 0) {
				List<AdminItemBean> list = dao.findAll();
				request.setAttribute("adminItems", list);
				gotoPage(request, response, "/WEB-INF/items.jsp");
			}
			//add
			else if (action.equals("regist")) {
				gotoPage(request, response, "/WEB-INF/addItem.jsp");
			} else if (action.equals("add")) {
				int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				dao.addItem(categoryCode, name, price);
				List<AdminItemBean> list = dao.findAll();
				request.setAttribute("adminItems", list);
				gotoPage(request, response, "/WEB-INF/items.jsp");
			}
			//update
			else if (action.equals("edit")) {
				int code = Integer.parseInt(request.getParameter("code"));
				int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				AdminItemBean item = new AdminItemBean(code, categoryCode, name, price);
				request.setAttribute("item", item);
				gotoPage(request, response, "/WEB-INF/updateItem.jsp");
			} else if (action.equals("update")) {
				int code = Integer.parseInt(request.getParameter("code"));
				String stringCategoryCode = request.getParameter("categoryCode");
				if (stringCategoryCode == null || stringCategoryCode.length() == 0) {
					request.setAttribute("message", "数値を入力してください。");
					gotoPage(request, response, "/errInternal.jsp");
				}
				int categoryCode = Integer.parseInt(stringCategoryCode);

				String name = request.getParameter("name");
				String stringPrice = request.getParameter("price");
				if (stringPrice == null || stringPrice.length() == 0) {
					request.setAttribute("message", "数値を入力してください。");
					gotoPage(request, response, "/errInternal.jsp");
				}
				int price = Integer.parseInt(stringPrice);
				dao.updateItem(code, categoryCode, name, price);
				List<AdminItemBean> list = dao.findAll();
				request.setAttribute("adminItems", list);
				gotoPage(request, response, "/WEB-INF/items.jsp");
			}
			//error
			else {
				request.setAttribute("message", "正しい操作をしてください。");
				gotoPage(request, response, "/errInternal.jsp");
			}

		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました");
			gotoPage(request, response, "/errInternal.jsp");
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

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
