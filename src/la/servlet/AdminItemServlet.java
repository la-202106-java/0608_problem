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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			String action = request.getParameter("action");

			AdminItemDAO dao = new AdminItemDAO();
			//show all items
			if (action == null || action.length() == 0) {

				List<AdminItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/items.jsp");

				//regist
			} else if (action.equals("regist")) {
				gotoPage(request, response, "/addItem.jsp");

				//add
			} else if (action.equals("add")) {
				int category_code = Integer.parseInt(request.getParameter("category_code"));
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				dao.addItem(category_code, name, price);

				List<AdminItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/items.jsp");

				//edit
			} else if (action.equals("edit")) {
				int code = Integer.parseInt(request.getParameter("code"));
				request.setAttribute("code", code);
				gotoPage(request, response, "/updateItem.jsp");

				//update
			} else if (action.equals("update")) {
				int code = Integer.parseInt(request.getParameter("code"));
				int category_code = Integer.parseInt(request.getParameter("category_code"));
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				dao.updateItem(code, category_code, name, price);

				List<AdminItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/items.jsp");

				//delete
			} else if (action.equals("delete")) {
				int code = Integer.parseInt(request.getParameter("code"));
				dao.deleteByPrimaryKey(code);

				List<AdminItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/items.jsp");

			} else {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
			}

		} catch (

		DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
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
		doGet(request, response);
	}

}
