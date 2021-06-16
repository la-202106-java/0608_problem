package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");

			CustomerDAO dao = new CustomerDAO();
			if (action == null || action.length() == 0) {
				Object list = null;
				request.setAttribute("items", list);
				request.setAttribute("messsage", "メールアドレスとパスワードが一致しません");
				gotoPage(request, response, "/login.jsp");
			} else if (action.equals("login")) {
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				dao = new CustomerDAO();
				CustomerBean bean = dao.findByEmailAndPassword(email, password);
				if (bean != null) {
					request.setAttribute("items", bean);
					gotoPage(request, response, "/top.jsp");
				} else {
					String message = "メールアドレスとパスワードが一致しません";
					gotoPage(request, response, "/login.jsp");
				}
			}

		} catch (

		DAOException e) {
			e.printStackTrace();
			request.setAttribute("messgae", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
