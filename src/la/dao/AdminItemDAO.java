package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.AdminItemBean;

public class AdminItemDAO {
	private Connection con;

	public AdminItemDAO() throws DAOException {
		getConnection();
	}

	public List<AdminItemBean> findAll() throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM item ORDER BY code";

		try {
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			List<AdminItemBean> list = new ArrayList<AdminItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				int categoryCode = rs.getInt("category_code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				AdminItemBean bean = new AdminItemBean(code, categoryCode, name, price);
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

	private void getConnection() throws DAOException {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql:sample";
			String user = "student";
			String pass = "himitu";
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			throw new DAOException("接続に失敗しました");
		}

	}

	private void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}

}
