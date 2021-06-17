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
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		try {
			ItemDAO dao = new ItemDAO();
			List<CategoryBean> list = dao.findAllCategory();
			getServletContext().setAttribute("categories", list);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");

		try {
			if (action == null || action.length() == 0) {
				gotoPage(request, response, "/login.jsp");
			} else if (action.equals("login")) {
				String mail = request.getParameter("mail");
				String pass = request.getParameter("pass");
				if (mail == null || mail.length() == 0 || pass == null || pass.length() == 0) {
					gotoPage(request, response, "/login.jsp");
				} else {
					CustomerDAO dao = new CustomerDAO();
					CustomerBean customer = dao.findByEmailAndPassword(mail, pass);
					if (customer == null) {
						request.setAttribute("errMessage", "メールアドレスとパスワードが一致しませんでした");
						gotoPage(request, response, "/login.jsp");
					} else {
						session.setAttribute("name", customer.getName());
						gotoPage(request, response, "/top.jsp");
					}
				}
			} else if (action.equals("logout")) {
				session.invalidate();
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

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
