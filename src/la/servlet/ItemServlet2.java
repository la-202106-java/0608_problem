package la.servlet;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.ItemBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

@WebServlet("/ItemServlet2")
public class ItemServlet2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");
			HttpSession session = request.getSession();
			ItemDAO dao = new ItemDAO();
			if (action == null || action.length() == 0) {
				//List<ItemBean> list = dao.findAll(); //課題5
				List<ItemBean> list = dao.findAll2();

				request.setAttribute("items", list);
				//gotoPage(request, response, "/showItem.jsp"); //課題5
				gotoPage(request, response, "/showItem2.jsp");
			} else if (action.equals("add")) { //追加
				int category_code = Integer.parseInt(request.getParameter("category_code"));
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				dao.addItem2(name, price, category_code);
				List<ItemBean> list = dao.findAll2();
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem2.jsp");
			} else if (action.equals("sort")) { //ソート
				String key = request.getParameter("key");
				@SuppressWarnings("unchecked")
				List<ItemBean> list = (List<ItemBean>) session.getAttribute("items");
				if (list == null) {
					if (key.equals("price_asc")) {
						list = dao.sortPrice(true);
					} else {
						list = dao.sortPrice(false);
					}
					request.setAttribute("items", list);
				} else if (list != null && key.equals("price_asc")) { //ここにリスト(list)をソートする処理を入れる
					list.sort(Comparator.comparing(ItemBean::getPrice));
					session.setAttribute("items", list);
				} else { //ここにリスト(list)をソートする処理を入れる
					list.sort(Comparator.comparing(ItemBean::getPrice).reversed());
					session.setAttribute("items", list);
				}
				gotoPage(request, response, "/showItem.jsp");
			} else if (action.equals("search")) { //検索
				int min_price = 0;
				int max_price = 99999;
				String name = "";
				if (request.getParameter("min_price") != null && request.getParameter("min_price").length() != 0) {
					min_price = Integer.parseInt(request.getParameter("min_price"));
					request.setAttribute("min_prices", min_price);
				}
				if (request.getParameter("max_price") != null && request.getParameter("max_price").length() != 0) {
					max_price = Integer.parseInt(request.getParameter("max_price"));
					request.setAttribute("max_prices", max_price);
				}
				if (request.getParameter("name") != null && request.getParameter("name").length() != 0) {
					name = request.getParameter("name");
					request.setAttribute("names", name);
				}

				List<ItemBean> list = dao.findByPrice(max_price, min_price, name);
				session.setAttribute("items", list); //requestからsessionに変更 add
				gotoPage(request, response, "/showItem.jsp");
			} else if (action.equals("delete")) { //削除
				int code = Integer.parseInt(request.getParameter("code"));
				dao.deleteByPrimaryKey(code);
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");
			} else if (action.equals("update")) { //更新
				int code = Integer.parseInt(request.getParameter("code"));
				int price = Integer.parseInt(request.getParameter("price"));
				dao.UpdateItem(code, price);
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");
			} else if (action.equals("regist")) {//登録する 新しく追加したところ
				List<ItemBean> list = dao.findAll2();

				request.setAttribute("items", list);
				gotoPage(request, response, "/addItem.jsp");
			}

			else {
				request.setAttribute("message", "正しく操作してください");
				gotoPage(request, response, "/showItem.jsp");
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
