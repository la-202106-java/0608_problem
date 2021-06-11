package la.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FortuneServlet
 */
@WebServlet("/fortune")
public class FortuneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DAI_KICHI = "大吉";
	private static final String KICHI = "吉";
	private static final String SHO_KICHI = "小吉";
	private static final String KYO = "凶";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FortuneServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> fortunes = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			fortunes.add(fortune());
		}
		request.setAttribute("result", fortunes);

		dispatcher(request, response);
	}

	private String fortune() {
		String result;
		Random r = new Random();
		switch (r.nextInt(6)) {
		case 0:
			result = DAI_KICHI;
			break;
		case 1:
			result = SHO_KICHI;
			break;
		case 2:
			result = KYO;
			break;
		default:
			result = KICHI;
		}
		return result;
	}

	private void dispatcher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var rd = request.getRequestDispatcher("/WEB-INF/Fortune.jsp");
		rd.forward(request, response);
	}

}
