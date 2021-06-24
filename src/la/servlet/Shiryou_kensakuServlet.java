package la.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.Material;
import la.dao.DAOException;
import la.dao.MaterialDAO;

@WebServlet("/Shiryou_kensakuServlet")
public class Shiryou_kensakuServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			String action = request.getParameter("action");

			MaterialDAO dao = new MaterialDAO();
			if (action.equals("top")) {//検索をはじめて開いたときの処理
				List<Material> list = new ArrayList<Material>();
				list = dao.findAll();
				request.setAttribute("items", list);
				gotoPage(request, response, "/shiryou_kensaku.jsp");

			} else if (action.equals("search")) {//検索ボタンが押された後の処理
				String radio = request.getParameter("radio");
				List<Material> list = new ArrayList<Material>();

				String str_id = null;
				String title = null;
				String stock_date = null;

				if (radio.equals("id")) {//資料ID検索だったら
					str_id = request.getParameter("id");//資料ID
					//入力値があったら検索、なかったらlistはnullのまま
					if (str_id != null && str_id.length() != 0) {
						int id = Integer.parseInt(str_id);
						list = dao.findById(id);
					}

				} else if (radio.equals("title")) {//資料名検索（あいまい検索）だったら
					title = request.getParameter("part_of_title");//資料名
					//入力値があったら検索、なかったらlistはnullのまま
					if (title != null && title.length() != 0) {
						list = dao.findByName(title);
					}
				} else if (radio.equals("stock_date")) {//入荷日検索だったら
					stock_date = request.getParameter("stock_date");//入荷日
					//入力値があったら検索、なかったらlistはnullのまま
					if (stock_date != null && stock_date.length() != 0) {
						list = dao.findByStockDate(stock_date);
					}
				}

				//検索件数
				int count = list.size();
				request.setAttribute("count", count);

				//入力値の保持
				request.setAttribute("id", str_id);
				request.setAttribute("title", title);
				request.setAttribute("stock_date", stock_date);

				if (list.isEmpty()) {//資料IDでも、資料名でも結果が拾えなかったら
					gotoPage(request, response, "/shiryou_kensaku.jsp");
				} else {
					request.setAttribute("items", list);
					gotoPage(request, response, "/shiryou_kensaku.jsp");
				}

			} else {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} catch (DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request, response, "/errInternal.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
