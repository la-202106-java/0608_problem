package la.kadai;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/OmikujiServlet1")
public class OmikujiServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String omikuji;
		Random rand = new Random();
		int num = rand.nextInt(6);

		if (num == 0) {
			omikuji = "大吉";
		} else if (num == 1) {
			omikuji = "小吉";
		} else if (num == 2) {
			omikuji = "凶";
		} else {
			omikuji = "吉";
		}

		request.setAttribute("omikuji", omikuji);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/omikuji1.jsp");
		rd.forward(request, response);
	}

}
