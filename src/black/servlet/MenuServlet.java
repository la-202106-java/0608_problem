package black.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class myPageServlet
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//会員側のメニューページ遷移
		String action = request.getParameter("action");
		if (action.equals("top")) {
			//トップページに移動
			gotoPage(request, response, "/top.jsp");
		} else if (action.equals("mypage")) {
			//マイページに移動
			gotoPage(request, response, "/mypage.jsp");
		} else if (action.equals("serch")) {
			//教書検索ページに移動
			//遷移先ページ未完了
			gotoPage(request, response, "/.jsp");
		} else if (action.equals("regist")) {
			//教科書登録ページに移動
			//遷移先ページ未完了
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
