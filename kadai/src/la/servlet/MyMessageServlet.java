package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyMessageServlet
 */
@WebServlet("/MyMessageServlet")
public class MyMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyMessageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String message = request.getParameter("message");
		String size = request.getParameter("size");

		String sizedMessage;
		if (size == null) {
			sizedMessage = "サイズを選択してください";

		} else {
			switch (size) {
			case "L":
				sizedMessage = "<h1>" + message + "</h1>";
				break;
			case "M":
				sizedMessage = "<h3>" + message + "</h3>";
				break;
			case "S":
				sizedMessage = "<h5>" + message + "</h5>";
				break;
			default:
				sizedMessage = "???";
			}
		}

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html><head><title>YourName</title></head><body>");
		out.println(sizedMessage);
		out.println("</body></html>");

	}

}
