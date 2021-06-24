package black.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import black.bean.MemberBean;
import black.dao.DAOException;
import black.dao.MemberDAO;

/**
 * Servlet implementation class KaiinServlet
 */
@WebServlet("/MemberRegistChangeServlet")
public class MemberRegistChangeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		try {
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");

			if (action == null || action.length() == 0) {
				gotoPage(request, response, "/memberRegistForm.jsp");
			} else if (action.equals("change")) {
				MemberBean member = (MemberBean) session.getAttribute("logined");
				if (member == null) {
					request.setAttribute("message", "会員情報を入力してください");
					gotoPage(request, response, "/errInternal.jsp");
				} else {
					int id = member.getId();
					String name = member.getName();
					String address = member.getAddress();
					String tel = member.getTel();
					String email = member.getEmail();
					Date birthday = member.getBirthday();
					String pass = member.getPass();

					MemberBean bean = new MemberBean(id, name, address, tel, email, birthday, pass);
					session.setAttribute("member", bean);
					gotoPage(request, response, "/memberRegistChange.jsp");
				}
			} else if (action.equals("check")) {
				MemberBean member = (MemberBean) session.getAttribute("logined");
				int id = member.getId();
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
				String email = request.getParameter("email");
				//Date birthday = Date.valueOf(request.getParameter("birthday"));
				Date birthday = member.getBirthday();
				String pass = request.getParameter("pass");
				MemberBean bean = new MemberBean(id, name, address, tel, email, birthday, pass);
				session.setAttribute("member", bean);
				gotoPage(request, response, "/memberChangeCheck.jsp");
			} else if (action.equals("add")) {
				MemberBean member = (MemberBean) session.getAttribute("logined");
				MemberBean memberchanged = (MemberBean) session.getAttribute("member");
				if (member == null) {
					request.setAttribute("message", "会員情報を入力してください");
					gotoPage(request, response, "/errInternal.jsp");
				} else {
					MemberDAO dao = new MemberDAO();
					int id = member.getId();
					String name = memberchanged.getName();
					String address = memberchanged.getAddress();
					String tel = memberchanged.getTel();
					String email = memberchanged.getEmail();
					Date birthday = memberchanged.getBirthday();
					String pass = memberchanged.getPass();
					MemberBean bean = new MemberBean(id, name, address, tel, email, birthday, pass);
					dao.updateMember(id, name, address, tel, email, birthday, pass);
					request.setAttribute("message", "会員情報変更が完了しました");
					session.setAttribute("logined", bean);
					gotoPage(request, response, "/top.jsp");
				}

			} else {
				request.setAttribute("message", "正しく操作してください");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("messgae", "内部エラーが発生しました。");
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
