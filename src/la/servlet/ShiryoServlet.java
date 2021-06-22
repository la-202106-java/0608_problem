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
import la.dao.DAOException;
import la.dao.MaterialDAO;

@WebServlet("/ShiryoServlet")
public class ShiryoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");

			String action = request.getParameter("action");

			MaterialDAO dao = new MaterialDAO();
			if (action.equals("search")) {
				List<Material> list = new ArrayList<Material>();

				//資料ID検索だったら
				String str_id = request.getParameter("id");//資料ID
				if (str_id != null && str_id.length() != 0) {
					int id = Integer.parseInt(str_id);
					list = dao.findById(id);
				}

				//資料名検索（あいまい検索）だったら
				String title = request.getParameter("part_of_title");//資料名
				if (title != null && title.length() != 0) {
					list = dao.findByName(title);
				}

				//検索件数
				int count = list.size();
				request.setAttribute("count", count);

				if (list.isEmpty()) {//資料IDでも、資料名でも結果が拾えなかったら
					request.setAttribute("message", "検索結果が存在しません。");
					gotoPage(request, response, "/shiryo_search.jsp");
				} else {
					request.setAttribute("items", list);
					request.setAttribute("message", "検索成功!");
					gotoPage(request, response, "/shiryo_search.jsp");
				}

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
