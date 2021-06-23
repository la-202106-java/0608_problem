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
		//退会チェック選択画面
		if (action.equals("leave")) {
			if (session.getAttribute("user").equals("member")) {
				gotoPage(request, response, "/memberLeaveCheck.jsp");
			} else if (session.getAttribute("user").equals("admin")) {
				MemberBean beannew = new MemberBean();
				beannew.setId(Integer.parseInt(request.getParameter("id")));
				beannew.setName(request.getParameter("name"));
				//				beannew.setAddress(request.getParameter("address"));
				//				beannew.setTel(request.getParameter("tel"));
				//				beannew.setEmail(request.getParameter("email"));
				request.setAttribute("member", beannew);
				gotoPage(request, response, "/memberLeaveCheck.jsp");
			} else {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
			}

		} else if (action.equals("doleave")) {
			MemberDAO dao = null;
			try {
				dao = new MemberDAO();
				dao.memberQuit(Integer.parseInt(request.getParameter("id")));
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
			if (session.getAttribute("user").equals("member")) {
				gotoPage(request, response, "/memberDetail.jsp");
			} else if (session.getAttribute("user").equals("admin")) {
				MemberBean bean = new MemberBean();
				bean.setId(Integer.parseInt(request.getParameter("id")));
				bean.setName(request.getParameter("name"));
				bean.setAddress(request.getParameter("address"));
				bean.setTel(request.getParameter("tel"));
				bean.setEmail(request.getParameter("email"));
				request.setAttribute("member", bean);
				gotoPage(request, response, "/memberSearch.jsp");

			}
		}
	}

}
