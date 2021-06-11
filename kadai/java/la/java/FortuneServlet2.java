package la.java;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FortuneServlet
 */
@WebServlet("/fortune2")
public class FortuneServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DAI_KICHI = "大吉";
	private static final String KICHI = "吉";
	private static final String SHO_KICHI = "小吉";
	private static final String KYO = "凶";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FortuneServlet2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");

		request.setAttribute("name", name);
		request.setAttribute("result", fortune());

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
		var rd = request.getRequestDispatcher("/WEB-INF/Fortune2.jsp");
		rd.forward(request, response);
	}

}
