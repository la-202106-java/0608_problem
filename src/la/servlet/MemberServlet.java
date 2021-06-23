package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.Member;
import la.dao.MemberDAO;

@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	//	SimpleDateFormat df = new SimpleDateFormat("yyyymmdd");

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		//		response.setContentType("text/html;charset=UTF-8");
		String action = request.getParameter("action");
		MemberDAO dao = new MemberDAO();

		if (action.equals("confirm")) { //登録確認
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String tel = request.getParameter("tel");
			String mail = request.getParameter("mail");
			String birth = request.getParameter("birth");

			java.sql.Date b1 = java.sql.Date.valueOf(birth);

			Member bean = new Member(name, address, tel, mail, b1);
			session.setAttribute("member", bean);
			RequestDispatcher rd = request.getRequestDispatcher("/confirm_kaiin_touroku.jsp");
			rd.forward(request, response);
		} else if (action.equals("addmember")) { //会員登録
			Member bean = (Member) session.getAttribute("member");

			String name = bean.getName();
			String address = bean.getAddress();
			String tel = bean.getTel();
			String mail = bean.geteMail();
			String birth = bean.getBirth();

			java.sql.Date b1 = java.sql.Date.valueOf(birth);

			dao.addMember(name, address, tel, mail, b1);
			Member memberbean = dao.findByName2(name);

			//		Member bean = new Member(name, address, tel, mail, b1);
			request.setAttribute("members", memberbean);

			gotoPage(request, response, "/confirm_ok.jsp");

		} else if (action.equals("cancel")) { //cancel
			session.invalidate();
			gotoPage(request, response, "/kaiin_touroku.jsp");
		} else if (action.equals("searchmember")) { //検索
			int id = Integer.parseInt(request.getParameter("id"));
			Member idbean = dao.findById(id);
			session.setAttribute("imember", idbean);
			gotoPage(request, response, "/kaiin_kensaku.jsp");
		} else if (action.equals("fix")) { //変更

			gotoPage(request, response, "/kaiin_henkou.jsp");
		} else if (action.equals("update")) { //変更
			Member bean = (Member) session.getAttribute("imember");
			int id = bean.getId();
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String tel = request.getParameter("tel");
			String mail = request.getParameter("email");

			int update = dao.updateMember(id, name, address, tel, mail);

			gotoPage(request, response, "/message_completed.jsp");
		} else if (action.equals("delete")) { //退会
			Member bean = (Member) session.getAttribute("imember");
			int id = bean.getId();
			int delete = dao.updateMember(id);

			gotoPage(request, response, "/message_completed.jsp");

		} else {
			request.setAttribute("message", "正しく操作してください。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}
}
