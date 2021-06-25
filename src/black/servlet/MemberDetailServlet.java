package black.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import black.bean.MemberBean;
import black.dao.MemberDAO;

/**
 * Servlet implementation class ListedItemDetailServlet
 */
@WebServlet("/MemberDetailServlet")
public class MemberDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String userType = (String) session.getAttribute("user");

		if (userType == null || userType.length() == 0) {
			gotoPage(request, response, "/LoginServlet");
		} else if (userType.equals("member")) {
			doPost(request, response);
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

		HttpSession session = request.getSession(false);
		String userType = (String) session.getAttribute("user");
		try {
			MemberDAO dao = new MemberDAO();
			if (userType == null || userType.length() == 0) {
				gotoPage(request, response, "/LoginServlet");
			} else if (userType.equals("member")) {
				MemberBean member = (MemberBean) session.getAttribute("logined");
				//				int id = member.getId();
				//				String name = member.getName();
				//				String address = member.getAddress();
				//				String tel = member.getTel();
				//				String email = member.getEmail();
				//				Date birthday = member.getBirthday();
				//				MemberBean bean_sales = dao.findMember(id);
				//				int sales = bean_sales.getSales();
				//				MemberBean bean = new MemberBean(name, address, tel, email, birthday, sales);

				request.setAttribute("member_info", member);
				gotoPage(request, response, "/memberDetail.jsp");

			} else if (userType.equals("admin")) {
				String idStr = (String) request.getParameter("member_id");
				if (idStr == null || idStr.length() == 0) {
					gotoPage(request, response, "/top");
				} else {
					int memberId = Integer.parseInt(idStr);
					MemberBean member = dao.findMember(memberId);

					request.setAttribute("member_info", member);
					session.setAttribute("member_info_se", member);
					gotoPage(request, response, "/memberDetail.jsp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラー");
			RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
			rd.forward(request, response);
		}

	}

}
