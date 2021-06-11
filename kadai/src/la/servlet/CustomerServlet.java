package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Customer
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {

	//	private String name;
	//	private String address;
	//	private String tel;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession(false);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (action.equals("confirm")) {
			if (session == null) {
				out.println("<html><head><title>Register</title></head><body>");
				out.println("<h1>登録してください！<h1>");
				out.println("</body></html>");
			} else {
				String name = (String) session.getAttribute("name");
				String address = (String) session.getAttribute("address");
				String tel = (String) session.getAttribute("tel");
				if (name == null && address == null && tel == null) {
					out.println("<html><head><title>Register</title></head><body>");
					out.println("<h1>登録してください！<h1>");
					out.println("</body></html>");
				} else {
					out.println("<html><head><title>ShowCustomer</title></head><body>");
					out.println("名前:" + name + "<br>");
					out.println("住所:" + address + "<br>");
					out.println("電話:" + tel + "<br>");
					out.println("</body></html>");
				}

			}

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// actionパラメータ=registerってGETとPOST切り分けててもいるの？？
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");

		HttpSession session = request.getSession();
		session.setAttribute("name", name);
		session.setAttribute("address", address);
		session.setAttribute("tel", tel);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html><head><title>Register</title></head><body>");
		out.println("<h1>登録しました！<h1>");
		out.println("</body></html>");
	}

}
