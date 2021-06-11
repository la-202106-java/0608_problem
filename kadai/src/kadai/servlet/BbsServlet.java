package kadai.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kasai.bean.BbsBean;

/**
 * Servlet implementation class BbsServlet
 */
@WebServlet("/BbsServlet")
public class BbsServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String message = request.getParameter("message");

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		BbsBean bean = new BbsBean(i1);

		request.setAttribute("message", bean);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Bbs.jsp");
		rd.forward(request, response);

	}

}
