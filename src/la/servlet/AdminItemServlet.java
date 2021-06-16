package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.ItemBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

/**
 * Servlet implementation class ItemServlet
 */
@WebServlet("/AdminItemServlet")
public class AdminItemServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//モデルを使って全商品を取得する
		try {
			request.setCharacterEncoding("UTF-8");
			//パラメータの解析
			String action = request.getParameter("action");
			ItemDAO dao = new ItemDAO();

			if (action == null || action.length() == 0) {
				List<ItemBean> list = dao.findAll2();
				//Listをリクエストスコープに入れてJSPへフォワードする
				//session.setAttribute("items", list);
				request.setAttribute("items", list);
				gotoPage(request, response, "/items.jsp");
			} else if (action.equals("regist")) {
				gotoPage(request, response, "/addItem.jsp");
			} else if (action.equals("add")) {
				int category = Integer.parseInt(request.getParameter("category"));
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				dao.addItem2(category, name, price);
				// 追加後、全レコード表示
				List<ItemBean> list = dao.findAll2();
				// Listをリクエストスコープに入れてJSPへフォーワードする
				request.setAttribute("items", list);
				gotoPage(request, response, "/items.jsp");
			}

		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
			rd.forward(request, response);
		}
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
