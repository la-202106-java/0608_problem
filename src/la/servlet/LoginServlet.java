package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.CategoryBean;
import la.bean.CustomerBean;
import la.dao.CustomerDAO;
import la.dao.DAOException;
import la.dao.ItemDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// パラメータの解析
			String action = request.getParameter("action");
			// パラメータなしの場合はログインページを表示
			if (action == null || action.length() == 0) {
				gotoPage(request, response, "/login.jsp");

			} else if (action.equals("login")) {
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				CustomerDAO dao = new CustomerDAO();
				CustomerBean bean = dao.findByEmailAndPassword(email, password);
				if (bean != null) {
					HttpSession session = request.getSession();
					session.setAttribute("customer", bean);
					gotoPage(request, response, "/top.jsp");
				} else {
					request.setAttribute("login_message", "メールアドレスとパスワードが一致しませんでした。");
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
		} catch (

		DAOException e) {
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

	public void init() throws ServletException {
		try {
			// カテゴリ一覧は最初にアプリケーションスコープへ入れる
			ItemDAO dao = new ItemDAO();
			List<CategoryBean> list = dao.findAllCategory();
			getServletContext().setAttribute("categories", list);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
