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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String size = request.getParameter("size");
		String message = request.getParameter("message");

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		/*
				if(message == null ) {
						showNotMessageError(out);
					return;
				}
		*/
		if (size.equals("large")) {
			out.println("<h1>" + message + "<h1>");
		} else if (size.equals("midium")) {
			out.println("<h2>" + message + "<h2>");
		} else if (size.equals("small")) {
			out.println("<h3>" + message + "<h3>");
		} else {
			out.println(message);
		}

		out.println("</body></html>");
		/*
				private void showNotMessageError(PrintWriter out) {
				out.println("<html><head><title>Plus</title></head><body>");
				out.println("<h1>文字が入力されていません</h1>");
				out.println("</body></html>");
				}
				*/
	}
}
