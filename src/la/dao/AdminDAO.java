package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.AdminBean;

public class AdminDAO {
	private Connection con;
	private CommonDAO dao;

	public AdminDAO() throws DAOException {
		dao = new CommonDAO();
		con = dao.getConnection();
	}

	public List<AdminBean> findAll() throws DAOException {

		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM admin";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			List<AdminBean> list = new ArrayList<AdminBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String email = rs.getString("email");
				String password = rs.getString("password");
				AdminBean bean = new AdminBean(id, email, password);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}

	}

	// private
	private void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}
}
