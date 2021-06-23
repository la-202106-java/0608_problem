package la.servlet;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.AdminBean;
import la.dao.AdminDAO;
import la.dao.DAOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action == null || action.length() == 0) {
			// 未ログインならログイン
			// ログイン済みならトップページ
			HttpSession session = request.getSession(false);
			if (session == null) {
				gotoPage(request, response, "/login.jsp");
			} else {
				if (!Objects.isNull(session.getAttribute("admin"))) {
					gotoPage(request, response, "/TopServlet");
				} else {
					gotoPage(request, response, "/login.jsp");
				}

			}
		} else if (action.equals("logout")) {
			HttpSession session = request.getSession(false);
			if (session == null) {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
			}
			session.removeAttribute("admin");
			session.invalidate();
			gotoPage(request, response, "/login.jsp");

		} else {
			request.setAttribute("message", "正しく操作してください。");
			gotoPage(request, response, "/errInternal.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {
			AdminDAO dao = new AdminDAO();
			AdminBean admin = dao.findbyEmailAndPassword(email, password);
			if (Objects.isNull(admin)) {
				request.setAttribute("message", "メールアドレスとパスワードが一致しませんでした");
				gotoPage(request, response, "/login.jsp");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("admin", admin);
				gotoPage(request, response, "/TopServlet");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
