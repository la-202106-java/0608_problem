package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/MON41Servlet")
public class MON4_1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MON4_1Servlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		request.setCharacterEncoding("UTF-8");
		String message = request.getParameter("message");
		String size = request.getParameter("size");

		if (message.length() == 0 || message == null) {
			out.println("<html><head><title>HelloServlet</title></head><body>");
			out.println(" メッセージを入力してください");
			out.println("</body></html>");
			return;
		}

		switch (size) {

		case "large":
			out.println("<html><head><title>HelloServlet</title></head><body>");
			out.println("<h1>" + message + "</h1>");
			out.println("</body></html>");
			break;
		case "mid":
			out.println("<html><head><title>HelloServlet</title></head><body>");
			out.println("<h2>" + message + "</h2>");
			out.println("</body></html>");
			break;
		case "sma":
			out.println("<html><head><title>HelloServlet</title></head><body>");
			out.println("<h3>" + message + "</h3>");
			out.println("</body></html>");
			break;
		default:
			out.println("<html><head><title>HelloServlet</title></head><body>");
			out.println("チェックボタンのいずれかを選択してください");
			out.println("</body></html>");
			break;
		}

	}

}
