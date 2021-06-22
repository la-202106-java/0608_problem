package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import la.bean.NowUserBean;

public class ReservationDAO {
	private Connection con;
	private CommonDAO dao;

	public ReservationDAO() throws DAOException {
		dao = new CommonDAO();
		con = dao.getConnection();
	}

	public NowUserBean findbyISBN(String isbn) throws DAOException {

		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM reservation WHERE isbn=? AND reservation_time=NULL ORDER BY reservation_time ASC LIMIT 1";
			st = con.prepareStatement(sql);
			st.setString(1, isbn);
			rs = st.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("user_id");
				NowUserDAO dao = new NowUserDAO();
				NowUserBean bean = dao.findByPrimaryKey(id);

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
