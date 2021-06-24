package black.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import black.dao.DAOException;
import black.dao.DepartmentDAO;

/**
 * Servlet implementation class ListedItemRegistServlet
 */
@WebServlet("/ListedItemRegistServlet")
public class ListedItemRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListedItemRegistServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		gotoPage(request, response, "/listedItemRegistForm.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//登録処理を書く
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

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
