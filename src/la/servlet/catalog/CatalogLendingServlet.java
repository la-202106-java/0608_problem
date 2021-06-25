package la.servlet.catalog;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.BookBean;
import la.bean.NowUserBean;
import la.dao.BookDAO;
import la.dao.DAOException;
import la.dao.NowUserDAO;
import la.dao.ReservationDAO;

/**
 * Servlet implementation class CatalogLendingServlet
 */
@WebServlet("/CatalogLendingServlet")
public class CatalogLendingServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if ("lending".equals(action)) {
			// 目録検索から取置ボタンを押して遷移してきた
			String isbn = request.getParameter("isbn");
			if (isbn == null || isbn.isBlank()) {
				// TODO: 不正な遷移なのでエラーページへ
				return;
			}

			try {
				// isbnから資料を検索する
				//   貸出・取置されている資料は表示しない
				BookDAO dao = new BookDAO();
				List<BookBean> bookList = dao.getCanLendingBookListByIsbn(isbn);

				request.setAttribute("bookList", bookList);
				gotoPage(request, response, "/5_reserve_reserved/catalog_book_search.jsp");
				return;
			} catch (DAOException e) {
				e.printStackTrace();
				request.setAttribute("message", "内部エラーが発生しました。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		}

		// TODO 以下は参考コード
		if ("reserve_confirm".equals(action)) {
			// 予約ボタンを押されたので予約確認画面へ
			// 会員IDを取得して検索
			String userId = request.getParameter("user_id");
			if (userId == null || userId.isBlank()) {
				// TODO: 不正な遷移なのでエラーページへ
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
