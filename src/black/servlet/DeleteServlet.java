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
			//String user = (String) session.getAttribute("user");

			//			if ((user.equals("member")) || (user.equals("admin"))) {
			//				String seller_id = request.getParameter("seller_id");
			//				if (seller_id.equals("id")) {

			//教科書削除チェック選択画面
			if (action.equals("delete")) {
				int id = Integer.parseInt(request.getParameter("item_id"));
				ListedItemBean item = dao.findItem(id);
				String isbn = item.getIsbn();
				request.setAttribute("setIsbn", isbn);
				String title = item.getAuthor();
				request.setAttribute("setTitle", title);
				int department_code = item.getDepartmentCode();
				request.setAttribute("SetDepartment_code", department_code);
				String author = item.getAuthor();
				request.setAttribute("setAuthor", author);
				int price = item.getPrice();
				request.setAttribute("SetPrice", price);
				session.setAttribute("item", item);
				gotoPage(request, response, "/listedItemDeleteCheck.jsp");
			} else if (action.equals("dodelete")) {
				//削除完了ページに行く
				ListedItemBean item = (ListedItemBean) session.getAttribute("item");
				int id = item.getId();
				dao.deleteItem(id);
				session.removeAttribute("item");
				gotoPage(request, response, "/listedItemDeleteDone.jsp");
			}
			//キャンセル　教科書詳細ページに行く
			else if (action.equals("cancel")) {
				gotoPage(request, response, "/ListedItemDetailServlet");
			}

			//教科書削除完了後、トップページに戻る
			else if (action.equals("topReturn")) {
				gotoPage(request, response, "/top");
			}

			//				}
			//			}

		} catch (Exception e) {
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
