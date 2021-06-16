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

import la.bean.AdminItemBean;
import la.dao.AdminItemDAO;
import la.dao.DAOException;

@WebServlet("/AdminItemServlet")

public class AdminItemServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");
			//セッション作成
			HttpSession session = request.getSession(false);
			if (session == null) {
				request.getSession();
			}

			AdminItemDAO dao = new AdminItemDAO();
			if (action == null || action.length() == 0) {
				List<AdminItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/items.jsp");
				return;
			} else if (action.equals("regist")) {
				gotoPage(request, response, "/addItem.jsp");
				return;
			} else if (action.equals("add")) {
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				int category_code = Integer.parseInt(request.getParameter("category_code"));
				dao.registItem(category_code, name, price);
				//追加後全レコード表示
				List<AdminItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/items.jsp");
				return;
			}

		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
			rd.forward(request, response);
		}
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
