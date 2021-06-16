package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.CustomerBean;
import la.dao.CustomerDAO;
import la.dao.DAOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String email = request.getParameter("email");
			if (email == null || email.length() == 0) {
				request.setAttribute("error", "メールアドレスが入力されていません");
				gotoPage(request, response, "/login.jsp");
				return;
			}
			String password = request.getParameter("password");
			if (password == null || password.length() == 0) {
				request.setAttribute("error", "パスワードが入力されていません");
				gotoPage(request, response, "/login.jsp");
				return;
			}
			String action = request.getParameter("action");
			if (action == null || action.length() == 0) {
				gotoPage(request, response, "/login.jsp");
			} else if (action.equals("login")) {
				CustomerDAO dao = new CustomerDAO();
				CustomerBean customer = dao.findByEmailAndPassword(email, password);
				if (customer.getEmail() != null && customer.getPassword() != null) {
					HttpSession session = request.getSession();
					session.setAttribute("Customer", customer);
					gotoPage(request, response, "/top.jsp");
				} else {
					request.setAttribute("error", "メールアドレスとパスワードが一致しませんでした");
					gotoPage(request, response, "/login.jsp");
				}

			} else if (action.equals("logout")) {
				HttpSession session = request.getSession(false);
				if (session != null) {
					session.invalidate();
				}
				gotoPage(request, response, "/login.jsp");
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
