
package black.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import black.bean.MemberBean;
import black.dao.DAOException;
import black.dao.MemberDAO;

/**
 * Servlet implementation class adminServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ログアウトはGETで処理
		String action = request.getParameter("action");
		if (action == null || action.length() == 0) {
			gotoPage(request, response, "/memberLogin.jsp");
		} else if (action.equals("logout")) {
			HttpSession session = request.getSession(false);
			session.invalidate();
			gotoPage(request, response, "/top");
		} else {
			gotoPage(request, response, "/memberLogin.jsp");
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
		try {
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");
			HttpSession session = request.getSession(false);
			MemberDAO dao = new MemberDAO();
			if (action == null || action.length() == 0) {
				gotoPage(request, response, "/memberLogin.jsp");
			} else if (action.equals("login")) {
				dao = new MemberDAO();
				String mail = request.getParameter("email");
				String password = request.getParameter("pass");
				MemberBean bean = dao.findByEmailAndPassword(mail, password);
				if (bean != null && bean.getQuitDate() == null) {
					session.setAttribute("logined", bean);
					session.setAttribute("user", "member");
					String page = (String) session.getAttribute("page");
					if (page != null && page.equals("itembuy")) {
						gotoPage(request, response, "/listedItemBuyCheck.jsp");
					} else {
						gotoPage(request, response, "/top");
					}

				} else {
					String message = "メールアドレスとパスワードが一致しません";
					request.setAttribute("message", message);
					gotoPage(request, response, "/memberLogin.jsp");
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("messgae", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}
}
