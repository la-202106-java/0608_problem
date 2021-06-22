package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.dao.AdminDAO;
import la.dao.DAOException;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String action = request.getParameter("action");
			AdminDAO dao = new AdminDAO();
			if (action == null || action.length() == 0) {
				gotoPage(request, response, "/adminLogin.jsp");
			} else if (action.equals("login")) {
				String email = request.getParameter("email");
				if (email == null || email.length() == 0) {
					request.setAttribute("error", "メールアドレスが入力されていません");
					gotoPage(request, response, "/adminLogin.jsp");
					return;
				}
				String password = request.getParameter("password");
				if (password == null || password.length() == 0) {
					request.setAttribute("error", "パスワードが入力されていません");
					gotoPage(request, response, "/adminLogin.jsp");
					return;
				}
				if (dao.isAdmin(email, password)) {
					gotoPage(request, response, "/adminTop.jsp");
				} else {
					request.setAttribute("error", "メールアドレスとパスワードが一致しませんでした");
					gotoPage(request, response, "/adminLogin.jsp");
				}
			} else if (action.equals("logout")) {

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

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
