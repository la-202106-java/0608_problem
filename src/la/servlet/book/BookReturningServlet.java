package la.servlet.book;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.BookBean;
import la.bean.LendingBean;
import la.bean.NowUserBean;
import la.dao.BookDAO;
import la.dao.DAOException;
import la.dao.LendingDAO;
import la.dao.NowUserDAO;
import la.dao.ReservationDAO;
import la.dao.ReservedDAO;
import la.dao.ReturnedLendingDAO;

/**
 * Servlet implementation class BookReturningServlet
 */
@WebServlet("/BookReturningServlet")
public class BookReturningServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		try {
			LendingDAO dao = new LendingDAO();
			BookDAO bookdao = new BookDAO();
			NowUserDAO userdao = new NowUserDAO();
			ReservationDAO reservationdao = new ReservationDAO();

			if (action == null || action.length() == 0) {

				gotoPage(request, response, "/4_lent_return/returning_book.jsp");

			} else if (action.equals("search")) {
				String input = request.getParameter("bookID");
				if (input == null || input.length() == 0) {
					gotoPage(request, response, "/4_lent_return/returning_book.jsp");
				}
				int bookID = Integer.parseInt(request.getParameter("bookID"));

				// 返却情報取得
				LendingBean lending = dao.findByBookId(bookID);
				// 該当貸出なかったらメッセージ表示
				if (lending == null) {
					request.setAttribute("message", "貸出が存在しません");
					gotoPage(request, response, "/4_lent_return/returning_book.jsp");
				}
				BookBean book = bookdao.findByPrimaryKey(bookID);
				NowUserBean user = userdao.findByPrimaryKey(lending.getUserId());

				request.setAttribute("lending", lending);
				request.setAttribute("book", book);
				request.setAttribute("user", user);

				gotoPage(request, response, "/4_lent_return/returning_book_confirm.jsp");

			} else if (action.equals("confirm")) {

				// そのあと使えるしbookIDのままで
				int bookID = Integer.parseInt(request.getParameter("id"));
				LendingBean lending = dao.findByBookId(bookID);
				ReturnedLendingDAO returneddao = new ReturnedLendingDAO();
				// 返却済み追加
				returneddao.addReturnedLending(lending);
				// 貸出中の貸出から消去
				dao.removeByBookId(bookID);

				BookBean book = bookdao.findByPrimaryKey(bookID);

				// 予約があるか探してある場合は取り置き
				NowUserBean topUser = reservationdao.findbyISBN(book.getIsbn());
				if (topUser != null) {
					//取り置き確認画面へ
					request.setAttribute("book", book);
					request.setAttribute("top", topUser);
					gotoPage(request, response, "/4_lent_return/reserved_book_confirm.jsp");
				} else {
					// なければ戻る
					gotoPage(request, response, "/4_lent_return/returning_book.jsp");
				}

			} else if (action.equals("reserve")) {
				int bookID = Integer.parseInt(request.getParameter("id"));
				int userID = Integer.parseInt(request.getParameter("userID"));
				// 取り置きを追加して
				ReservedDAO reserveddao = new ReservedDAO();
				reserveddao.addReserved(userID, bookID, Calendar.getInstance().getTime());

				// 予約に取り置き日を追加
				BookBean book = bookdao.findByPrimaryKey(bookID);
				reservationdao.updateReservation(userID, book.getIsbn(), Calendar.getInstance().getTime());

				gotoPage(request, response, "/4_lent_return/returning_book.jsp");

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
