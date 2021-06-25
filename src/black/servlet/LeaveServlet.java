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
import black.dao.DAOException;
import black.dao.MemberDAO;

/**
 * Servlet implementation class LeaveServlet
 */
@WebServlet("/LeaveServlet")
public class LeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		String user = (String) session.getAttribute("user");
		//退会チェック選択画面
		if (user.equals("member")) {
			gotoPage(request, response, "/memberLeaveCheck.jsp");
			MemberBean bean = (MemberBean) session.getAttribute("logined");
			request.setAttribute("leavemember", bean);
			gotoPage(request, response, "/memberLeaveCheck.jsp");
		} else if (user.equals("admin")) {
			int id = Integer.parseInt(request.getParameter("member_id"));
			MemberDAO dao;
			try {
				dao = new MemberDAO();
				MemberBean bean = dao.findMember(id);
				request.setAttribute("leavemember", bean);
			} catch (DAOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			gotoPage(request, response, "/memberLeaveCheck.jsp");

		} else {
			request.setAttribute("message", "正しく操作してください。");
			gotoPage(request, response, "/errInternal.jsp");
		}

	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		HttpSession session = request.getSession(false);
		if (action.equals("doleave")) {
			MemberDAO dao = null;
			String user = (String) session.getAttribute("user");
			try {
				if (user.equals("member")) {
					MemberBean member = (MemberBean) session.getAttribute("logined");
					int id = member.getId();
					dao = new MemberDAO();
					dao.memberQuit(id);
					session.removeAttribute("logined");
					session.removeAttribute("user");
					gotoPage(request, response, "/memberLeaveDone.jsp");
				} else if (session.getAttribute("user").equals("admin")) {
					int id = Integer.parseInt(request.getParameter("member_id"));
					dao = new MemberDAO();
					dao.memberQuit(id);
					gotoPage(request, response, "/memberLeaveDone.jsp");
				}

			} catch (NumberFormatException | DAOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			if (session.getAttribute("user").equals("member")) {
				session.removeAttribute("logined");
			}
			//退会完了ページに行く
			gotoPage(request, response, "/memberLeaveDone.jsp");

			//キャンセル　マイページに行く
		} else if (action.equals("cancel")) {
			String user = (String) session.getAttribute("user");
			if (user.equals("member")) {
				gotoPage(request, response, "/memberDetail.jsp");
			} else if (user.equals("admin")) {
				gotoPage(request, response, "/memberSearch.jsp");
			}
		}
	}

}
