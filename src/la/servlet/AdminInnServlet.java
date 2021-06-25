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
 * Servlet implementation class AdminInnServlet
 */
@WebServlet("/admin/inn")
public class AdminInnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminInnServlet() {
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
				AdminInnDAO dao = new AdminInnDAO();
				if (action == null || action.length() == 0) {
					gotoPage(request, response, "/adminTop.jsp");
				} else if (action.equals("add")) {
					String name = request.getParameter("name");
					int classCode = Integer.parseInt(request.getParameter("class_code"));
					String postalCode = request.getParameter("postal_code");
					String address = request.getParameter("address");
					String inTime = request.getParameter("inTime") + ":00";
					String outTime = request.getParameter("outTime") + ":00";
					//				java.sql.Time inTime = java.sql.Time.valueOf(request.getParameter("inTime") + ":00");
					//				java.sql.Time outTime = java.sql.Time.valueOf(request.getParameter("outTime") + ":00");
					dao.addInn(name, classCode, postalCode, address, inTime, outTime);
					request.setAttribute("message", name + "を追加しました。");
					gotoPage(request, response, "/adminConfirm.jsp");
				} else if (action.equals("search")) {
					String name = request.getParameter("name");
					String check = request.getParameter("ck01");
					List<InnBean> list = dao.searchInn(name, check != null);
					request.setAttribute("name", name);
					request.setAttribute("Inns", list);
					gotoPage(request, response, "/adminSearchInn.jsp");
				} else if (action.equals("edit")) {
					int id = Integer.parseInt(request.getParameter("id"));
					String name = request.getParameter("name");
					int classCode = Integer.parseInt(request.getParameter("class_code"));
					String postalCode = request.getParameter("postal_code");
					String address = request.getParameter("address");
					String inTime = request.getParameter("inTime");
					String outTime = request.getParameter("outTime");
					//				java.sql.Time inTime = java.sql.Time.valueOf(request.getParameter("inTime") + ":00");
					//				java.sql.Time outTime = java.sql.Time.valueOf(request.getParameter("outTime") + ":00");
					InnBean target = new InnBean(id, name, classCode, postalCode, address, inTime, outTime);
					request.setAttribute("innTarget", target);
					gotoPage(request, response, "/adminUpdateInn.jsp");

				} else if (action.equals("update")) {
					int id = Integer.parseInt(request.getParameter("id"));
					String name = request.getParameter("name");
					int classCode = Integer.parseInt(request.getParameter("class_code"));
					String postalCode = request.getParameter("postal_code");
					String address = request.getParameter("address");
					String inTime = request.getParameter("inTime");
					String outTime = request.getParameter("outTime");
					if (inTime.matches("[0-9]{2}:[0-9]{2}")) {
						inTime += ":00";
					}
					if (outTime.matches("[0-9]{2}:[0-9]{2}")) {
						outTime += ":00";
					}
					dao.updateInn(id, name, classCode, postalCode, address, inTime, outTime);
					request.setAttribute("message", name + "を更新しました。");
					gotoPage(request, response, "/adminConfirm.jsp");
				}

				else {
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
