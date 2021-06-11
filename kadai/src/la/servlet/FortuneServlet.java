package la.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.FortuneBean;

/**
 * Servlet implementation class FortuneServlet
 */
@WebServlet("/FortuneServlet")
public class FortuneServlet extends HttpServlet {

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FortuneServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/fortune.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String month = req.getParameter("month");
		int m = Integer.parseInt(month);

		FortuneBean bean = new FortuneBean(m, randomColor(), randomItem(), randomRank());
		req.setAttribute("fortune", bean);

		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/fortune.jsp");
		rd.forward(req, resp);

	}

	private int randomRank() {
		Random rand = new Random();
		return rand.nextInt(12) + 1;
	}

	private String randomItem() {
		String item = "";
		Random rand = new Random();
		int num = rand.nextInt(3);
		if (num == 0) {
			item = "タオル";
		} else if (num == 1) {
			item = "カバン";
		} else if (num == 2) {
			item = "腕時計";
		} else {
			item = "???";
		}

		return item;
	}

	private String randomColor() {
		String color = "";
		Random rand = new Random();
		int num = rand.nextInt(3);
		if (num == 0) {
			color = "赤";
		} else if (num == 1) {
			color = "黄";
		} else if (num == 2) {
			color = "白";
		} else {
			color = "???";
		}
		return color;
	}

}
