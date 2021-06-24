
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
	//仮のログイン用のパスワードとメールアドレス
	//private static final String MAIL = "xyz@abc@.com";
	//private static final String PASS = "abc";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
				if (bean != null) {
					session.setAttribute("logined", bean);
					gotoPage(request, response, "/top.jsp");
				} else {
					String message = "メールアドレスとパスワードが一致しません";
					request.setAttribute("message", message);
					gotoPage(request, response, "/memberLogin.jsp");
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

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
