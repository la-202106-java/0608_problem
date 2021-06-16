package la.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

		String action = request.getParameter("action");
		String email = request.getParameter("email");
		String password = hashed(request.getParameter("password"));

		try {
			CustomerDAO dao = new CustomerDAO();

			if (email == null || email.length() == 0 || password == null || password.length() == 0) {
				gotoPage(request, response, "/login.jsp");
			}
			//login
			else if (action.equals("login")) {
				CustomerBean bean = dao.findByEmailAndPassword(email, password);
				if (bean == null) {
					request.setAttribute("message", "メールアドレスとパスワードが一致しませんでした");
					gotoPage(request, response, "/login.jsp");
				} else {
					HttpSession session = request.getSession();
					session.setAttribute("customer", bean);
					gotoPage(request, response, "/top.jsp");
				}
			} else {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
			}

		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("logout")) {
			HttpSession session = request.getSession(false);
			session.invalidate();
			gotoPage(request, response, "/top.jsp");
		} else {
			request.setAttribute("message", "正しく操作してください。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	private String hashed(String str) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] result = digest.digest(str.getBytes());
			return String.format("%040x", new BigInteger(1, result));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
