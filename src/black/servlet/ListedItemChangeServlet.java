package black.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession(false);
		if (session == null) {
			//ログインしていなければログインへ
			gotoPage(request, response, "/LoginServlet");
		} else {
			//ログインしていればフォームへ
			int id = Integer.parseInt(request.getParameter("item_id"));
			try {
				ListedItemDAO dao = new ListedItemDAO();

				ListedItemBean bean = dao.findItem(id);
				request.setAttribute("textbook", bean);
				gotoPage(request, response, "/listedItemChangeForm.jsp");

			} catch (DAOException e) {
				e.printStackTrace();
				request.setAttribute("messgae", "内部エラーが発生しました。");
				gotoPage(request, response, "/errInternal.jsp");
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		String regist = request.getParameter("regist");
		String cancel = request.getParameter("cancel");

		if (action == null || action.length() == 0) {
			//actionが指定されていなければフォームへ
			gotoPage(request, response, "/listedItemChangeForm.jsp");
		} else if (action.equals("change")) {
			//教科書情報取得して教科書変更画面へ
			int id = Integer.parseInt(request.getParameter("item_id"));
			try {
				ListedItemDAO dao = new ListedItemDAO();

				ListedItemBean bean = dao.findItem(id);
				request.setAttribute("textbook", bean);
				gotoPage(request, response, "/listedItemChangeForm.jsp");

			} catch (DAOException e) {
				e.printStackTrace();
				request.setAttribute("messgae", "内部エラーが発生しました。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} else if (action.equals("check")) {
			//変更確認ページに行く

			//入力内容取得
			int id = Integer.parseInt(request.getParameter("id"));
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			int departmentCode = Integer.parseInt(request.getParameter("department_code"));
			String author = request.getParameter("author");
			int price = Integer.parseInt(request.getParameter("price"));
			String condition = request.getParameter("condition");
			String image = request.getParameter("image");

			//入力内容をスコープに入れる
			ListedItemBean bean = new ListedItemBean(id, isbn, title, departmentCode, author, price, condition, -1,
					image);
			request.setAttribute("textbook", bean);

			gotoPage(request, response, "/listedItemChangeCheck.jsp");
		} else if (action.equals("regist") && regist != null) {
			//変更を登録→教科書詳細画面

			//入力内容取得
			int id = Integer.parseInt(request.getParameter("id"));
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			int departmentCode = Integer.parseInt(request.getParameter("department_code"));
			String author = request.getParameter("author");
			int price = Integer.parseInt(request.getParameter("price"));
			String condition = request.getParameter("condition");
			String image = request.getParameter("image");

			try {
				ListedItemDAO dao = new ListedItemDAO();
				dao.updateItem(id, isbn, title, departmentCode, author, price, condition, image);

				//ListedItemBean bean = new ListedItemBean(id, isbn, title, departmentCode,
				//		author, price, condition, -1, image);
				//request.setAttribute("item", bean);
				request.setAttribute("item_id", id);
				gotoPage(request, response, "ListedItemDetailServlet");

			} catch (DAOException e) {
				e.printStackTrace();
				request.setAttribute("messgae", "内部エラーが発生しました。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} else if (action.equals("regist") && cancel != null) {
			//確認画面からキャンセル→フォームへもどる

			//入力内容取得
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			int departmentCode = Integer.parseInt(request.getParameter("department_code"));
			String author = request.getParameter("author");
			int price = Integer.parseInt(request.getParameter("price"));
			String condition = request.getParameter("condition");
			String image = request.getParameter("image");

			//入力内容をスコープに入れる
			ListedItemBean bean = new ListedItemBean(-1, isbn, title, departmentCode,
					author, price, condition, -1, image);
			request.setAttribute("textbook", bean);

			gotoPage(request, response, "/listedItemChangeForm.jsp");
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
