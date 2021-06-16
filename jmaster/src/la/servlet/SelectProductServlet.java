package la.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectProductServlet
 */
@WebServlet("/SelectProductServlet")
public class SelectProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectProductServlet() {
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

		out.println("<html><head><title>SelectProduct</title></head><body>");
		out.println("選択された商品は「" + productName + "」です");
		out.println("</body></html>");
	}

}
