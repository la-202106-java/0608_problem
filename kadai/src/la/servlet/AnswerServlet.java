package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AnswerServlet
 */
@WebServlet("/AnswerServlet")
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnswerServlet() {
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

		String answerNo = request.getParameter("answer_no");
		int no = Integer.parseInt(answerNo);
		String answerName = null;

		switch (no) {
		case 100:
			answerName = "不正解";
			break;
		case 101:
			answerName = "正解！";
			break;
		case 102:
			answerName = "不正解";
			break;
		default:
			answerName = "???";
		}

		out.println("<html><head><title>Question</title></head><body>");
		out.println("<h1>" + answerName + "</h1>");
		out.println("</body></html>");

	}

}
