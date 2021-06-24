package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import la.bean.ReservationBean;

public class ReservationsDAOSub {
	private Connection con;

	public ReservationsDAOSub() throws DAOException {
		getConnection();
	}

	public int add(ReservationBean reservation) throws DAOException {
		if (con == null) {
			getConnection();
		}

		PreparedStatement st = null;

		try {
			String sql = "INSERT INTO reservations(member_id, plan_id, date, in_date, out_date, room) VALUES(?, ?, ?, ?, ?, ?)";

			st = con.prepareStatement(sql);

			st.setInt(1, reservation.getMemberId());
			st.setInt(2, reservation.getPlanId());
			st.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
			st.setDate(4, java.sql.Date.valueOf(reservation.getInDate()));
			st.setDate(5, java.sql.Date.valueOf(reservation.getOutDate()));
			st.setInt(6, reservation.getRoom());

			int rows = st.executeUpdate();

			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	private void getConnection() throws DAOException {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql:reservationsystem";
			String user = "adminuser";
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
