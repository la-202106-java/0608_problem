package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BBSServlet
 */
@WebServlet("/BbsServlet")
public class BbsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BbsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	List<String> articles = new ArrayList<String>();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String message = request.getParameter("message");
		if (message != null && message.length() != 0) {
			articles.add(message);
		}
		out.println("<html><head><title>Bbs</title></head><body>");
		out.println("<form action='/lesson/BbsServlet' method='post'>");
		out.println("メッセージ：<br />");
		out.println("<textarea name='message'>");
		out.println("</textarea>");
		out.println("<br /> <input type='submit' value='書き込み'></form>");
		out.println("<hr/>");
		for (String article : articles) {
			out.println(article);
			out.println("<hr/>");
		}
		out.println("</body></html>");
		// TODO Auto-generated method stub

	}

}
