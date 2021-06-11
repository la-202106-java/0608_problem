package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BBSServlet
 */
@WebServlet("/BBSServlet")
public class BBSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BBSServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		//リクエストパラメータの読み込み
		request.setCharacterEncoding("UTF-8");
		String message = request.getParameter("value1");
		String name = request.getParameter("value2");

		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		ArrayList<String> messa = (ArrayList<String>) session.getAttribute("message");
		@SuppressWarnings("unchecked")
		ArrayList<String> nam = (ArrayList<String>) session.getAttribute("name");
		if (messa == null) {
			messa = new ArrayList<String>();
			session.setAttribute("message", messa);
		}
		if (nam == null) {
			nam = new ArrayList<String>();
			session.setAttribute("name", nam);
		}
		messa.add(message);
		nam.add(name);

		out.println("<html><head><title>SelectProduct</title></head><body>");
		out.println("<form action='/lesson/BBSServlet' method='post'>");
		out.println("名前：");
		out.println("<br>");
		out.println("<textarea name = 'value2' rows='1' cols='30'>");
		out.println("</textarea>");
		out.println("<br>");

		out.println("メッセージ：");
		out.println("<br>");
		out.println("<textarea name = 'value1' rows='4' cols='40'>");
		out.println("</textarea>");
		out.println("<br>");
		out.println("<input type = 'submit' value = '書き込み'>");
		out.println("<form>");
		for (int i = 0; i < messa.size(); i++) {
			out.println("<hr>");
			out.println(nam.get(i) + "：" + messa.get(i) + "<br>");
		}
		out.println("</body></html>");
	}

}
