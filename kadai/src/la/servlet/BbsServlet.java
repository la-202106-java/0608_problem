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
 * Servlet implementation class BbsServlet
 */
@WebServlet("/BbsServlet")
public class BbsServlet extends HttpServlet {

	List<String> messages = new ArrayList<String>();
	//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	//			throws ServletException, IOException {
	//		// TODO Auto-generated method stub
	//		response.getWriter().append("Served at: ").append(request.getContextPath());
	//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String message = request.getParameter("message");

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (name != null && message != null && message.length() != 0) {
			messages.add(name + "：" + message);
		}

		out.println("<html><head><title>掲示板</title></head><body>");
		out.println("<form action ='/lesson/BbsServlet'method = post>");
		out.println("名前：<br />");
		out.println("<input type = 'text' name = 'name'><br />");
		out.println("メッセージ：<br />");
		out.println("<textarea name ='message' cols = '30' rows = '5'");
		out.println("<br /></textarea>");
		out.println("<br /><input type = 'submit' value = '書き込み'></form>");
		out.println("<hr/>");

		for (String onemessage : messages) {
			out.println(onemessage);
			out.println("<hr/>");
		}

		out.println("</body></html>");

	}

}
