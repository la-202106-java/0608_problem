package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import la.bean.ReservationBean;

public class ReservationsDAOSub {
	private Connection con;

	public ReservationsDAOSub() throws DAOException {
		getConnection();
	}

	public void add(ReservationBean reservation) throws DAOException {
		if (con == null) {
			getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			// reservationsテーブルとreservation_detailsテーブル二つにレコードを追加する
			// nextvalでシーケンスを抑える

			int id = 0;
			String sql = "SELECT nextval('reservations_id_seq')";

			st = con.prepareStatement(sql);
			rs = st.executeQuery();

			if (rs.next()) {
				id = rs.getInt(1);
			}

			rs.close();
			st.close();

			sql = "INSERT INTO reservations(id, member_id, plan_id, date, in_date, out_date, room) VALUES(?, ?, ?, ?, ?, ?, ?)";

			st = con.prepareStatement(sql);

			st.setInt(1, id);
			st.setInt(2, reservation.getMemberId());
			st.setInt(3, reservation.getPlanId());
			st.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
			st.setDate(5, java.sql.Date.valueOf(reservation.getInDate()));
			st.setDate(6, java.sql.Date.valueOf(reservation.getOutDate()));
			st.setInt(7, reservation.getRoom());

			st.executeUpdate();

			st.close();

			LocalDate in = LocalDate.parse(reservation.getInDate());
			LocalDate out = LocalDate.parse(reservation.getOutDate());

			sql = "INSERT INTO reservation_details (reservations_id, reserve_date, room) VALUES(?, ?, ?)";
			st = con.prepareStatement(sql);

			// reservation_detailsのreserve_dateには宿泊する日が入る
			// チェックインからチェックアウトまでの期間で宿泊する日をここではループさせながら追加している
			while (in.isBefore(out)) {
				st.setInt(1, id);
				st.setDate(2, java.sql.Date.valueOf(in));
				st.setInt(3, reservation.getRoom());
				st.executeUpdate();
				in = in.plusDays(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (st != null)
					st.close();
				if (rs != null)
					rs.close();
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
