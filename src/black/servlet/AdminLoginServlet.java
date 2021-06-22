package black.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class adminServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	//
	private static final String MAIL = "xyz@abc@.com";
	private static final String PASS = "abc";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		//actionリクエストパラメータの読み込み
		String action = request.getParameter("action");
		if (action.equals("login")) {//ログイン時
			//ログイン時はユーザー名とパスワードを取得する
			//パラメータのエラーチェックは省略
			String mail = request.getParameter("mail");
			String passWord = request.getParameter("password");

			if (mail.equals(MAIL) && passWord.equals(PASS)) {
				//ユーザ名とパスワードが一致したらログイン処理を行う
				//セッション管理を行う
				HttpSession session = request.getSession();
				//ログイン済みの属性を設定する
				session.setAttribute("isLogin", "true");
				gotoPage(request, response, "/adminMenu.jsp");
			} else {
				out.println("<html><head><title>login</title></head></body>");
				out.println("<h1>ユーザー名またはパスワードが違います</h1>");
				out.println("</body></html>");
			}
			//これはいらないかも
		} else if (action.equals("logout")) {//ログアウト時
			//既に作成されているセッション領域を取得する、新しくは作成しない
			HttpSession session = request.getSession(false);
			if (session != null) {
				//セッション領域を無効にする
				session.invalidate();
				out.println("<html><head><title>login</title></head></body>");
				out.println("<h1>ログアウトしました</h1>");
				out.println("</body></html>");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
