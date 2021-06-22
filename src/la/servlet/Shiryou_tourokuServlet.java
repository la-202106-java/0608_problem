package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.MaterialCatalog;
import la.bean.MaterialCategory;
import la.dao.DAOException;
import la.dao.MaterialCatalogDAO;
import la.dao.MaterialCategoryDAO;

@WebServlet("/Shiryou_tourokuServlet")
public class Shiryou_tourokuServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");
			//			String action = request.getParameter("action");
			//			HttpSession session = request.getSession();
			MaterialCatalogDAO dao = new MaterialCatalogDAO();
			if (action.equals("regist")) {
				String ISBN = request.getParameter("isbn");
				//入力ISBN番号がnullじゃない場合
				if (ISBN != null || ISBN.length() != 0) {

					//ISBN番号が目録に入っていたら
					MaterialCatalog bean = dao.findByISBNkey(ISBN);
					if (bean != null) { //資料が既に目録に登録されていたら
						request.setAttribute("beans", bean);
						gotoPage(request, response, "/confirm_siryou_touroku.jsp");
					} else {
						//ここでカテゴリテーブルをフォワードする必要がある
						request.setAttribute("isbn", ISBN);
						gotoPage(request, response, "/shiryou_touroku_ver2.jsp");
					}
				} else {
					request.setAttribute("message", "正常なISDN番号を入力してください");
					RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
					rd.forward(request, response);
				}
			} else if (action.equals("regist2")) {

			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/errInternal.jsp");
			rd.forward(request, response);
		}
	}

	public void init() throws ServletException {
		try {
			//ServletContext context = this.getServletContext();
			//			String realPath = this.getServletContext().getRealPath("/WEB-INF/common.properties");
			//			getServletContext().setAttribute("realpath", realPath);
			// カテゴリ一覧は最初にアプリケーションスコープへ入れる
			MaterialCategoryDAO dao = new MaterialCategoryDAO();
			List<MaterialCategory> list = dao.findAll();
			getServletContext().setAttribute("categories", list);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
