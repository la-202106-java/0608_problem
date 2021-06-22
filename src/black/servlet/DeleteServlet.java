package black.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		//教科書削除チェック選択画面
		if (action.equals("leave")) {
			gotoPage(request, response, "/Delete_Check.jsp");
		} else if (action.equals("delete")) {
			//削除完了ページに行く
			gotoPage(request, response, "/Delete_Done.jsp");
		}
		//キャンセル　教科書詳細ページに行く
		//遷移先ページ未完了
		else if (action.equals("cancel")) {
			gotoPage(request, response, "/.jsp");
		}

		//教科書削除完了後、トップページに戻る
		//遷移先ページ未完了
		else if (action.equals("topReturn")) {
			gotoPage(request, response, "/top.jsp");
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
