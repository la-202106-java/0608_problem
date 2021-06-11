package la.kadai;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/OmikujiServlet")
public class OmikujiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		request.setAttribute("name", name);

		/*		String[] omikuji = new String[12];
		
				for (int i = 0; i < omikuji.length; i++) {
					omikuji[i] = "大吉";
				}*/
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

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/omikuji.jsp");
		rd.forward(request, response);
	}

}
