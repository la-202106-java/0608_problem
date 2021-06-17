package shopping.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shopping.bean.CustomerBean;
import shopping.dao.CustomerDAO;
import shopping.dao.DAOException;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private String realPath;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 大事！
		request.setCharacterEncoding("UTF-8");
		try {
			String action = request.getParameter("action");
			if (action == null || action.length() == 0) {
				gotoPage(request, response, "/regist.jsp");
			} else if (action.equals("regist")) {
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
				String email = request.getParameter("email");
				String password = request.getParameter("password");

				CustomerDAO dao = new CustomerDAO(realPath);
				CustomerBean cu = dao.registByInputs(name, address, tel, email, password);
				if (cu == null) {
					request.setAttribute("message", "すでに登録されています");
					gotoPage(request, response, "/login.jsp");
				} else {
					HttpSession session = request.getSession();
					session.setAttribute("customer", cu);
					gotoPage(request, response, "/top.jsp");
				}
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void init() throws ServletException {
		ServletContext context = this.getServletContext();
		realPath = context.getRealPath("/WEB-INF/common.properties");
	}
}
