package la.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
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
	private static final long serialVersionUID = 1L;

	ArrayList<String> messages = new ArrayList<String>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BbsServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String message = request.getParameter("message");
		if (message != null && message.length() != 0) {
			messages.add(message);
		}

		//MessageBean bean = new MessageBean(messages);

		request.setAttribute("messages", messages);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/message.jsp");
		rd.forward(request, response);

	}

}
