package la.servlet.catalog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.CatalogBean;

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
		System.out.println(titles);
		// 取得した資料名で目録（catalog）資料名でをあいまい検索する
		// 目録のISBNと資料(book)を結合して資料一覧を取得する
		// 資料IDで貸出テーブルを結合して貸出数を取得する
		// 資料IDで取置テーブルを結合して貸出数を取得する
		// ISBNでGroupingした数ー貸出数の数＝在庫数

		// 結果をJSPへ出力する
		List<CatalogBean> list = new ArrayList<>();
		request.setAttribute("result", list);
		request.setAttribute("titles", titles);
		gotoPage(request, response, "/5_reserve_reserved/catalog_search.jsp");
		return;

	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
