package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.CategoryBean;
import la.bean.ItemBean2;
import la.dao.DAOException;
import la.dao.ItemDAO2;

/**
 * Servlet implementation class ShowItemServlet
 */
@WebServlet("/ShowItemServlet")
public class ShowItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String action = request.getParameter("action");
			if (action == null || action.length() == 0 || action.equals("top")) {
				gotoPage(request, response, "/top.jsp");
			} else if (action.equals("list")) {
				int categoryCode = Integer.parseInt(request.getParameter("code"));
				ItemDAO2 dao = new ItemDAO2();
				List<ItemBean2> list = dao.findByCategory(categoryCode);
				request.setAttribute("items", list);
				gotoPage(request, response, "/list.jsp");
			} else if (action.equals("detail")) {
				int item_code = Integer.parseInt(request.getParameter("code"));
				ItemDAO2 dao = new ItemDAO2();
				List<ItemBean2> list = dao.showDetail(item_code);
				request.setAttribute("items", list);
				gotoPage(request, response, "/item.jsp");
			} else if (action.equals("search")) {
				String keyword = request.getParameter("keyword");
				ItemDAO2 dao = new ItemDAO2();
				List<ItemBean2> list = dao.findByName(keyword);
				request.setAttribute("items", list);
				gotoPage(request, response, "/list.jsp");
			} else {
				request.setAttribute("messgae", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("messgae", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	public void init() throws ServletException {
		try {
			ItemDAO2 dao = new ItemDAO2();
			List<CategoryBean> list = dao.findAllCategory();
			getServletContext().setAttribute("categories", list);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServletException();
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
