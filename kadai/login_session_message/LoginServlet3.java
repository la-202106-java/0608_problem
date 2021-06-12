package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet3")
public class LoginServlet3 extends HttpServlet {

	private static final String USER = "jack";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");
		if (action.equals("login")) {
			String name = request.getParameter("name");

			if (name.equals(USER)) {
				HttpSession session = request.getSession();
				session.setAttribute("isLogin", "true");

				session.setAttribute("name", name);
				RequestDispatcher rd = request.getRequestDispatcher("/message.jsp");
				rd.forward(request, response);
			} else {
				out.println("<html><head><title>SelectProduct</title></head><body>");
				out.println("<h1>ユーザ名が違います</h1>");
				out.println("</body></html>");
			}
		} else if (action.equals("logout")) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
				out.println("<html><head><title>SelectProduct</title></head><body>");
				out.println("<h1>ログアウトしました</h1>");
				out.println("</body></html>");
			} else {
				out.println("<html><head><title>SelectProduct</title></head><body>");
				out.println("<h1>ログインしていないのでログアウトできません</h1>");
				out.println("</body></html>");
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}