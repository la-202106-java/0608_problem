package black.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import black.bean.MemberBean;
import black.dao.ListedItemDAO;
import black.dao.MemberDAO;

/**
 * Servlet implementation class ListedItemBuy
 */
@WebServlet("/ListedItemBuy")
public class ListedItemBuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");
			ListedItemDAO dao = new ListedItemDAO();

			//教科書購入チェック選択画面
			if (action.equals("buy")) {
				HttpSession session = request.getSession(false);
				MemberBean bean = (MemberBean) session.getAttribute("member");
				if (bean == null) {
					gotoPage(request, response, "/memberLogin.jsp");
				}
				gotoPage(request, response, "/listedItemBuyCheck.jsp");
			} else if (action.equals("dobuy")) {
				//購入完了ページに行く
				int id = Integer.parseInt(request.getParameter("id"));
				int sales = Integer.parseInt(request.getParameter("sales"));
				int seller_id = Integer.parseInt(request.getParameter("seller_id"));
				MemberDAO dao2 = new MemberDAO();
				MemberBean bean = dao2.findMember(seller_id);
				sales += bean.getSales();
				dao2.plusSales(sales, seller_id);
				dao.deleteItem(id);
				//setterの追加

				gotoPage(request, response, "/listedItemBuyDone.jsp");
			}
			//キャンセル　教科書詳細ページに行く
			else if (action.equals("cancel")) {
				gotoPage(request, response, "/ListedItemDetail.jsp");
			}

			//教科書削除完了後、トップページに戻る
			else if (action.equals("topReturn")) {
				gotoPage(request, response, "/top.jsp");
			}
		} catch (Exception e) {

			////////////////////////////////////
			//エラーメッセ‐ジ先のページ未完了
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました");
			gotoPage(request, response, "/.jsp");
		}
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
