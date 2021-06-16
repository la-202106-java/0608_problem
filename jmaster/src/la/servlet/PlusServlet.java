package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PlusServlet")
public class PlusServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// リクエストパラメータの読み込み
		String num1 = request.getParameter("value1");
		String num2 = request.getParameter("value2");

		// 足し算の実行
		int i1 = Integer.parseInt(num1);
		int i2 = Integer.parseInt(num2);
		int answer = i1 + i2;

		// 答えの出力
		out.println("<html><head><title>Plus</title></head><body>");
		out.println(num1 + "+" + num2 + "=" + answer);
		out.println("</body></html>");
	}
}
