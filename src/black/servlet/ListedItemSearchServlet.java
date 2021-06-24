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
import black.bean.MemberBean;
import black.dao.DAOException;
import black.dao.DepartmentDAO;
import black.dao.ListedItemDAO;

/**
 * Servlet implementation class ListedItemSearchServlet
 */
@WebServlet("/ListedItemSearchServlet")
public class ListedItemSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListedItemSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		gotoPage(request, response, "/listedItemSearch.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if (action == null || action.length() == 0) {
			//actionが指定されていなければ検索フォームへ
			gotoPage(request, response, "/listedItemSearch.jsp");

		} else if (action.equals("search")) {
			//検索ボタンが押された場合
			try {
				ListedItemDAO dao = new ListedItemDAO();

				String isbn = request.getParameter("isbn");
				String title = request.getParameter("title");
				int departmentCode = Integer.parseInt(request.getParameter("department_code"));
				String author = request.getParameter("author");
				String pMin = request.getParameter("price_min");
				String pMax = request.getParameter("price_max");
				String condition = request.getParameter("condition");
				String stock = request.getParameter("stock");
				String myItem = request.getParameter("my_item");

				//金額上限下限設定
				//指定なしの場合、下限ゼロ、上限はintのmax値
				int priceMin = 0;
				int priceMax = Integer.MAX_VALUE;
				if (pMin != null && pMin.length() != 0) {
					priceMin = Integer.parseInt(pMin);
				}
				if (pMax != null && pMax.length() != 0) {
					priceMax = Integer.parseInt(pMax);
				}

				//在庫ありのみ指定をbooleanに変換
				boolean onlyInStock = false;
				if (stock != null && stock.equals("true")) {
					onlyInStock = true;
				}

				//自分の商品のみ指定をidに変換
				int sellerId = -1;
				if (myItem != null && myItem.equals("true")) {
					HttpSession session = request.getSession(false);
					MemberBean member = new MemberBean();
					if (session.getAttribute("user").equals("member")) {
						member = (MemberBean) session.getAttribute("logined");
						sellerId = member.getId();
					}
				}

				List<ListedItemBean> list = dao.findItem(isbn, title, departmentCode, author,
						priceMin, priceMax, condition, onlyInStock, sellerId);

				//結果と件数をスコープに入れる
				request.setAttribute("listed_items", list);
				request.setAttribute("result_num", list.size());

				//検索条件をスコープに入れる
				request.setAttribute("search_isbn", isbn);
				request.setAttribute("search_title", title);
				request.setAttribute("search_department_code", departmentCode);
				request.setAttribute("search_author", author);
				request.setAttribute("search_price_min", pMin);
				request.setAttribute("search_price_max", pMax);
				request.setAttribute("search_condition", condition);
				request.setAttribute("search_stock", stock);
				request.setAttribute("search_my_item", myItem);

				gotoPage(request, response, "/listedItemSearch.jsp");

			} catch (DAOException e) {
				e.printStackTrace();
				request.setAttribute("messgae", "内部エラーが発生しました。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		}
	}

	public void init() throws ServletException {
		try {
			// 分類コード一覧は最初にアプリケーションスコープへ入れる
			DepartmentDAO dao = new DepartmentDAO();
			List<String> departments = dao.allDepartment();
			getServletContext().setAttribute("departments", departments);
			getServletContext().setAttribute("departments_size", departments.size());
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
