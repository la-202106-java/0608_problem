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
	private static final long serialVersionUID = 1L;
	List<String> responseList = new ArrayList<String>();

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		String res = request.getParameter("res");
		if (res != null && res.length() != 0) {
			responseList.add(res);
		}

		out.println("<html><head><title>BBS</title></head><body>");
		out.println("メッセージ：<br>");
		out.println("<form action = \"/lesson/BBSServlet\" method = \"post\">");
		out.println("<textarea rows = \"10\" cols =\"60\" name = \"res\"></textarea>");
		out.println("<br>");
		out.println("<input type = \"submit\" value = \"書き込み\">");
		out.println("</form><hr>");
		for (int i = 0; i < responseList.size(); i++) {
			out.println((i + 1) + ":" + responseList.get(i) + "<br><hr>");
		}
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
