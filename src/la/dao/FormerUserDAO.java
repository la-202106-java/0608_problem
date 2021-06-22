package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import la.bean.FormerUserBean;

public class FormerUserDAO {
	private Connection con;
	private CommonDAO dao;

	public FormerUserDAO() throws DAOException {
		dao = new CommonDAO();
		con = dao.getConnection();
	}

	public FormerUserBean findbyEmail(String email) throws DAOException {

		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM former_user WHERE email=?";
			st = con.prepareStatement(sql);
			st.setString(1, email);
			rs = st.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Date bitrhDate = rs.getDate("birth_date");
				Date joinDate = rs.getDate("join_date");
				Date quitDate = rs.getDate("quit_date");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				FormerUserBean bean = new FormerUserBean(id, name, bitrhDate, joinDate, quitDate, address, tel, email);
				return bean;
			} else {
				return null;

			}
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
