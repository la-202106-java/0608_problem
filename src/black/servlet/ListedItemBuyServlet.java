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
import black.bean.MemberBean;
import black.dao.ListedItemDAO;
import black.dao.MemberDAO;

/**
 * Servlet implementation class ListedItemBuy
 */
@WebServlet("/ListedItemBuyServlet")
public class ListedItemBuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");
			ListedItemDAO dao = new ListedItemDAO();
			HttpSession session = request.getSession(false);
			if (action == null || action.length() == 0) {
				//actionが指定されていなければフォームへ
				gotoPage(request, response, "/listedItemRegistForm.jsp");
				//教科書購入チェック選択画面
			} else if (action.equals("buy")) {
				MemberBean bean = (MemberBean) session.getAttribute("logined");
				int id = Integer.parseInt(request.getParameter("item_id"));
				ListedItemBean item = dao.findItem(id);
				session.setAttribute("item", item);
				if (bean == null) {
					session.setAttribute("page", "itembuy");
					gotoPage(request, response, "/memberLogin.jsp");
				} else {
					gotoPage(request, response, "/listedItemBuyCheck.jsp");
				}
			} else if (action.equals("dobuy")) {
				//購入完了ページに行く
				ListedItemBean item = (ListedItemBean) session.getAttribute("item");
				MemberBean member = (MemberBean) session.getAttribute("logined");
				int id = item.getId();
				int sales = item.getPrice();
				int seller_id = item.getSellerId();
				int buyer_id = member.getId();
				MemberDAO dao2 = new MemberDAO();
				MemberBean bean = dao2.findMember(seller_id);
				sales += bean.getSales();
				dao2.plusSales(sales, seller_id);
				dao2.upflag(id, seller_id);
				dao.updateItem(id, buyer_id);
				session.removeAttribute("item");
				session.removeAttribute("page");
				//setterの追加

				gotoPage(request, response, "/litedItemBuyDone.jsp");
			}
			//キャンセル　教科書詳細ページに行く
			else if (action.equals("cancel")) {
				gotoPage(request, response, "/listedItemDetail.jsp");
			}

			//教科書削除完了後、トップページに戻る
			else if (action.equals("topReturn")) {
				gotoPage(request, response, "/top");
			}
		} catch (Exception e) {

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
