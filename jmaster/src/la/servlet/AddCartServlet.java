package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String productNo = request.getParameter("product_no");
		int no = Integer.parseInt(productNo);
		String productName = null;

		switch (no) {
		case 100:
			productName = "パソコン";
			break;
		case 101:
			productName = "プリンタ";
			break;
		case 102:
			productName = "デジタルカメラ";
			break;
		default:
			productName = "???";
		}

		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<String> cart = (ArrayList<String>) session.getAttribute("products");
		if (cart == null) {
			cart = new ArrayList<String>();
			session.setAttribute("products", cart);
		}

		cart.add(productName);

		out.println("<html><head><title>ShowCart</title></head><body>");
		out.println("現在のカートの中身は以下の通りです<br><br>");
		for (int i = 0; i < cart.size(); i++) {
			out.println(i + 1);
			out.println(":" + cart.get(i) + "<br>");
		}
		out.println("<hr><a href='/jmaster/selectProduct3.html'>商品リスト</a>");
		out.println("</body></html>");
	}

}
