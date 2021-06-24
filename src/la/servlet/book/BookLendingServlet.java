package la.servlet.book;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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
import la.dao.ReservedDAO;

/**
 * Servlet implementation class BookLendingServlet
 */
@WebServlet("/BookLendingServlet")
public class BookLendingServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		try {
			LendingDAO lendingdao = new LendingDAO();
			BookDAO bookdao = new BookDAO();
			NowUserDAO userdao = new NowUserDAO();
			ReservedDAO reserveddao = new ReservedDAO();

			if (action == null || action.length() == 0) {
				gotoPage(request, response, "/4_lent_return/lending_book1.jsp");
			} else if (action.equals("userSearch")) {
				int userID = Integer.parseInt(request.getParameter("userID"));
				request.setAttribute("userID", userID);
				List<LendingBean> lendings = lendingdao.findByUserId(userID);
				request.setAttribute("lendings", lendings);

				boolean entai = false;
				for (LendingBean lending : lendings) {
					java.util.Date Date = new Date();
					if (lending.getDeadline().before(Date)) {
						request.setAttribute("message", "返却期限の過ぎた本があります");
						entai = true;
						break;
					}
				}
				if (entai) {
					gotoPage(request, response, "/4_lent_return/lending_book1.jsp");
				} else if (lendings.size() >= 5) {
					request.setAttribute("message", "貸出上限に達しています");
					gotoPage(request, response, "/4_lent_return/lending_book1.jsp");
				} else if (reserveddao.reservedExists(userID)) {
					// 取り置きを確認してあったら取り置き画面へ
					List<BookBean> books = reserveddao.findByUserID(userID);
					request.setAttribute("books", books);
					gotoPage(request, response, "/4_lent_return/reserved_book_lending.jsp");
				} else {
					// 貸出ボタン押したら貸出処理
					// 貸出 or 別の資料の貸出
					// (ここまで取り置き貸出確認画面)

					// 取り置きは貸出日付をいれて更新、新たな貸し出しを増やす

					// なかったら資料ID入力へ画面遷移
					gotoPage(request, response, "/4_lent_return/lending_book2.jsp");
				}

			} else if (action.equals("bookSearch")) {
				int bookID = Integer.parseInt(request.getParameter("bookID"));
				int userID = Integer.parseInt(request.getParameter("userID"));
				request.setAttribute("userID", userID);
				request.setAttribute("bookID", bookID);
				List<LendingBean> lendings = lendingdao.findByUserId(userID);

				if (lendings.size() >= 5) {
					request.setAttribute("message", "貸出上限に達しています");
					gotoPage(request, response, "/4_lent_return/lending_book2.jsp");
				} else if (lendingdao.findByBookId(bookID) != null) {
					request.setAttribute("message", "貸出中です");
					gotoPage(request, response, "/4_lent_return/lending_book2.jsp");
				} else if (bookdao.findByPrimaryKey(bookID) == null) {
					request.setAttribute("message", "存在しない資料です");
					gotoPage(request, response, "/4_lent_return/lending_book2.jsp");

				} else {
					BookBean book = bookdao.findByPrimaryKey(bookID);
					NowUserBean user = userdao.findByPrimaryKey(userID);
					request.setAttribute("book", book);
					request.setAttribute("user", user);

					gotoPage(request, response, "/4_lent_return/lending_book_confirm.jsp");
				}
			} else if (action.equals("finish") || action.equals("continue")) {
				int bookID = Integer.parseInt(request.getParameter("bookID"));
				int userID = Integer.parseInt(request.getParameter("userID"));
				BookBean book = bookdao.findByPrimaryKey(bookID);

				// 貸出
				lendingdao.addLending(book, userID);

				if (action.equals("finish")) {
					gotoPage(request, response, "/4_lent_return/lending_book1.jsp");
				} else if (action.equals("continue")) {
					request.setAttribute("userID", userID);
					List<LendingBean> lendings = lendingdao.findByUserId(userID);
					request.setAttribute("lendings", lendings);
					gotoPage(request, response, "/4_lent_return/lending_book2.jsp");
				}

			}

			else {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} catch (DAOException e) {
			e.printStackTrace();
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
