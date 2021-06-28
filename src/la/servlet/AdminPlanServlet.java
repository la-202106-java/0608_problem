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

import la.bean.InnBean;
import la.bean.PlanBean;
import la.dao.AdminInnDAO;
import la.dao.AdminPlanDAO;
import la.dao.DAOException;

/**
 * Servlet implementation class AdminPlanServlet
 */
@WebServlet("/admin/plan")
public class AdminPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminPlanServlet() {
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
			try {
				request.setCharacterEncoding("UTF-8");
				String action = request.getParameter("action");
				AdminPlanDAO dao = new AdminPlanDAO();
				if (action == null || action.length() == 0) {
					gotoPage(request, response, "/adminTop.jsp");
				} else if (action.equals("add")) {
					int innID = Integer.parseInt(request.getParameter("id"));
					String content = request.getParameter("contents");
					int fee = Integer.parseInt(request.getParameter("fee"));
					int roomMax = Integer.parseInt(request.getParameter("room"));
					String imgUrl = request.getParameter("picture");
					int row = dao.addPlan(innID, content, fee, roomMax, imgUrl);
					request.setAttribute("message", row + "件追加しました。");
					gotoPage(request, response, "/adminConfirm.jsp");
				} else if (action.equals("search")) {
					String name = request.getParameter("name");
					String check = request.getParameter("ck01");
					List<PlanBean> list = dao.searchPlan(name, check != null);
					request.setAttribute("name", name);
					request.setAttribute("Plans", list);
					gotoPage(request, response, "/adminSearchPlan.jsp");
				} else if (action.equals("edit")) {
					int PlanID = Integer.parseInt(request.getParameter("planid"));
					int innID = Integer.parseInt(request.getParameter("id"));
					String content = request.getParameter("contents");
					String StringFee = request.getParameter("fee");
					String rmax = request.getParameter("room");
					String imgUrl = request.getParameter("picture");
					if (content == null || content.length() == 0 || StringFee == null || StringFee.length() == 0
							|| rmax == null || rmax.length() == 0 || imgUrl == null || imgUrl.length() == 0) {
						gotoPage(request, response, "/adminSearchPlan.jsp");
						return;
					}
					int fee = Integer.parseInt(StringFee);
					int roomMax = Integer.parseInt(rmax);

					PlanBean target = new PlanBean(innID, content, fee, roomMax, imgUrl);
					target.setPlanId(PlanID);
					AdminInnDAO idao = new AdminInnDAO();
					List<InnBean> list = idao.searchInn("", false);
					request.setAttribute("Inns", list);
					request.setAttribute("planTarget", target);
					gotoPage(request, response, "/adminUpdatePlan.jsp");
				} else if (action.equals("update")) {
					int PlanID = Integer.parseInt(request.getParameter("planid"));
					int innID = Integer.parseInt(request.getParameter("id"));
					String content = request.getParameter("contents");
					int fee = Integer.parseInt(request.getParameter("fee"));
					int roomMax = Integer.parseInt(request.getParameter("room"));
					String imgUrl = request.getParameter("picture");
					dao.updatePlan(PlanID, innID, content, fee, roomMax, imgUrl);
					request.setAttribute("message", "プランID：" + PlanID + "を更新しました。");
					gotoPage(request, response, "/adminConfirm.jsp");
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
		doGet(request, response);
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
