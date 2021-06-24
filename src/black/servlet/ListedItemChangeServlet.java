package black.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import black.bean.ListedItemBean;
import black.dao.DAOException;
import black.dao.DepartmentDAO;
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
		gotoPage(request, response, "/listedItemChangeForm.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if (action == null || action.length() == 0) {
			//actionが指定されていなければフォームへ
			gotoPage(request, response, "/listedItemRegistForm.jsp");
		} else if (action.equals("change")) {
			//教科書変更画面
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

	public void init() throws ServletException {
		try {
			// 分類コード一覧は最初にアプリケーションスコープへ入れる
			DepartmentDAO dao = new DepartmentDAO();
			List<String> departments = dao.allDepartment();
			getServletContext().setAttribute("departments", departments);
			getServletContext().setAttribute("departments_size", departments.size());

			//状態（新品・未使用・中古）もアプリケーションスコープへ
			String[] conditions = { "新品", "未使用", "中古" };
			getServletContext().setAttribute("conditions", conditions);

		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
}
