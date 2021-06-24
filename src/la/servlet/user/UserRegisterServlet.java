package la.servlet.user;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.NowUserBean;
import la.dao.DAOException;
import la.dao.NowUserDAO;

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		//パラメータ解析
		String action = request.getParameter("action");
		try {
			//セッション領域の取得
			HttpSession session = request.getSession();
			NowUserDAO dao = new NowUserDAO();
			if (action == null || action.length() == 0) {
				gotoPage(request, response, "2_user/user_register.jsp");
			}

			else if (action.equals("register1")) {

				String name = request.getParameter("name");
				String BirthDate = request.getParameter("birthDate");

				LocalDate todaysDate = LocalDate.now();
				Date joinDate = java.sql.Date.valueOf(todaysDate);
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
				String email = request.getParameter("email");
				if (name.isEmpty() || BirthDate.isEmpty() || address.isEmpty() || tel.isEmpty() || email.isEmpty()) {
					gotoPage(request, response, "2_user/user_register_error.jsp");
				}

				Date birthDate = java.sql.Date.valueOf(request.getParameter("birthDate"));
				NowUserBean bean = new NowUserBean(name, birthDate, joinDate, address, tel, email);
				request.setAttribute("bean", bean);
				session.setAttribute("bean", bean);

				gotoPage(request, response, "2_user/user_register_confirm.jsp");

			}

			else if (action.equals("return_register1")) {
				gotoPage(request, response, "2_user/user_register_return.jsp");

			} else if (action.equals("register2")) {

				NowUserBean bean = (NowUserBean) session.getAttribute("bean");
				//登録済み
				NowUserBean r_bean = new NowUserBean();
				r_bean = dao.register(bean.getName(), bean.getBirthDate(), bean.getJoinDate(), bean.getAddress(),
						bean.getTel(), bean.getEmail());
				session.setAttribute("r_bean", r_bean);
				gotoPage(request, response, "2_user/user_register_confirmed.jsp");

			} else if (action.equals("top")) {
				gotoPage(request, response, "/TopServlet");

			}

		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました");
			gotoPage(request, response, "errInternal.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
