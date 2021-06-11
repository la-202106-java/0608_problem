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

@WebServlet("/BbsServlet")
public class BbsServlet extends HttpServlet {

	List<String> namelist = new ArrayList<String>();
	List<String> messagelist = new ArrayList<String>();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String message = request.getParameter("message");
		String name = request.getParameter("name");

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (message != null && name != null) {
			namelist.add(name);
			messagelist.add(message);
		}
		//		if (message != null) {
		//			messagelist.add(message);
		//		}

		out.println("<html><head><title>メッセージ</title></head><body>");
		out.println("<form action=\"/lesson/BbsServlet\" method=\"post\">");
		out.println("名前:<br>");
		out.println("<input type=\"text\" name=\"name\"><br>");
		out.println("メッセージ:<br>");
		out.println("<textarea name=\"message\" cols=\"40\" rows=\"5\">");
		out.println("</textarea>");
		out.println("<br>");
		out.println("<input type=\"submit\" value=\"書き込み\">");
		out.println("<hr>");

		//		for (int i = 0; i < namelist.size(); i++) {
		//			out.println(namelist+ ":" + messagelist);
		//			out.println("<hr>");
		//		}
		for (String list1 : namelist) {
			out.println(list1 + ":");
		}

		for (String list2 : messagelist) {
			out.println(list2);
			out.println("<hr>");

		}
		//step4
		//		for (String lists : messagelist) {
		//			out.println(lists);
		//			out.println("<hr>");
		//		}
		out.println("</body></html>");
	}
}
