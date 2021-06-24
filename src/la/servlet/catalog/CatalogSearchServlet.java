package la.servlet.catalog;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.CatalogBean;
import la.dao.CatalogDAO;
import la.dao.DAOException;

@WebServlet("/CatalogSearchServlet")
public class CatalogSearchServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		gotoPage(request, response, "/5_reserve_reserved/catalog_search.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 資料名がgetParameterで取得できる
		request.setCharacterEncoding("UTF-8");
		String titles = request.getParameter("titles");
		if (Objects.isNull(titles) || titles.isBlank()) {
			request.setAttribute("error", "資料名を入力してください");
			gotoPage(request, response, "/5_reserve_reserved/catalog_search.jsp");
			return;
		}
		// 取得した資料名で目録（catalog）資料名でをあいまい検索する
		try {
			CatalogDAO dao = new CatalogDAO();
			List<CatalogBean> list = dao.getCatalogListWithStockByName(titles);

			// 結果をJSPへ出力する
			request.setAttribute("result", list);
			request.setAttribute("titles", titles);
			gotoPage(request, response, "/5_reserve_reserved/catalog_search.jsp");

		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
