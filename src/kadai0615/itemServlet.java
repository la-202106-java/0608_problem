package kadai0615;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.ItemBean2;
import la.dao.DAOE;
import la.dao.ItemDAO_kadai2;

/**
 * Servlet implementation class itemServlet
 */
@WebServlet("/itemServlet")
public class itemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");
			//モデルのDAOを作成
			ItemDAO_kadai2 dao = new ItemDAO_kadai2();

			if (action == null || action.length() == 0) {
				List<ItemBean2> list = dao.findAll();
				request.setAttribute("items", list);
				RequestDispatcher rd = request.getRequestDispatcher("/items.jsp");
				rd.forward(request, response);
			} else if (action.equals("regist")) {

				RequestDispatcher rd = request.getRequestDispatcher("/addItem.jsp");
				rd.forward(request, response);

			}

			//追加するとき
			//			else if (action.equals("add")) {
			//				String name = request.getParameter("name");
			//				int price = Integer.parseInt(request.getParameter("price"));
			//				dao.addItem(name, price);
			//				List<ItemBean> list = dao.findAll();
			//				request.setAttribute("items", list);
			//				gotoPage(request, response, "/showItem2.jsp");
			//
			//				//valueにsort
			//			}

		} catch (DAOE e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラー");
			RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
			rd.forward(request, response);
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
}
