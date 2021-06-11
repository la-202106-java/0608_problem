package la.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.PlusBean;

/**
 * Servlet implementation class Omikuji1Servlet
 */
@WebServlet("/Omikuji1Servlet")
public class Omikuji1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Omikuji1Servlet() {
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

		Random rand = new Random();
		int dice = rand.nextInt(6) + 1;

		String answer = null;
		switch (dice) {
		case 1:
			answer = "「大吉」です";
			break;
		case 2:
			answer = "「小吉」です";
			break;
		case 3:
			answer = "「凶」です";
			break;
		default:
			answer = "「吉」です";
		}

		PlusBean bean = new PlusBean(answer);

		request.setAttribute("plus", bean);

		RequestDispatcher rd = request.getRequestDispatcher("/omikuji1.jsp");
		rd.forward(request, response);
	}

}
