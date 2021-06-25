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
import la.bean.ReservationBean;
import la.dao.DAOException;
import la.dao.MembersDAO;
import la.dao.UserReservationDAO;

@WebServlet("/MembersServlet")
public class MembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MembersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if ("true".equals(session.getAttribute("isLogin"))) {
			try {
				request.setCharacterEncoding("UTF-8");
				String action = request.getParameter("action");
				MembersDAO dao = new MembersDAO();

				//全表示処理
				if (action == null || action.length() == 0) {
					List<MemberBean> list = dao.findAll();
					request.setAttribute("members", list);
					gotoPage(request, response, "/membersShow.jsp");
					//更新処理
				} else if (action.equals("update")) {
					//					//idが空欄であった場合intの「-1」とする
					//					String tmp_id = request.getParameter("id");
					//					if (tmp_id == "") {
					//						tmp_id = "-1";
					//					}
					//					int id = Integer.parseInt(tmp_id);
					MemberBean member = (MemberBean) session.getAttribute("member");
					//ログインしている会員のIDを入力
					int id = member.getId();
					String name = request.getParameter("name");
					String postal_code = request.getParameter("postal_code");
					String address = request.getParameter("address");
					String email_address = request.getParameter("email_address");
					String tel = request.getParameter("tel");
					dao.update(id, name, postal_code, address, email_address, tel);
					member = dao.searchMemberByID(id);
					session.setAttribute("member", member);
					gotoPage(request, response, "/membersShow.jsp");
					//退会処理
				} else if (action.equals("quit")) {
					MemberBean member = (MemberBean) session.getAttribute("member");
					//ログインしている会員のIDを入力
					int id = member.getId();
					dao.quit(id);
					session.invalidate();
					gotoPage(request, response, "/top2.jsp");
				} else if (action.equals("log")) {
					UserReservationDAO udao = new UserReservationDAO();
					MemberBean member = (MemberBean) session.getAttribute("member");
					List<ReservationBean> rlist = udao.searchReservation(member.getId());
					request.setAttribute("Reservations", rlist);
					gotoPage(request, response, "/reservationLog.jsp");
				} else if (action.equals("yoyakucancel")) {
					int yoyakuID = Integer.parseInt(request.getParameter("id"));
					UserReservationDAO udao = new UserReservationDAO();
					udao.quitReservation(yoyakuID);
					MemberBean member = (MemberBean) session.getAttribute("member");
					List<ReservationBean> rlist = udao.searchReservation(member.getId());
					request.setAttribute("message", "予約を削除しました");
					request.setAttribute("Reservations", rlist);
					gotoPage(request, response, "/reservationLog.jsp");
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
			gotoPage(request, response, "/top2.jsp");
		}

	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
