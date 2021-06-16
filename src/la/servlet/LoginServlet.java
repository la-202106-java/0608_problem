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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String action = request.getParameter("action");
			HttpSession session = request.getSession();

			if (action.equals("login")) {
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				CustomerDAO dao = new CustomerDAO();
				CustomerBean customer = dao.findByEmailAndPassword(email, password);
				if (customer == null) {
					String errMessage = "メールアドレスとパスワードが一致しませんでした";
					request.setAttribute("errMessage", errMessage);
					gotoPage(request, response, "/login.jsp");
				} else {
					session.setAttribute("customer", customer);
					gotoPage(request, response, "/ShowItemServlet?action=top");
				}
			} else if (action.equals("logout")) {
				session.invalidate();
				gotoPage(request, response, "/login.jsp");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
