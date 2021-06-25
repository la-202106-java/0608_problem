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
@WebServlet("/MemberRegistServlet")
public class MemberRegistServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		try {
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");

			if (action == null || action.length() == 0) {
				gotoPage(request, response, "/memberRegistForm.jsp");
			} else if (action.equals("create")) {

				String name = request.getParameter("name");
				request.setAttribute("setName", name);
				String address = request.getParameter("address");
				request.setAttribute("setAddress", address);
				String tel = request.getParameter("tel");
				request.setAttribute("setTel", tel);
				String email = request.getParameter("email");
				request.setAttribute("setEmail", email);
				//DateをStringで受け取る方法,年月日をそれぞれ受け取って、結合して生年月日としてデータベースに挿入する
				String date = request.getParameter("date");

				//生年月日をString型からDate型に変更する処理
				Date birthday = Date.valueOf(date);
				request.setAttribute("setBirthday", birthday);
				String pass = request.getParameter("pass");
				request.setAttribute("setPass", pass);

				MemberBean bean = new MemberBean(-1, name, address, tel, email, birthday, pass);
				session.setAttribute("tmp-logined", bean);
				gotoPage(request, response, "/memberRegistCheck.jsp");
			} else if (action.equals("add")) {
				MemberBean member = (MemberBean) session.getAttribute("tmp-logined");
				if (member == null) {
					request.setAttribute("message", "会員情報を入力してください");
					gotoPage(request, response, "/errInternal.jsp");
				} else {
					MemberDAO dao = new MemberDAO();
					String name = member.getName();
					String address = member.getAddress();
					String tel = member.getTel();
					String email = member.getEmail();
					Date birthday = member.getBirthday();
					String pass = member.getPass();
					int id = dao.addMember(name, address, tel, email, birthday, pass);
					MemberBean bean = new MemberBean(id, name, address, tel, email, birthday, pass);

					session.setAttribute("logined", bean);
					request.setAttribute("message", "会員登録が完了しました");
					session.setAttribute("user", "member");
					session.removeAttribute("logined-tmp");
					gotoPage(request, response, "/top");
				}

			} else if (action.equals("cancel")) {
				MemberBean member = (MemberBean) session.getAttribute("tmp-logined");
				String name = member.getName();
				request.setAttribute("setName", name);
				String address = member.getAddress();
				request.setAttribute("setAddress", address);
				String tel = member.getTel();
				request.setAttribute("setTel", tel);
				String email = member.getEmail();
				request.setAttribute("setEmail", email);
				Date birthday = member.getBirthday();
				request.setAttribute("setBirthday", birthday);
				String pass = member.getPass();
				request.setAttribute("setPass", pass);
				gotoPage(request, response, "/memberRegistForm.jsp");

			} else {
				request.setAttribute("message", "正しく操作してください");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} catch (

		DAOException e) {
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
