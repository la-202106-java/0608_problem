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
@WebServlet("/BBSServlet")
public class BBSServlet extends HttpServlet {
	List<String> articles = new ArrayList<String>();
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String message = request.getParameter("message");
		if (message != null && message.length() != 0) {
			articles.add(name + ":" + message);
		}

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html><head><title>message</title></head></body>");
		out.println("<form action='/lesson/BBSServlet' method='post'>");
		out.println("名前:<br>");
		out.println("<input type ='name' name = 'name'><br>");
		out.println("メッセージ:<br>");
		out.println("<textarea name = 'message' cols ='40' rows='5'>");
		out.println("</textarea>");
		out.println("<br/><input type='submit' value='書き込み'></form>");

		out.println("<hr/>");
		for (String article : articles) {
			out.println(article);
			out.println("<hr/>");
		}
		out.println("</body></html>");
	}

}
