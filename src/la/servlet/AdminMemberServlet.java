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

import la.bean.MemberBean;
import la.dao.DAOException;
import la.dao.MembersDAO;

/**
 * Servlet implementation class AdminMemberServlet
 */
@WebServlet("/admin/member")
public class AdminMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminMemberServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if ("true".equals(session.getAttribute("isAdminLogin"))) {
			try {
				request.setCharacterEncoding("UTF-8");
				String action = request.getParameter("action");
				MembersDAO dao = new MembersDAO();
				if (action == null || action.length() == 0) {
					gotoPage(request, response, "/adminTop.jsp");
				} else if (action.equals("search")) {
					String name = request.getParameter("name");
					String email = request.getParameter("email");
					List<MemberBean> list = dao.searchMember(name, email);
					request.setAttribute("name", name);
					request.setAttribute("email", email);
					request.setAttribute("Members", list);
					gotoPage(request, response, "/adminMember.jsp");
				} else if (action.equals("edit")) {
					MemberBean target = new MemberBean();
					int id = Integer.parseInt(request.getParameter("id"));
					String name = request.getParameter("name");
					String postalCode = request.getParameter("postal_code");
					String address = request.getParameter("address");
					String email = request.getParameter("email");
					String tel = request.getParameter("tel");
					target.setId(id);
					target.setName(name);
					target.setPostalCode(postalCode);
					target.setAddress(address);
					target.setEmailAddress(email);
					target.setTel(tel);
					request.setAttribute("memberTarget", target);
					gotoPage(request, response, "/adminUpdateMember.jsp");

				} else if (action.equals("update")) {
					int id = Integer.parseInt(request.getParameter("id"));
					String name = request.getParameter("name");
					String postalCode = request.getParameter("postal_code");
					String address = request.getParameter("address");
					String email = request.getParameter("email");
					String tel = request.getParameter("tel");
					dao.update(id, name, postalCode, address, email, tel);
					request.setAttribute("message", name + "を更新しました。");
					gotoPage(request, response, "/adminConfirm.jsp");
				} else {
					request.setAttribute("message", "正しく操作してください。");
					gotoPage(request, response, "/errInternal.jsp");
				}
			} catch (DAOException e) {
				e.printStackTrace();
				request.setAttribute("message", "内部エラーが発生しました。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} else {
			request.setAttribute("error", "ログインしていません");
			gotoPage(request, response, "/adminLogin.jsp");
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

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
