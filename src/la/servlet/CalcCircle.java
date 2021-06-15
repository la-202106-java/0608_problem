package la.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.PlusBean;

/**
 * Servlet implementation class PlusServlet
 */
@WebServlet("/CalcCircle")
public class CalcCircle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalcCircle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String num1 = request.getParameter("value1");
		String num2 = request.getParameter("value2");

		int value1 = Integer.parseInt(num1);
		int value2 = Integer.parseInt(num2);
		int answer = value1 + value2;
		PlusBean bean = new PlusBean(value1, value2, answer);
		request.setAttribute("plus", bean);

		//		/WEB-INF/answer.jsp
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/answer.jsp");
		rd.forward(request, response);
	}

}
