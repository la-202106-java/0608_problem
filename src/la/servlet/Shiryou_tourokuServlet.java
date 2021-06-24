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

import la.bean.MaterialCatalog;
import la.bean.MaterialCategory;
import la.bean.MaterialLedger;
import la.dao.DAOException;
import la.dao.MaterialCatalogDAO;
import la.dao.MaterialCategoryDAO;
import la.dao.MaterialLedgerDAO;

@WebServlet("/Shiryou_tourokuServlet")
public class Shiryou_tourokuServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");
			//			String action = request.getParameter("action");
			HttpSession session = request.getSession();
			MaterialCatalogDAO dao = new MaterialCatalogDAO();
			if (action.equals("regist")) {
				String ISBN = request.getParameter("isbn");
				//入力ISBN番号がnullじゃない場合
				if (ISBN != null && ISBN.matches("[0-9]{13}")) {
					//ISBN番号が目録に入っていたら
					MaterialCatalog bean = dao.findByISBNkey(ISBN);
					if (bean != null) { //資料が既に目録に登録されていたら
						session.setAttribute("beans", bean);
						MaterialCategoryDAO dao2 = new MaterialCategoryDAO();//カテゴリコードに対応したカテゴリネームを取得
						MaterialCategory bean1 = dao2.findName(bean.getCategoryCode());
						request.setAttribute("beans1", bean1);
						gotoPage(request, response, "/confirm_siryou_touroku.jsp");
					} else {
						session.setAttribute("isbn", ISBN);
						gotoPage(request, response, "/shiryou_touroku_ver2.jsp");
					}
				} else {
					request.setAttribute("isError", "FALSE");
					gotoPage(request, response, "/shiryou_touroku.jsp");
				}
			} else if (action.equals("mod")) { //入力を修正したい場合
				session.invalidate();
				//session.removeAttribute("isbn");
				gotoPage(request, response, "/shiryou_touroku.jsp");
			} else if (action.equals("add")) { //登録されている内容で問題ない場合
				MaterialCatalog bean = (MaterialCatalog) session.getAttribute("beans");
				if (bean != null) {
					String ISBN = bean.getIsbn();
					java.sql.Timestamp d4 = java.sql.Timestamp.valueOf(java.time.LocalDateTime.now());
					MaterialLedgerDAO dao2 = new MaterialLedgerDAO(); //台帳
					dao2.add(ISBN, d4);
					//追加したDAOをbeanに格納する
					MaterialLedger bean1 = dao2.findBynewID();
					request.setAttribute("bbean", bean1);
					gotoPage(request, response, "/message_completed_shiryou_touroku.jsp");
				}
			}

			else if (action.equals("confirm")) { //資料番号が入力されてきたら
				//台帳と目録に記帳する
				String isbn = (String) session.getAttribute("isbn");
				String title = request.getParameter("title");
				String author = request.getParameter("author");
				String publisher = request.getParameter("publisher");
				String publisher_date = request.getParameter("publisher_date");

				int categoryCode = Integer.parseInt(request.getParameter("choice"));

				//MaterialCatalog bean4 = dao.findByName(title);//既存
				MaterialCatalog bean5 = dao.findBySelect(title, author, publisher, publisher_date, categoryCode);
				if (bean5 != null) { //入力した資料名が既に目録に登録されている場合
					session.setAttribute("beans", bean5);
					request.setAttribute("Inputcheck", "True");
					MaterialCategoryDAO dao2 = new MaterialCategoryDAO();//カテゴリコードに対応したカテゴリネームを取得
					MaterialCategory bean1 = dao2.findName(bean5.getCategoryCode());
					request.setAttribute("beans1", bean1);
					gotoPage(request, response, "/confirm_siryou_touroku.jsp");
				} else if (title != null && title.length() != 0 &&
						author != null && author.length() != 0 &&
						publisher != null && publisher.length() != 0 &&
						publisher_date != null && publisher_date.length() != 0) {
					//台帳と目録に記帳する
					java.sql.Date d3 = java.sql.Date.valueOf(publisher_date);
					MaterialCatalogDAO dao1 = new MaterialCatalogDAO(); //目録
					dao1.add(title, author, publisher, d3, categoryCode, isbn);//目録に追加
					java.sql.Timestamp d4 = java.sql.Timestamp.valueOf(java.time.LocalDateTime.now());
					MaterialLedgerDAO dao2 = new MaterialLedgerDAO(); //台帳
					dao2.add(isbn, d4); //台帳に追加
					//追加したDAOをbeanに格納する
					MaterialLedger bean1 = dao2.findBynewID();
					request.setAttribute("bbean", bean1);
					MaterialCatalog bean = dao.findByISBNkey((String) session.getAttribute("isbn"));
					request.setAttribute("beans", bean);
					gotoPage(request, response, "/message_completed_shiryou_touroku.jsp");//ここにもアウトプットの処理が必要
				} else {
					request.setAttribute("title", title);
					request.setAttribute("author", author);
					request.setAttribute("publisher", publisher);
					request.setAttribute("publisher_date", publisher_date);
					request.setAttribute("isError", "FALSE");
					gotoPage(request, response, "/shiryou_touroku_ver2.jsp");
				}

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
