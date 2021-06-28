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
import la.dao.AdminInnDAO;
import la.dao.DAOException;

/**
 * Servlet implementation class AdminTopServlet
 */
@WebServlet("/admin/top")
public class AdminTopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminTopServlet() {
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
			String link = request.getParameter("link");
			if (link == null || link.length() == 0) {
				gotoPage(request, response, "/adminTop.jsp");
			} else if (link.equals("member")) {
				gotoPage(request, response, "/adminMember.jsp");
			} else if (link.equals("addInn")) {
				gotoPage(request, response, "/adminAddInn.jsp");
			} else if (link.equals("searchInn")) {
				gotoPage(request, response, "/adminSearchInn.jsp");
			} else if (link.equals("addPlan")) {
				try {
					AdminInnDAO dao = new AdminInnDAO();
					List<InnBean> list = dao.searchInn("", false);
					request.setAttribute("Inns", list);
					gotoPage(request, response, "/adminAddPlan.jsp");
				} catch (DAOException e) {
					e.printStackTrace();
					request.setAttribute("message", "内部エラーが発生しました。");
					gotoPage(request, response, "/errInternal.jsp");
				}
			} else if (link.equals("searchPlan")) {
				gotoPage(request, response, "/adminSearchPlan.jsp");
			} else if (link.equals("reservation")) {
				gotoPage(request, response, "/adminSearchReservation.jsp");
			} else {
				gotoPage(request, response, "/adminTop.jsp");
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
