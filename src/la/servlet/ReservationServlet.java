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
import la.bean.ReservationBean;
import la.dao.DAOException;
import la.dao.ReservationsDAOSub;

@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String action = request.getParameter("action");

		try {
			if (action.equals("reservation")) { // トップページで予約ボタンをクリックしたとき
				int i = Integer.parseInt(request.getParameter("i")) - 1; // セッションスコープ
				PlanBean plan = new PlanBean();
				List<PlanBean> plans = (ArrayList<PlanBean>) session.getAttribute("plans"); // 型変換の前のチェック未実装
				plan = plans.get(i);

				session.setAttribute("plan", plan);

				gotoPage(request, response, "reservation.jsp");
			} else if (action.equals("confirm")) { // 予約画面で確定ボタンをクリックしたとき
				String isLogin = (String) session.getAttribute("isLogin");
				PlanBean plan = (PlanBean) session.getAttribute("plan");

				if (isLogin.equals("true")) { // ログイン状態のとき
					int roomNum = Integer.parseInt(request.getParameter("roomNum"));

					MemberBean member = (MemberBean) session.getAttribute("member");
					String checkIn = (String) session.getAttribute("checkIn");
					String checkOut = (String) session.getAttribute("checkOut");

					ReservationBean reservation = new ReservationBean();
					reservation.setMemberId(member.getId());
					reservation.setPlanId(plan.getPlanId());
					reservation.setInDate(checkIn);
					reservation.setOutDate(checkOut);
					reservation.setRoom(roomNum);

					ReservationsDAOSub dao = new ReservationsDAOSub();
					dao.add(reservation);

					request.setAttribute("reservation", reservation);

					gotoPage(request, response, "/complete.jsp");
				} else {
					// 後で考える。セッションに保存されたplanがnullじゃない場合、ログイン後、予約確定画面へ行く処理をするため
					gotoPage(request, response, "/login.jsp");
				}
			} else {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
			}

		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
		}
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
