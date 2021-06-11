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
 * Servlet implementation class HelloServlet
 */
@WebServlet("/BBSServlet")
public class BBSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String[]> msg_list = new ArrayList<String[]>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BBSServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String msg = request.getParameter("msg");
		String name = request.getParameter("name");
		if (msg != null && (msg.trim()).length() != 0) {
			if (name == null || (name.trim()).length() == 0) {
				name = "ゲスト";
			}
			msg_list.add(new String[] { msg, name });

		}
		out.println("<html><head><title>掲示板</title></head><body>");
		out.println("<form action=\"BBSServlet\" method=\"post\">\r\n"
				+ "名前：\r\n<br>"
				+ "<input type=\"text\" name=\"name\" />\r\n<br>"
				+ "メッセージ：<br>\r\n"
				+ "<textarea cols=\"40\" rows=\"4\" name=\"msg\"></textarea><br>\r\n"
				+ "<input type=\"submit\" value=\"書き込み\">\r\n"
				+ "</form>");
		for (int i = 0; i < msg_list.size(); i++) {

			out.println(i + 1 + " " + msg_list.get(i)[0]);
			out.println("&nbsp; by " + msg_list.get(i)[1]);
			out.println("<br>");

		}

		out.println("</body></html>");
	}

}
