package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.MemberBean;
import la.dao.DAOException;
import la.dao.MembersDAO;

@WebServlet("/MembersServlet")
public class MembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MembersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			MembersDAO dao = new MembersDAO();
			List<MemberBean> list = dao.findAll();
			request.setAttribute("members", list);
			RequestDispatcher rd = request.getRequestDispatcher("/membersShow.jsp");
			rd.forward(request, response);
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
