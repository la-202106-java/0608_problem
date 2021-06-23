package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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
			String sql = "SELECT * FROM reservation WHERE isbn=? AND reserved_date is NULL ORDER BY reservation_time ASC LIMIT 1";
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

	// 新しい予約を追加
	public int addReservation(String isbn, int userID, java.time.LocalDateTime reservationTime) throws DAOException {

		if (con == null) {
			con = dao.getConnection();
		}

		// localtimeとsqlの変換
		Timestamp time = Timestamp.valueOf(reservationTime);

		String sql = "INSERT INTO reservation(isbn, user_id, reservation_time) VALUES(?, ?, ?)";

		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, isbn);
			st.setInt(2, userID);
			st.setTimestamp(3, time);

			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました");
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
