package black.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import black.bean.ListedItemBean;
import black.bean.MemberBean;
import black.dao.DAOException;
import black.dao.DepartmentDAO;
import black.dao.ListedItemDAO;

/**
 * Servlet implementation class ListedItemDetailServlet
 */
@WebServlet("/ListedItemDetailServlet")
public class ListedItemDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			ListedItemDAO dao = new ListedItemDAO();
			HttpSession session = request.getSession(false);

			int id = Integer.parseInt(request.getParameter("item_id"));
			ListedItemBean item = dao.findItem(id);
			request.setAttribute("item", item);

			if (session != null) {
				String userType = (String) session.getAttribute("user");
				if (userType != null && userType.equals("member")) {
					MemberBean member = (MemberBean) session.getAttribute("logined");
					if (item != null && item.getSellerId() == member.getId()) {
						request.setAttribute("is_my_item", true);
					}
				}
			}

			gotoPage(request, response, "/listedItemDetail.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラー");
			RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
			rd.forward(request, response);
		}
	}

	public void init() throws ServletException {
		try {
			// 分類コード一覧は最初にアプリケーションスコープへ入れる
			DepartmentDAO dao = new DepartmentDAO();
			List<String> departments = dao.allDepartment();
			getServletContext().setAttribute("departments", departments);
			getServletContext().setAttribute("departments_size", departments.size());

			//状態（新品・未使用・中古）もアプリケーションスコープへ
			String[] conditions = { "新品", "未使用", "中古" };
			getServletContext().setAttribute("conditions", conditions);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
