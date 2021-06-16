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
import la.bean.ItemBean;
import la.dao.DAOException;
import la.dao.ItemDAO;

/**
 * Servlet implementation class ShowItemServlet
 */
@WebServlet("/ShowItemServlet")
public class ShowItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int PAGE_SIZE = 10;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private List<ItemBean> paging(int currentPage, List<ItemBean> list)
			throws ServletException, IOException {
		//page begins from 0
		int count = list.size();
		int begin = currentPage * PAGE_SIZE;
		int end = 0;
		if ((currentPage + 1) * PAGE_SIZE <= count) {
			end = (currentPage + 1) * PAGE_SIZE;
		} else {
			end = count;
		}
		List<ItemBean> pagedList = list.subList(begin, end);
		return pagedList;
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			ItemDAO dao = new ItemDAO();
			String action = request.getParameter("action");
			System.out.println(action);
			if (action == null || action.length() == 0 || action.equals("top")) {
				gotoPage(request, response, "/top.jsp");
			} else if (action.equals("list")) {
				int category = Integer.parseInt(request.getParameter("code"));
				int currentNum = Integer.parseInt(request.getParameter("page"));
				List<ItemBean> list = dao.findByCategory(category);
				int totalPage = (int) Math.ceil((double) list.size() / PAGE_SIZE);
				request.setAttribute("totalPage", totalPage);
				int totalItem = list.size();
				request.setAttribute("totalItem", totalItem);
				List<ItemBean> pagedList = paging(currentNum, list);
				request.setAttribute("items", pagedList);
				gotoPage(request, response, "/list.jsp");
			} else if (action.equals("detail")) {
				int code = Integer.parseInt(request.getParameter("code"));
				ItemBean bean = dao.findByPrimaryKey(code);
				request.setAttribute("bean", bean);
				gotoPage(request, response, "/item.jsp");

			} else if (action.equals("search")) {
				String name = request.getParameter("name");
				int currentNum = Integer.parseInt(request.getParameter("page"));
				List<ItemBean> list = dao.searchByName(name);
				int totalPage = (int) Math.ceil((double) list.size() / PAGE_SIZE);
				request.setAttribute("totalPage", totalPage);
				List<ItemBean> pagedList = paging(currentNum, list);
				int totalItem = list.size();
				request.setAttribute("totalItem", totalItem);
				request.setAttribute("items", pagedList);
				gotoPage(request, response, "/list.jsp");

			} else {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	public void init() throws ServletException {
		try {
			ItemDAO dao = new ItemDAO();
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
