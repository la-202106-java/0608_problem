package black.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import black.bean.MemberBean;
import black.dao.DAOException;
import black.dao.MemberDAO;

/**
 * Servlet implementation class MemberSearchServlet
 */
@WebServlet("/MemberSearchServlet")
public class MemberSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		gotoPage(request, response, "/memberSearch.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if (action == null || action.length() == 0) {
			//actionが指定されていなければ検索フォームへ
			gotoPage(request, response, "/memberSearch.jsp");

		} else if (action.equals("search")) {
			//検索ボタンが押された場合
			try {
				MemberDAO dao = new MemberDAO();

				//まずidをチェック
				String idStr = request.getParameter("id");

				if (idStr == null || idStr.length() == 0) {
					//idが指定されていない→名前などで検索
					String name = request.getParameter("name");
					String address = request.getParameter("address");
					String tel = request.getParameter("tel");
					String email = request.getParameter("email");
					String date = request.getParameter("date");
					Date birthday = null;
					if (date != null && date.length() != 0) {
						//生年月日をString型からDate型に変更する処理
						birthday = Date.valueOf(date);
					}

					List<MemberBean> list = dao.findMember(name, address, tel, email, birthday);
					request.setAttribute("member2", list);
					request.setAttribute("result_num", list.size());

					MemberBean searchBean = new MemberBean(-1, name, address, tel, email, birthday, "");
					request.setAttribute("search_member", searchBean);

					gotoPage(request, response, "/memberSearch.jsp");

				} else {
					//id指定あり→idのみで検索
					int id = Integer.parseInt(idStr);
					MemberBean bean = dao.findMember(id);
					List<MemberBean> list = new ArrayList<MemberBean>();
					list.add(bean);
					request.setAttribute("member2", list);
					request.setAttribute("result_num", list.size());

					request.setAttribute("search_member_id", id);
					gotoPage(request, response, "/memberSearch.jsp");

				}
			} catch (DAOException e) {
				e.printStackTrace();
				request.setAttribute("messgae", "内部エラーが発生しました。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		}
	}

	private void gotoPage(HttpServletRequest request,
			HttpServletResponse response, String page) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
}
