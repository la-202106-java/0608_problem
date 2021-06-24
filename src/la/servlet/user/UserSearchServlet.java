package la.servlet.user;

import java.io.IOException;
import java.sql.Date;

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
@WebServlet("/UserSearchServlet")
public class UserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSearchServlet() {
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
				gotoPage(request, response, "2_user/user_search.jsp");
			}

			else if (action.equals("search")) {
				String email = request.getParameter("email");
				if (email.isEmpty()) {
					gotoPage(request, response, "2_user/user_search.jsp");
				}
				NowUserBean bean = new NowUserBean();
				bean = dao.findbyEmail(email);
				if (bean == null) {
					gotoPage(request, response, "2_user/user_search_error.jsp");
				}
				request.setAttribute("bean", bean);
				session.setAttribute("bean", bean);

				gotoPage(request, response, "2_user/user_search_result.jsp");

			} else if (action.equals("delete1")) {
				gotoPage(request, response, "2_user/user_leave_confirm.jsp");

			}

			else if (action.equals("delete2")) {
				int id = Integer.parseInt(request.getParameter("id"));
				int rows = dao.deleteByID(id);
				System.out.println(rows);
				gotoPage(request, response, "2_user/user_search.jsp");

			} else if (action.equals("renew1")) {
				gotoPage(request, response, "2_user/user_renew.jsp");

			} else if (action.equals("renew2")) {
				int id = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
				String email = request.getParameter("email");
				Date birthDate = java.sql.Date.valueOf(request.getParameter("birthDate"));
				Date joinDate = java.sql.Date.valueOf(request.getParameter("joinDate"));
				NowUserBean bean = new NowUserBean(id, name, birthDate, joinDate, address, tel, email);
				//bean = bean.NowUserBean(id, name, address, tel, email);
				request.setAttribute("bean", bean);

				gotoPage(request, response, "2_user/user_renew_confirm.jsp");

			}

			else if (action.equals("renew3")) {
				int id = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
				String email = request.getParameter("email");
				dao.updateByID(id, name, address, tel, email);

				gotoPage(request, response, "2_user/user_search.jsp");

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
