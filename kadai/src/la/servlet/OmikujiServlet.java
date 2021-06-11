package la.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OmikujiServlet
 */
@WebServlet("/OmikujiServlet")
public class OmikujiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OmikujiServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String result = name + "さんの今日の運勢は、、、「" + omikuji() + "」です。";

		request.setAttribute("result", result);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/omikuji.jsp");
		rd.forward(request, response);

	}

	public String omikuji() {
		String result = "";
		Random rand = new Random();
		int num = rand.nextInt(6);
		if (num == 0) {
			result = "大吉";
		} else if (num >= 1 && num <= 3) {
			result = "吉";
		} else if (num == 4) {
			result = "小吉";
		} else if (num == 5) {
			result = "凶";
		} else {
			result = "???";
		}

		return result;
	}
}
