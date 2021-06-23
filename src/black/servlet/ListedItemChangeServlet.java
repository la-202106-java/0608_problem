package black.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import black.bean.ListedItemBean;
import black.dao.DAOException;
import black.dao.ListedItemDAO;

/**
 * Servlet implementation class ListedItemChange
 */
@WebServlet("/ListedItemChangeServlet")
public class ListedItemChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");

		//教科書変更画面
		if (action.equals("change")) {
			int id = Integer.parseInt(request.getParameter("id"));
			ListedItemDAO dao = null;
			try {
				dao = new ListedItemDAO();
				ListedItemBean bean = dao.findItem(id);
				request.setAttribute("textbook", bean);
			} catch (DAOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

			gotoPage(request, response, "/listedItemRegistForm.jsp");
		} else if (action.equals("dochange")) {
			//変更確認ページに行く
			int id = Integer.parseInt(request.getParameter("id"));
			ListedItemDAO dao = null;
			try {
				dao = new ListedItemDAO();
				ListedItemBean bean = dao.findItem(id);
				request.setAttribute("textbook", bean);
			} catch (DAOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			gotoPage(request, response, "/listedItemRegistCheck.jsp");
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
