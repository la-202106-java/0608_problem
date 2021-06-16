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
import la.dao.ItemDAO;

/**
 * Servlet implementation class ItemManagement
 */
@WebServlet("/ItemManagement")
public class ItemManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemManagement() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("UTF-8");
			RequestDispatcher rd = null;
			ItemDAO dao = new ItemDAO();
			String action = request.getParameter("action");
			if (action == null || action.trim().length() == 0) {
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				rd = request.getRequestDispatcher("itemList.jsp");
			} else if (action.equals("regist")) {
				rd = request.getRequestDispatcher("addItem.jsp");
			} else if (action.equals("add")) {

				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				int category = Integer.parseInt(request.getParameter("category"));

				ItemBean bean = new ItemBean(name, price, category);
				int result = dao.addItem(bean);
				System.out.println(result == 0 ? "Insert failed" : "Insert success");
				rd = request.getRequestDispatcher("ItemManagement?action=");

			} else if (action.equals("goUpdate")) {
				int code = Integer.parseInt(request.getParameter("code"));
				ItemBean bean = dao.findByPrimaryKey(code);
				request.setAttribute("bean", bean);
				rd = request.getRequestDispatcher("updateItem.jsp");
			} else if (action.equals("update")) {

				int code = Integer.parseInt(request.getParameter("code"));
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				int category = Integer.parseInt(request.getParameter("category"));

				ItemBean bean = new ItemBean(code, name, price, category);
				int result = dao.updateBeanByCode(bean);
				System.out.println(result == 0 ? "Update failed" : "Update success");
				rd = request.getRequestDispatcher("ItemManagement?action=");
			} else if (action.equals("delete")) {

				int code = Integer.parseInt(request.getParameter("code"));

				int result = dao.deleteBeanByCode(code);
				System.out.println(result == 0 ? "Delete failed" : "Delete success");
				rd = request.getRequestDispatcher("ItemManagement?action=");
			}

			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("mseeage", "内部エラーが発生しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/errIternal.jsp");
			rd.forward(request, response);
		}
	}

}
