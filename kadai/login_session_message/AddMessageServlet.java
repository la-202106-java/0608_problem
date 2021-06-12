package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddMessageServlet")
public class AddMessageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		// ログイン済みかどうかチェックする
		// すでの作成済みのセッション領域の取得
		HttpSession session = request.getSession(false);
		if (session == null) { // セッションがない場合
			out.println("<html><head><title>ShowCart</title></head><body>");
			out.println("<h1>ログインしてくださいa</h1>");
			return;
		} else { // セッションは開始されているがログイン済みではない場合
			String isLogin = (String) session.getAttribute("isLogin");
			if (isLogin == null || !isLogin.equals("true")) {
				out.println("<html><head><title>ShowCart</title></head><body>");
				out.println("<h1>ログインしてくださいb</h1>");
				return;
			}
		}

		// リクエストパラメータの読み込み
		request.setCharacterEncoding("UTF-8");
		String message = request.getParameter("value1");

		@SuppressWarnings("unchecked")
		ArrayList<String> messa = (ArrayList<String>) session.getAttribute("products");
		if (messa == null) { // 初めてのときはメッセージリストを作成
			messa = new ArrayList<String>();
			session.setAttribute("products", messa);
		}
		// メッセージの追加
		messa.add(message);
		// 転送
		RequestDispatcher rd = request.getRequestDispatcher("/message.jsp");
		rd.forward(request, response);

	}

}
