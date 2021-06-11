package la.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

	//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//		// TODO Auto-generated method stub
	//		response.getWriter().append("Served at: ").append(request.getContextPath());
	//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String msg = request.getParameter("message");

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>BBS</title></head><body>");
		out.println("<form action=\"/Lesson/BbsServlet\" method=\"post\">");
		out.println("<p>メッセージ：</p>");
		out.println("<textarea name=\"message\" cols=\"50\" rows=\"5\"></textarea><br>");
		out.println("<input type=\"submit\" name=\"submit\" value=\"書き込み\"><hr>");
		out.println(msg + "<br><hr>");
		out.println("</form></body></html>");

	}

}
