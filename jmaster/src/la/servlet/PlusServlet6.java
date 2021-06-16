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
 * Servlet implementation class PlusServlet6
 */
@WebServlet("/PlusServlet6")
public class PlusServlet6 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PlusServlet6() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String num1 = request.getParameter("value1");
		String num2 = request.getParameter("value2");

		int i1 = Integer.parseInt(num1);
		int i2 = Integer.parseInt(num2);
		int answer = i1 + i2;

		PlusBean bean = new PlusBean(i1, i2, answer);

		request.setAttribute("plus", bean);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/answer.jsp");
		rd.forward(request, response);
	}

}
