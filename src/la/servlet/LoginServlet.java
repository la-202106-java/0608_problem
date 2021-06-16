package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.CategoryBean;
import la.bean.CustomerBean;
import la.dao.CustomerDAO;
import la.dao.DAOException;
import la.dao.ItemDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	//	private static final String USER = "jack";
	//	private static final String PASS = "abc";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		try {
			String action = request.getParameter("action");
			if (action.equals("login")) {
				String address = request.getParameter("address");
				String passWord = request.getParameter("password");
				CustomerDAO dao = new CustomerDAO();
				CustomerBean bean = dao.findByEmailAndPassword(address, passWord);
				if (bean != null) { //ログイン成功時
					// Listをリクエストスコープに入れてJSPへフォーワードする
					//request.setAttribute("items", list);
					HttpSession session = request.getSession();
					session.setAttribute("ibean", bean);
					session.setAttribute("isLogin", "true");
					gotoPage(request, response, "/top.jsp");
				} else { //メアドとパスワードが間違えている場合
					request.setAttribute("isLogin", "false");
					gotoPage(request, response, "/login.jsp");
				}
			} else if (action.equals("logout")) { //ログアウト
				HttpSession session = request.getSession(false);
				if (session != null) {
					session.invalidate();
					gotoPage(request, response, "/menu.jsp");
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}

	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	public void init() throws ServletException {
		try {
			// カテゴリ一覧は最初にアプリケーションスコープへ入れる
			ItemDAO dao = new ItemDAO();
			List<CategoryBean> list = dao.findAllCategory();
			getServletContext().setAttribute("categories", list);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		doGet(req, resp);
	}
}