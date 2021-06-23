package black.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import black.dao.ListedItemDAO;

/**
 * Servlet implementation class ListedItemBuy
 */
@WebServlet("/ListedItemBuy")
public class ListedItemBuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");
			ListedItemDAO dao = new ListedItemDAO();

			//教科書購入チェック選択画面
			if (action.equals("Check")) {
				gotoPage(request, response, "/.jsp");
			} else if (action.equals("")) {
				//購入完了ページに行く
				int id = Integer.parseInt(request.getParameter("id"));
				dao.deleteItem(id);
				gotoPage(request, response, "/.jsp");
			}
			//キャンセル　教科書詳細ページに行く
			else if (action.equals("cancel")) {
				gotoPage(request, response, "/ListedItemDetail.jsp");
			}

			//教科書削除完了後、トップページに戻る
			else if (action.equals("topReturn")) {
				gotoPage(request, response, "/top.jsp");
			}
		} catch (Exception e) {

			////////////////////////////////////
			//エラーメッセ‐ジ先のページ未完了
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました");
			gotoPage(request, response, "/.jsp");
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
