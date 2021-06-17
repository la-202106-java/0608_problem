package kadai0615;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.ItemBean;
import la.dao.DAOE;
import la.dao.ItemDAO2;

/**
 * Servlet implementation class ItemServlet
 */
@WebServlet("/itemServlet2")
public class itemServlet2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");

			//モデルのDAOを作成
			ItemDAO2 dao = new ItemDAO2();

			if (action == null || action.length() == 0) {
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				//showItem2.jspに転送
				gotoPage(request, response, "/showItem2.jsp");

				//valueにaddが記載されていたら
			} else if (action.equals("add")) {
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				dao.addItem(name, price);
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem2.jsp");

				//valueにsort
			} else if (action.equals("sort")) {
				String key = request.getParameter("key");
				List<ItemBean> list = dao.findAll();
				if (key.equals("price_asc")) {
					list = dao.sortPrice(true);
				} else {
					list = dao.sortPrice(false);
				}

				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem2.jsp");

				//valueにserch
			} else if (action.equals("serch")) {

				int minP = Integer.parseInt(request.getParameter("minPrice"));
				int maxP = Integer.parseInt(request.getParameter("maxPrice"));

				request.setAttribute("minP", minP);
				request.setAttribute("maxP", maxP);

				List<ItemBean> list = dao.findByPrice(minP, maxP);

				request.setAttribute("items", list);
				RequestDispatcher rd = request.getRequestDispatcher("showItem2.jsp");
				rd.forward(request, response);

			} //valueにdelete
			else if (action.equals("delete")) {
				int code = Integer.parseInt(request.getParameter("code"));
				dao.deleteByPrimaryKey(code);
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem2.jsp");

			} else {
				request.setAttribute("message", "正しく操作できません");
				gotoPage(request, response, "/errInternal.jsp");

			}
		} catch (DAOE e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラー");
			gotoPage(request, response, "errInternal.jsp");
		}
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);

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
