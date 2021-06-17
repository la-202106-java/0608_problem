package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.AdminItemBean;
import la.dao.AdminItemDAO;
import la.dao.DAOException;

@WebServlet("/AdminItemServlet")
public class AdminItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		try {
			String action = request.getParameter("action");
			AdminItemDAO dao = new AdminItemDAO();

			if (action == null || action.length() == 0) {
				List<AdminItemBean> list = dao.findAll();

				request.setAttribute("items", list);

				RequestDispatcher rd = request.getRequestDispatcher("/items.jsp");
				rd.forward(request, response);
			} else if (action.equals("regist")) {
				RequestDispatcher rd = request.getRequestDispatcher("/addItem.jsp");
				rd.forward(request, response);
			} else if (action.equals("add")) {
				int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));

				dao.addItem(categoryCode, name, price);

				List<AdminItemBean> list = dao.findAll();

				request.setAttribute("items", list);

				RequestDispatcher rd = request.getRequestDispatcher("/items.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("message", "正しく操作してください。");
				RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
				rd.forward(request, response);
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
