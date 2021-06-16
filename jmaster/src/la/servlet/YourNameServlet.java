package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/YourNameServlet")
public class YourNameServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの文字コードの指定
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");

		// 送信する文字コードの指定
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// 名前の出力
		out.println("<html><head><title>YourName</title></head><body>");
		out.println("あなたのお名前は「" + name + "」さんですね。");
		out.println("</body></html>");
	}
}
