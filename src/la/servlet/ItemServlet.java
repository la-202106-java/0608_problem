package la.servlet;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/ItemServlet")

public class ItemServlet extends HttpServlet {

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

			ItemDAO dao = new ItemDAO();
			if (action == null || action.length() == 0) {
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");
				return;
			}
			//ソート
			else if (action.equals("sort")) {
				String key = request.getParameter("key");
				String name = (String) session.getAttribute("name");
				List<ItemBean> list;
				if (!name.isEmpty()) {
					if (key.equals("price_asc")) {
						list = dao.findByNameSortPrice(name, true);
					} else {
						list = dao.findByNameSortPrice(name, false);
					}
				} else {
					if (key.equals("price_asc")) {
						list = dao.sortPrice(true);
					} else {
						list = dao.sortPrice(false);
					}
				}

				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");
				return;
			}
			//追加
			else if (action.equals("add")) {
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				dao.addItem(name, price);
				//追加後全レコード表示
				List<ItemBean> list = dao.findAll();

				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");
				return;
			}

			//検索
			else if (action.equals("search")) {
				String namePart = request.getParameter("name");
				String SminPrice = request.getParameter("minPrice");
				String SmaxPrice = request.getParameter("maxPrice");
				if (!namePart.isEmpty()) {
					List<ItemBean> list1 = dao.findByName(namePart);
					if (SminPrice != "" && SmaxPrice != "") {
						int minPrice = Integer.parseInt(SminPrice);
						int maxPrice = Integer.parseInt(SmaxPrice);
						List<ItemBean> list2 = dao.findByPrice(minPrice, maxPrice);
						List<ItemBean> list = Compare(list1, list2);
						request.setAttribute("items", list);
						session_return(session, namePart, SminPrice, SmaxPrice);
						gotoPage(request, response, "/showItem.jsp");
						return;
					} else if (!SminPrice.isEmpty()) {
						int minPrice = Integer.parseInt(SminPrice);
						List<ItemBean> list2 = dao.findByminPrice(minPrice);
						List<ItemBean> list = Compare(list1, list2);
						request.setAttribute("items", list);
						session_return(session, namePart, SminPrice, SmaxPrice);
						gotoPage(request, response, "/showItem.jsp");
						return;
					} else if (!SmaxPrice.isEmpty()) {
						int maxPrice = Integer.parseInt(SmaxPrice);
						List<ItemBean> list2 = dao.findBymaxPrice(maxPrice);
						List<ItemBean> list = Compare(list1, list2);
						request.setAttribute("items", list);
						session_return(session, namePart, SminPrice, SmaxPrice);
						gotoPage(request, response, "/showItem.jsp");
						return;
					} else {
						List<ItemBean> list2 = dao.findAll();
						List<ItemBean> list = Compare(list1, list2);
						request.setAttribute("items", list);
						session_return(session, namePart, SminPrice, SmaxPrice);
						gotoPage(request, response, "/showItem.jsp");
						return;
					}

				} else {
					List<ItemBean> list1 = dao.findAll();
					if (!SminPrice.isEmpty() && !SmaxPrice.isEmpty()) {
						int minPrice = Integer.parseInt(SminPrice);
						int maxPrice = Integer.parseInt(SmaxPrice);
						List<ItemBean> list2 = dao.findByPrice(minPrice, maxPrice);
						List<ItemBean> list = Compare(list1, list2);
						request.setAttribute("items", list);
						session_return(session, namePart, SminPrice, SmaxPrice);
						gotoPage(request, response, "/showItem.jsp");
						return;
					} else if (!SminPrice.isEmpty()) {
						int minPrice = Integer.parseInt(SminPrice);
						List<ItemBean> list2 = dao.findByminPrice(minPrice);
						List<ItemBean> list = Compare(list1, list2);
						request.setAttribute("items", list);
						session_return(session, namePart, SminPrice, SmaxPrice);
						gotoPage(request, response, "/showItem.jsp");
						return;
					} else if (!SmaxPrice.isEmpty()) {
						int maxPrice = Integer.parseInt(SmaxPrice);
						List<ItemBean> list2 = dao.findBymaxPrice(maxPrice);
						List<ItemBean> list = Compare(list1, list2);
						request.setAttribute("items", list);
						session_return(session, namePart, SminPrice, SmaxPrice);
						gotoPage(request, response, "/showItem.jsp");
						return;
					} else {
						List<ItemBean> list2 = dao.findAll();
						List<ItemBean> list = Compare(list1, list2);
						request.setAttribute("items", list);
						session_return(session, namePart, SminPrice, SmaxPrice);
						gotoPage(request, response, "/showItem.jsp");
						return;
					}
				}
			}

			//削除
			else if (action.equals("delete")) {
				int code = Integer.parseInt(request.getParameter("code"));
				dao.deleteByPrimaryKey(code);
				//削除後、全レコードを表示
				List<ItemBean> list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/showItem.jsp");
				return;
			}

		} catch (

		DAOException e) {
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

	private List<ItemBean> Compare(List<ItemBean> listA, List<ItemBean> listB) {
		List<ItemBean> listC = new ArrayList<ItemBean>();
		for (ItemBean sampleA : listA) {
			for (ItemBean sampleB : listB) {
				if (sampleA.getCode() == sampleB.getCode()) {
					listC.add(sampleA);
					break;
				}
			}
		}
		return listC;
	}

	private void session_return(HttpSession session, String namePart, String minPrice, String maxPrice) {
		session.setAttribute("name", namePart);
		session.setAttribute("minPrice", minPrice);
		session.setAttribute("maxPrice", maxPrice);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
