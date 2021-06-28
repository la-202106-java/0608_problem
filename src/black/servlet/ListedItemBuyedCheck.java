package black.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import black.bean.ListedItemBean;
import black.bean.MemberBean;
import black.dao.ListedItemDAO;
import black.dao.MemberDAO;

/**
 * Servlet implementation class ListedItemBuyedCheck
 */
@WebServlet("/ListedItemBuyedCheck")
public class ListedItemBuyedCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListedItemBuyedCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession(false);
			MemberBean bean = (MemberBean) session.getAttribute("logined");
			int id = bean.getId();
			int item_id = (int) session.getAttribute("flag");
			MemberDAO dao = new MemberDAO();
			ListedItemDAO dao2 = new ListedItemDAO();
			ListedItemBean item = dao2.findItem(item_id);
			dao.deflag(id);
			session.setAttribute("flag", 0);
			request.setAttribute("item", item);
			gotoPage(request, response, "/listedItemBuyedCheck.jsp");
		} catch (Exception e) {

			////////////////////////////////////
			//エラーメッセ‐ジ先のページ未完了
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました");
			gotoPage(request, response, "/errInternal.jsp");
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
