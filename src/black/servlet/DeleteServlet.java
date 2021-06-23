package black.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import black.bean.ListedItemBean;
import black.dao.ListedItemDAO;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			response.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");

			ListedItemDAO dao = new ListedItemDAO();
			//商品情報の取得
			HttpSession session = request.getSession(false);

			if ((session.getAttribute("user").equals("member")) || (session.getAttribute("user").equals("admin"))) {
				if (request.getAttribute("seller_id").equals("id"))

					//教科書削除チェック選択画面
					if (action.equals("deleteCheck")) {
						String id_item = request.getParameter("item_code");
						int id = Integer.parseInt(id_item);
						ListedItemBean bean = dao.findItem(id);
						request.setAttribute("id_item", bean);

						gotoPage(request, response, "/listedItemDeleteCheck.jsp");
					} else if (action.equals("delete")) {
						//削除完了ページに行く
						int id = Integer.parseInt(request.getParameter("id"));
						dao.deleteItem(id);
						gotoPage(request, response, "/listedItemDeleteDone.jsp");
					}
					//キャンセル　教科書詳細ページに行く
					else if (action.equals("cancel")) {
						gotoPage(request, response, "/ListedItemDetail.jsp");
					}

					//教科書削除完了後、トップページに戻る
					else if (action.equals("topReturn")) {
						gotoPage(request, response, "/top.jsp");
					}

			}

		} catch (

		Exception e) {

			////////////////////////////////////
			//エラーメッセ‐ジ先のページ未完了
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました");
			gotoPage(request, response, "/errInternal.jsp");
		}
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
