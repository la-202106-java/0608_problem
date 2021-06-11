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
@WebServlet("/Lesson4")
public class Lesson4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Lesson4() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String answer = request.getParameter("answer");
		String msg = "不正解！";
		if (answer.equals("2")) {
			msg = "正解！";
		}
		out.println("<html><head><title>HellloServlet</title></head><body>");
		out.println("<div style=\"font-size:" + 60 + "px\">" + msg + "</div>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//		// TODO Auto-generated method stub
		//		response.setContentType("text/html;charset=UTF-8");
		//		PrintWriter out = response.getWriter();
		//		String msg_size = request.getParameter("msg_size");
		//		String msg = request.getParameter("msg");
		//
		//		//		String num1 = request.getParameter("value1");
		//		//		String num2 = request.getParameter("value2");
		//		//
		//		//		int i1 = Integer.parseInt(num1);
		//		//		int i2 = Integer.parseInt(num2);
		//		out.println("<html><head><title>HellloServlet</title></head><body>");
		//		out.println("<div style=\"font-size:" + msg_size + "px\">" + msg + "</div>");
		//		out.println("</body></html>");

	}

}
