package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.MemberBean;
import la.bean.PlanBean;
import la.bean.ReservationBean;
import la.dao.PlansDAOSub;
import la.dao.ReservationsDAOSub;

@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String planId = request.getParameter("planId");

		String isLogin = (String) session.getAttribute("isLogin");
		MemberBean member = (MemberBean) session.getAttribute("member");
		String checkIn = (String) session.getAttribute("checkIn");
		String checkOut = (String) session.getAttribute("checkOut");

		if (isLogin.equals("true")) {
			ReservationBean reservation = new ReservationBean();
			reservation.setMemberId(member.getId());
			reservation.setPlanId(Integer.parseInt(planId));
			reservation.setInDate(checkIn);
			reservation.setOutDate(checkOut);
			reservation.setRoom(1); // ここどう処理する？

			ReservationsDAOSub dao = new ReservationsDAOSub();
			dao.add(reservation);

			gotoPage(request, response, "/complete.jsp");
		} else {
			PlanBean plan = new PlanBean();
			PlansDAOSub dao = new PlansDAOSub();
			plan = dao.find(Integer.parseInt(planId));

			session.setAttribute("plan", plan); // セッションに保存されたplanがnullじゃない場合、ログイン後、予約確定画面へ行く処理をするため

			gotoPage(request, response, "/login.jsp");
		}
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
