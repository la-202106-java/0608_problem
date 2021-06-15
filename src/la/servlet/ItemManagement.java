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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			ItemDAO dao = new ItemDAO();
			String action = request.getParameter("action");
			if (action == null || action.trim().length() == 0) {
				List<ItemBean> list = dao.findAll();

				request.setAttribute("items", list);
			}

			RequestDispatcher rd = request.getRequestDispatcher("itemList.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("mseeage", "内部エラーが発生しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/errIternal.jsp");
			rd.forward(request, response);
		}
	}

}
