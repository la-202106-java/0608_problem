package la.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.Omikujibean;

/**
 * Servlet implementation class OmikujiServlet
 */
@WebServlet("/OmikujiServlet")
public class OmikujiServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//		response.setContentType("text/html;charset=UTF-8");
		//		PrintWriter out = response.getWriter();

		Random random = new Random();
		int randomValue = random.nextInt(6) + 1;

		String result;

		if (randomValue == 1) {
			result = "大吉";
		} else if (randomValue == 2 || randomValue == 3 || randomValue == 4) {
			result = "吉";
		} else if (randomValue == 5) {
			result = "小吉";
		} else {
			result = "凶";
		}

		//Beansにまとめる
		Omikujibean bean = new Omikujibean(result);
		request.setAttribute("omikuji", bean);

		RequestDispatcher rd = request.getRequestDispatcher("/OmikujiServlet.java");
		rd.forward(request, response);

	}

	//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//		// TODO Auto-generated method stub
	//		doGet(request, response);
	//	}

}
