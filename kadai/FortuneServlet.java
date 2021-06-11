package la.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FortuneServlet
 */
@WebServlet("/FortuneServlet")
public class FortuneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
String month = request.getParameter("month");
switch(month) {
case ”jan":>1月</option>
<option value=”feb”>2月</option>
<option value=”mar”>3月</option>
<option value=”apr”>4月</option>
<option value=”may”>5月</option>
<option value=”jun”>6月</option>
<option value=”jul”>7月</option>
<option value=”aug”>8月</option>
<option value=”sep”>9月</option>
<option value=”oct”>10月</option>
<option value=”nov”>11月</option>
<option value=”dec”
}
	}

}
