package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.dao.DAOException;
import la.dao.MembersDAO;

/**
 * Servlet implementation class AdminDeleteServlet
 */
@WebServlet("/AdminDeleteServlet")
public class AdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminDeleteServlet() {
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
			if (action == null || action.length() == 0) {
				gotoPage(request, response, "/adminTop.jsp");
			} else if (action.equals("kakunin")) {
				String id = request.getParameter("id");
				String from = request.getParameter("from");
				request.setAttribute("id", id);
				request.setAttribute("from", from);
				gotoPage(request, response, "/adminDelete.jsp");
			} else if (action.equals("delete")) {
				String from = request.getParameter("from");
				if (from == null || from.length() == 0) {
					gotoPage(request, response, "/adminTop.jsp");
				} else if (from.equals("member")) {
					int id = Integer.parseInt(request.getParameter("id"));
					MembersDAO dao = new MembersDAO();
					dao.quit(id);
					request.setAttribute("message", "会員番号：" + id + "を削除しました。");
					gotoPage(request, response, "/adminConfirm.jsp");

				} else {
					request.setAttribute("message", "正しく操作してください。");
					gotoPage(request, response, "/errInternal.jsp");
				}

			} else if (action.equals("cancel")) {
				String from = request.getParameter("from");
				if (from == null || from.length() == 0) {
					gotoPage(request, response, "/adminTop.jsp");
				} else if (from.equals("member")) {
					gotoPage(request, response, "/adminMember.jsp");
				} else {
					gotoPage(request, response, "/adminTop.jsp");
				}

			}

			else {
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
