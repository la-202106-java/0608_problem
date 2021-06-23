package black.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import black.bean.MemberBean;
import black.dao.DAOException;
import black.dao.MemberDAO;

/**
 * Servlet implementation class MemberSearchServlet
 */
@WebServlet("/MemberSearchServlet")
public class MemberSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		gotoPage(request, response, "/memberSearch.jsp");
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
			gotoPage(request, response, "/memberSearch.jsp");

		} else if (action.equals("search")) {
			//検索ボタンが押された場合
			try {
				MemberDAO dao = new MemberDAO();

				//まずidをチェック
				String idStr = request.getParameter("id");

				if (idStr == null || idStr.length() == 0) {
					//idが指定されていない→名前などで検索

				} else {
					//id指定あり→idのみで検索
					int id = Integer.parseInt(idStr);
					MemberBean bean = dao.findMember(id);
					request.setAttribute("member", bean);

				}
			} catch (DAOException e) {
				e.printStackTrace();
				request.setAttribute("messgae", "内部エラーが発生しました。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		}
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
}
