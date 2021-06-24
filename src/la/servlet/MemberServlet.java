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
import la.dao.DAOException;
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
		try {

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
				//		} else if (action.equals("searchid")) { //検索
				//			int id = Integer.parseInt(request.getParameter("id"));
				//			Member idbean = dao.findById(id);
				//			session.setAttribute("imember", idbean);
				//			gotoPage(request, response, "/kaiin_kensaku.jsp");
				/*******************/
			} else if (action.equals("search")) { //検索
				String radio = request.getParameter("radio");
				Member sbean = null;

				String email = null;
				String str_id = null;

				if (radio.equals("email")) {//email検索
					email = request.getParameter("email");
					//入力値があれば検索無ければそのまま
					if (email != null && email.length() != 0) {
						sbean = dao.findByEmail(email);
					}
				} else if (radio.equals("id")) {
					str_id = request.getParameter("id");
					if (str_id != null && str_id.length() != 0) {
						int id = Integer.parseInt(str_id);
						sbean = dao.findById(id);
					}
				}
				request.setAttribute("id", str_id);
				request.setAttribute("title", email);

				session.setAttribute("imember", sbean);
				gotoPage(request, response, "/kaiin_kensaku.jsp");
				/*******************************/
			} else if (action.equals("confirmhenkou")) { //変更

				gotoPage(request, response, "/kaiin_henkou.jsp");
			} else if (action.equals("confirmtaikai")) { //退会

				gotoPage(request, response, "/confirm_taikai.jsp");
			} else if (action.equals("update")) { //変更
				Member bean = (Member) session.getAttribute("imember");
				int id = bean.getId();
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
				String mail = request.getParameter("email");

				int update = dao.updateMember(id, name, address, tel, mail);
				Member memberbean2 = dao.findById(id);
				request.setAttribute("upmember", memberbean2);

				gotoPage(request, response, "/message_completed_henkou.jsp");

			} else if (action.equals("delete")) { //退会
				Member bean = (Member) session.getAttribute("imember");
				int id = bean.getId();
				int delete = dao.updateMember(id);

				Member memberbean3 = dao.findById(id);
				request.setAttribute("imembers", memberbean3);

				gotoPage(request, response, "/message_completed_taikai.jsp");
			} else if (action.equals("canceltaikai")) { //退会

				gotoPage(request, response, "/kaiin_kensaku.jsp");

			} else {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} catch (DAOException | NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/kaiin_kensaku.jsp");
		}
	}
}
