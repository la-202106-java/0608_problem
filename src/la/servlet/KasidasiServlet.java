package la.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.LendingLedger;
import la.bean.Member;
import la.dao.LendingLedgerDAO;

/**
 * Servlet implementation class KasidasiServlet
 */
@WebServlet("/KasidasiServlet")
public class KasidasiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KasidasiServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		LendingLedgerDAO ld = new LendingLedgerDAO();
		RequestDispatcher rd;
		if (action == null || action.trim().length() == 0 || action.equals("search")) {
			kensaku(request, response, ld);
		} else if (action.equals("add")) {

			add(request, response, ld);
		} else if (action.equals("return")) {
			returnMaterial(request, response, ld);
		}
	}

	private void returnMaterial(HttpServletRequest request, HttpServletResponse response, LendingLedgerDAO ld) {
		int lendingID = Integer.parseInt(request.getParameter("lid"));
		int rows = ld.returnMaterial(lendingID);
		if (rows == 1) {
			try {
				response.sendRedirect("KasidasiServlet");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.sendRedirect("error.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response, LendingLedgerDAO ld) {
		int mid = Integer.parseInt(request.getParameter("mid4add"));
		int sid = Integer.parseInt(request.getParameter("sid4add"));
		int lendingNum = ld.findUnreturn(mid).size();
		if (lendingNum < 5) {
			int rows = ld.addLendingRecord(mid, sid);

			if (rows == 1) {
				try {
					response.sendRedirect("KasidasiServlet");
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			} else if (rows == -1) {
				RequestDispatcher rd = request.getRequestDispatcher("KasidasiServlet?action=search");
				request.setAttribute("msg", "入力した会員IDもしくは資料IDは存在しません。");
				try {
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			} else if (rows == -2) {
				RequestDispatcher rd = request.getRequestDispatcher("KasidasiServlet?action=search");
				request.setAttribute("msg", "廃棄済みの資料は貸出できません。");
				try {
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			} else if (rows == -3) {
				RequestDispatcher rd = request.getRequestDispatcher("KasidasiServlet?action=search");
				request.setAttribute("msg", "退会済みの会員は貸出できません。");
				try {
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			} else if (rows == -4) {
				RequestDispatcher rd = request.getRequestDispatcher("KasidasiServlet?action=search");
				request.setAttribute("msg", "資料目録は存在しません。");
				try {
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			} else if (rows == -5) {
				RequestDispatcher rd = request.getRequestDispatcher("KasidasiServlet?action=search");
				request.setAttribute("msg", "すでに貸出されている資料は登録できません。");
				try {
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			} else {
				try {
					response.sendRedirect("error.jsp");
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		} else {

			RequestDispatcher rd = request.getRequestDispatcher("KasidasiServlet?action=search");
			request.setAttribute("msg", "すでに５冊の資料が貸出されています。新規の貸出が出来ません。");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

	}

	private void kensaku(HttpServletRequest request, HttpServletResponse response, LendingLedgerDAO ld) {
		Member member = new Member();
		member.setId(-1);
		boolean isFindALl = true;
		String mid = request.getParameter("mid");
		String mname = request.getParameter("mname");
		if (mid != null && mid.trim().length() != 0) {
			member.setId(Integer.parseInt(mid));
			request.setAttribute("mid", mid);
			isFindALl = false;
		}
		if (mname != null && mname.trim().length() != 0) {
			member.setName(mname);
			request.setAttribute("mname", mname);
			isFindALl = false;
		}
		if (isFindALl) {
			List<LendingLedger> list = ld.findAll();
			request.setAttribute("list", list);
		} else {
			List<LendingLedger> list = ld.findByBean(member);
			request.setAttribute("list", list);
		}
		RequestDispatcher rd = request.getRequestDispatcher("kasidasi_kensaku.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
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
