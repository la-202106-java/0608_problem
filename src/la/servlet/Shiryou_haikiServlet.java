package la.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import la.bean.Material;
import la.bean.MaterialLedger;
import la.dao.DAOException;
import la.dao.MaterialDAO;
import la.dao.MaterialLedgerDAO;

@WebServlet("/Shiryou_haikiServlet")
public class Shiryou_haikiServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			String action = request.getParameter("action");

			if (action.equals("dispose_confirm")) {//廃棄確認
				MaterialDAO dao = new MaterialDAO();
				int id = Integer.parseInt(request.getParameter("material_id"));
				List<Material> list = new ArrayList<Material>();
				list = dao.findById(id);//資料idに紐づくレコードの取得

				//				String isbn = (request.getParameter("isbn"));
				//				String stockDate = request.getParameter("stockDate");
				//				String title = request.getParameter("title");

				//				request.setAttribute("material_id", id);
				//				request.setAttribute("isbn", isbn);
				//				request.setAttribute("stockDate", stockDate);
				//				request.setAttribute("title", title);
				request.setAttribute("items", list);
				gotoPage(request, response, "/confirm_shiryou_haiki.jsp");

			} else if (action.equals("dispose_ok")) {//廃棄OK
				MaterialLedgerDAO dao = new MaterialLedgerDAO();

				int id = Integer.parseInt(request.getParameter("material_id"));
				@SuppressWarnings("unused")
				int rows = dao.disposeById(id);//廃棄年月日の更新
				MaterialLedger bean = dao.findById(id);//更新したレコードの取得
				request.setAttribute("bean", bean);
				gotoPage(request, response, "/shiryou_haiki_ok.jsp");

			} else if (action.equals("dispose_cancel")) {//廃棄キャンセル
				gotoPage(request, response, "/shiryou_kensaku.jsp");

			} else {
				request.setAttribute("message", "正しく操作してください。");
				gotoPage(request, response, "/errInternal.jsp");
			}
		} catch (

		DAOException e) {
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
