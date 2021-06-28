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

import la.bean.PlanBean;
import la.bean.ReservationBean;
import la.dao.AdminInnDAO;
import la.dao.AdminPlanDAO;
import la.dao.AdminReservationDAO;
import la.dao.DAOException;
import la.dao.MembersDAO;

/**
 * Servlet implementation class AdminDeleteServlet
 */
@WebServlet("/AdminDeleteServlet")
public class AdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && "true".equals(session.getAttribute("isAdminLogin"))) {
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");
			try {
				if (action == null || action.length() == 0) {
					gotoPage(request, response, "/adminTop.jsp");
				} else if (action.equals("kakunin")) {
					String id = request.getParameter("id");
					String from = request.getParameter("from");
					request.setAttribute("id", id);
					request.setAttribute("from", from);
					gotoPage(request, response, "/adminDelete.jsp");
				} else if (action.equals("delete")) {
					String from = request.getParameter("from");
					if (from == null || from.length() == 0) {
						gotoPage(request, response, "/adminTop.jsp");
					} else if (from.equals("member")) {
						int id = Integer.parseInt(request.getParameter("id"));
						MembersDAO dao = new MembersDAO();
						dao.quit(id);
						request.setAttribute("message", "会員番号：" + id + "を削除しました。");
						gotoPage(request, response, "/adminConfirm.jsp");
					} else if (from.equals("inn")) {
						String name = request.getParameter("id");
						AdminInnDAO idao = new AdminInnDAO();
						AdminPlanDAO pdao = new AdminPlanDAO();
						AdminReservationDAO rdao = new AdminReservationDAO();
						List<PlanBean> list = pdao.searchPlan(name, false);
						idao.quitInn(name);
						String message = "宿名：" + name;
						List<ReservationBean> rlist = new ArrayList<ReservationBean>();
						for (int i = 0; i < list.size(); i++) {
							int pid = list.get(i).getPlanId();
							message = message + "とプランID:" + pid;
							pdao.quitPlan(pid);
							rlist.addAll(rdao.searchReservation(pid));
						}
						message += "を削除しました";
						request.setAttribute("message", message);
						session.setAttribute("Reservations", rlist);
						gotoPage(request, response, "/adminConfirmReservation.jsp");
					} else if (from.equals("plan")) {
						int planID = Integer.parseInt(request.getParameter("id"));
						AdminPlanDAO dao = new AdminPlanDAO();
						dao.quitPlan(planID);
						AdminReservationDAO rdao = new AdminReservationDAO();
						List<ReservationBean> list = rdao.searchReservation(planID);
						request.setAttribute("message", "プランID：" + planID + "を削除しました。");
						session.setAttribute("Reservations", list);
						gotoPage(request, response, "/adminConfirmReservation.jsp");
					} else {
						request.setAttribute("message", "正しく操作してください。");
						gotoPage(request, response, "/errInternal.jsp");
					}

				} else if (action.equals("cancel")) {
					String from = request.getParameter("from");
					if (from == null || from.length() == 0) {
						gotoPage(request, response, "/adminTop.jsp");
					} else if (from.equals("member")) {
						gotoPage(request, response, "/adminMember.jsp");
					} else if (from.equals("inn")) {
						gotoPage(request, response, "/adminSearchInn.jsp");
					} else if (from.equals("plan")) {
						gotoPage(request, response, "/adminSearchPlan.jsp");
					} else {
						gotoPage(request, response, "/adminTop.jsp");
					}

				} else if (action.equals("yoyakuok") || action.equals("yoyakucancel")) {
					List<ReservationBean> list = (List<ReservationBean>) session.getAttribute("Reservations");
					int yoyakuID = Integer.parseInt(request.getParameter("id"));
					int no = Integer.parseInt(request.getParameter("no"));
					list.remove(no - 1);
					if (action.equals("yoyakucancel")) {
						AdminReservationDAO rdao = new AdminReservationDAO();
						rdao.quitReservation(yoyakuID);
					}
					session.setAttribute("Reservations", list);
					gotoPage(request, response, "/adminConfirmReservation.jsp");
				} else if (action.equals("searchReservation")) {
					AdminReservationDAO rdao = new AdminReservationDAO();
					String name = request.getParameter("name");
					List<ReservationBean> list = rdao.searchReservation(name);
					request.setAttribute("ReservationsByName", list);
					gotoPage(request, response, "/adminSearchReservation.jsp");
				} else if (action.equals("reservationCancel")) {
					AdminReservationDAO rdao = new AdminReservationDAO();
					int yoyakuID = Integer.parseInt(request.getParameter("id"));
					rdao.quitReservation(yoyakuID);
					request.setAttribute("message", "予約ID：" + yoyakuID + "を削除しました。");
					gotoPage(request, response, "/adminSearchReservation.jsp");
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
			request.setAttribute("error", "ログインしていません");
			gotoPage(request, response, "/adminLogin.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
