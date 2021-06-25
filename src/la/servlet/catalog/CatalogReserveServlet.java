package la.servlet.catalog;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.NowUserBean;
import la.dao.DAOException;
import la.dao.NowUserDAO;
import la.dao.ReservationDAO;

/**
 * Servlet implementation class CatalogReserveServlet
 */
@WebServlet("/CatalogReserveServlet")
public class CatalogReserveServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if ("reserve".equals(action)) {
			// 目録検索から予約ボタンを押して遷移してきた
			request.setAttribute("isbn", request.getParameter("isbn"));
			request.setAttribute("title", request.getParameter("title"));
			gotoPage(request, response, "/5_reserve_reserved/add_reservation.jsp");
			return;
		}

		if ("reserve_confirm".equals(action)) {
			// 予約ボタンを押されたので予約確認画面へ
			// 会員IDを取得して検索
			String userId = request.getParameter("user_id");
			if (userId == null || userId.isBlank()) {
				// 会員IDを間違えているので再度入力を促す
				request.setAttribute("error", "入力された会員IDは見つかりませんでした");
				gotoPage(request, response, "/5_reserve_reserved/add_reservation.jsp");
				return;
			}
			try {
				NowUserDAO dao = new NowUserDAO();
				NowUserBean bean = dao.findByPrimaryKey(Integer.parseInt(userId));
				if (bean == null) {
					// TODO: 不正な遷移なのでエラーページへ
					return;
				}
				request.setAttribute("isbn", request.getParameter("isbn"));
				request.setAttribute("title", request.getParameter("title"));
				request.setAttribute("userBean", bean);
				gotoPage(request, response, "/5_reserve_reserved/add_reservation_confirm.jsp");
				return;
			} catch (DAOException e) {
				e.printStackTrace();
				request.setAttribute("message", "内部エラーが発生しました。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		}

		if ("reserve_confirm_execute".equals(action)) {
			// 予約確定
			String userId = request.getParameter("user_id");
			String isbn = request.getParameter("isbn");
			if (userId == null || userId.isBlank() || isbn == null || isbn.isBlank()) {
				// TODO: 不正な遷移なのでエラーページへ
				return;
			}
			try {
				ReservationDAO dao = new ReservationDAO();
				dao.addReservation(isbn, Integer.parseInt(userId), LocalDateTime.now());
				// 予約できたので目録検索画面へ
				request.setAttribute("message", "予約が完了しました");
				gotoPage(request, response, "/5_reserve_reserved/catalog_search.jsp");
				return;
			} catch (DAOException e) {
				e.printStackTrace();
				request.setAttribute("message", "内部エラーが発生しました。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		}
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
