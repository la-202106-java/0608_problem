package la.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.MemberBean;
import la.bean.PlanBean;
import la.dao.MembersDAOSub;
import la.dao.PlansDAOSub;

@WebServlet("/ShowPlanServlet")
public class ShowPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String action = request.getParameter("action");

		if (action == null) { // 最初のアクセス
			gotoPage(request, response, "/top.jsp");
		} else if (action.equals("registration")) { // 会員登録ボタンクリック時
			gotoPage(request, response, "reservation.jsp");
		} else if (action.equals("logout")) {
			session.setAttribute("isLogin", "false");
			gotoPage(request, response, "top.jsp");
		} else {
			request.setAttribute("message", "正しく操作してください。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String action = request.getParameter("action");

		if (action.equals("login")) { // ログイン画面でログインボタンクリック時
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			MembersDAOSub dao = new MembersDAOSub();
			MemberBean member = new MemberBean();
			member = dao.find(email);

			if (password.equals(member.getPassword())) {
				session.setAttribute("isLogin", "true");
				gotoPage(request, response, "/top.jsp");
			} else {
				// ログイン失敗時の処理書く
			}
		} else if (action.equals("plan")) { // 検索ボタンクリック時
			String checkIn = request.getParameter("checkIn");
			String checkOut = request.getParameter("checkOut");

			PlansDAOSub dao = new PlansDAOSub();
			List<PlanBean> plans = new ArrayList<PlanBean>();

			plans = dao.find(checkIn, checkOut);
			request.setAttribute("plans", plans);

			gotoPage(request, response, "/top.jsp");
		} else {
			request.setAttribute("message", "正しく操作してください。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
