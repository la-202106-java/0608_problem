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
import la.dao.DAOException;
import la.dao.MembersDAOSub;
import la.dao.PlansDAOSub;

@WebServlet("/ShowPlanServlet2")
public class ShowPlanServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String action = request.getParameter("action");

		if (action == null) { // 最初のアクセス
			gotoPage(request, response, "/top2.jsp");
		} else if (action.equals("logout")) { // ログアウトリンククリック時
			// session.setAttribute("isLogin", "false");
			session.invalidate(); // セッションスコープのデータをすべて削除
			gotoPage(request, response, "top2.jsp");
		} else if (action.equals("complete")) { // 予約完了画面でトップページが押された場合
			// sessionに保存した情報のうち必要のない情報、planなど？をnullとする
			// いらん処理してるかも？セッションに何が保存されているか要確認
			session.setAttribute("checkIn", null);
			session.setAttribute("checkOut", null);
			session.setAttribute("plans", null);
			session.setAttribute("plan", null);
			session.setAttribute("innName", null);
			session.setAttribute("place", null);
			session.setAttribute("lower", null);
			session.setAttribute("upper", null);
			gotoPage(request, response, "top2.jsp");
		} else {
			request.setAttribute("message", "正しく操作してください。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String action = request.getParameter("action");

		try {
			if (action.equals("login")) { // ログイン画面でログインボタンクリック時
				String email = request.getParameter("email");
				String password = request.getParameter("password");

				MembersDAOSub dao = new MembersDAOSub();
				MemberBean member = new MemberBean();
				member = dao.find(email);

				if (member != null && password.equals(member.getPassword()) && member.getQuiteDate() == null) {
					session.setAttribute("isLogin", "true");
					session.setAttribute("member", member);

					if (session.getAttribute("plan") != null) {
						gotoPage(request, response, "/reservation.jsp");
						return;
					}

					gotoPage(request, response, "/top2.jsp");
				} else {
					request.setAttribute("error", "メールアドレスとパスワードが一致しませんでした");
					gotoPage(request, response, "/login.jsp");
				}
			} else if (action.equals("registration")) { // 会員登録ボタンクリック時
				String password = request.getParameter("password");
				String name = request.getParameter("name");
				String postalCode = request.getParameter("postalCode");
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
				String email = request.getParameter("email");
				String birthday = request.getParameter("birthday");

				MemberBean member = new MemberBean();
				member.setPassword(password);
				member.setName(name);
				member.setPostalCode(postalCode);
				member.setAddress(address);
				member.setTel(tel);
				member.setEmailAddress(email);
				member.setBirthDate(birthday);

				MembersDAOSub dao = new MembersDAOSub();
				dao.registration(member);

				// idとかとってきたいからデータベースから情報とってきなおす
				member = dao.find(email);

				session.setAttribute("isLogin", "true");
				session.setAttribute("member", member);

				if (session.getAttribute("plan") != null) {
					gotoPage(request, response, "/reservation.jsp");
					return;
				}

				gotoPage(request, response, "/top2.jsp");
			} else if (action.equals("plan")) { // 検索ボタンクリック時（チェックイン・アウト）
				String checkIn = request.getParameter("checkIn");
				String checkOut = request.getParameter("checkOut");

				session.setAttribute("checkIn", checkIn);
				session.setAttribute("checkOut", checkOut);

				if (checkIn == null || checkOut == null || checkIn.length() == 0 || checkOut.length() == 0) {
					request.setAttribute("error", "チェックイン日とチェックアウト日を入力してください");
					session.setAttribute("plans", null);
					session.setAttribute("plan", null);
					gotoPage(request, response, "/top2.jsp");
					return;
				}

				PlansDAOSub dao = new PlansDAOSub();
				List<PlanBean> plans = new ArrayList<PlanBean>();

				plans = dao.find(checkIn, checkOut);

				session.setAttribute("plans", plans);

				gotoPage(request, response, "/top2.jsp");
			} else if (action.equals("narrow")) {
				String innName = request.getParameter("innName");
				String place = request.getParameter("place");
				String StringLower = request.getParameter("lower");
				request.setAttribute("innName", innName);
				request.setAttribute("place", place);
				request.setAttribute("lower", StringLower);
				if (StringLower == null || StringLower.length() == 0) {
					StringLower = "0";
				}
				String StringUpper = request.getParameter("upper");
				request.setAttribute("upper", StringUpper);
				if (StringUpper == null || StringUpper.length() == 0) {
					StringUpper = "100000000";
				}
				int lower = Integer.parseInt(StringLower);
				int upper = Integer.parseInt(StringUpper);

				String checkIn = (String) session.getAttribute("checkIn");
				String checkOut = (String) session.getAttribute("checkOut");

				PlansDAOSub dao = new PlansDAOSub();
				List<PlanBean> plans = new ArrayList<PlanBean>();

				plans = dao.find(checkIn, checkOut, innName, place, lower, upper);

				session.setAttribute("plans", plans);
				gotoPage(request, response, "/top2.jsp");
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
