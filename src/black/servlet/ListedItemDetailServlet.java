package black.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import black.bean.ListedItemBean;
import black.bean.MemberBean;
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
			String action = request.getParameter("action");
			ListedItemDAO dao = new ListedItemDAO();
			HttpSession session = request.getSession(false);

			if (action == null || action.length() == 0) {

				RequestDispatcher rd = request.getRequestDispatcher("/top.jsp");
				rd.forward(request, response);
			} else if (action.equals("detail")) {

				int id = Integer.parseInt(request.getParameter("item_code"));
				ListedItemBean item = dao.findItem(id);
				String userType = (String) session.getAttribute("user");
				if (userType.equals("member")) {
					MemberBean member = (MemberBean) session.getAttribute("logined");
					if (item.getSellerId() == member.getId()) {
						request.setAttribute("is_my_item", true);
					}
				}

				request.setAttribute("item", item);
				gotoPage(request, response, "/listedItemDetail.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラー");
			RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
			rd.forward(request, response);
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
