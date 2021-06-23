package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.dao.AdminPlanDAO;
import la.dao.DAOException;

/**
 * Servlet implementation class AdminPlanServlet
 */
@WebServlet("/admin/plan")
public class AdminPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminPlanServlet() {
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
			AdminPlanDAO dao = new AdminPlanDAO();
			if (action == null || action.length() == 0) {
				gotoPage(request, response, "/adminTop.jsp");
			} else if (action.equals("add")) {
				int innID = Integer.parseInt(request.getParameter("id"));
				String content = request.getParameter("contents");
				int fee = Integer.parseInt(request.getParameter("fee"));
				int roomMax = Integer.parseInt(request.getParameter("room"));
				String imgUrl = request.getParameter("picture");
				int row = dao.addPlan(innID, content, fee, roomMax, imgUrl);
				request.setAttribute("message", row + "件追加しました。");
				gotoPage(request, response, "/adminConfirm.jsp");
			} else {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
