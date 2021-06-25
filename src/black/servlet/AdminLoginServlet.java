
package black.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import black.bean.AdminBean;
import black.dao.AdminDAO;
import black.dao.DAOException;

/**
 * Servlet implementation class adminServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ログアウトはGETで処理
		String action = request.getParameter("action");
		if (action == null || action.length() == 0) {
			gotoPage(request, response, "/adminLogin.jsp");
		} else if (action.equals("logout")) {
			HttpSession session = request.getSession(false);
			session.invalidate();
			gotoPage(request, response, "/top");
		} else {
			gotoPage(request, response, "/adminLogin.jsp");
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
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		try {
			AdminDAO dao = new AdminDAO();
			if (action == null || action.length() == 0) {
				gotoPage(request, response, "/adminLogin.jsp");
			} else if (action.equals("login")) {
				String mail = request.getParameter("mail");
				String password = request.getParameter("password");
				dao = new AdminDAO();
				AdminBean bean = dao.findByEmailAndPassword(mail, password);
				if (bean != null) {
					session.setAttribute("logined", bean);
					session.setAttribute("user", "admin");
					gotoPage(request, response, "/top.jsp");
				} else {
					String message = "メールアドレスとパスワードが一致しません";
					request.setAttribute("message", message);
					gotoPage(request, response, "/adminLogin.jsp");
				}
			} else if (action.equals("logout")) {
				session = request.getSession();
				session.setAttribute("items", "none");
				gotoPage(request, response, "/top.jsp");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("messgae", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}
}
