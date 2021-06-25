package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.InnBean;
import la.bean.PlanBean;
import la.bean.ReservationBean;

public class UserReservationDAO {
	private Connection con;

	public UserReservationDAO() throws DAOException {
		getConnection();
	}

	public List<ReservationBean> searchReservation(int memberID) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "SELECT r.id,"
				+ "	i.name, "
				+ "	i.address, "
				+ "	s.contents, "
				+ "	r.in_date, "
				+ "	r.out_date, "
				+ "	i.in_time, "
				+ "	i.out_time, "
				+ "	r.room, "
				+ "	s.fee "
				+ "FROM "
				+ "	inns i "
				+ "INNER JOIN "
				+ "	stay_plans s "
				+ "ON "
				+ "	i.id = s.inn_id "
				+ "INNER JOIN "
				+ "	reservations r "
				+ "ON "
				+ "	s.plan_id = r.plan_id WHERE member_id = ?"
				+ "AND cansel_check = FALSE";
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, memberID);
			rs = st.executeQuery();
			List<ReservationBean> list = new ArrayList<ReservationBean>();
			while (rs.next()) {
				ReservationBean rbean = new ReservationBean();

				String InnName = rs.getString("name");
				InnBean ibean = new InnBean(InnName);
				ibean.setAddress(rs.getString("address"));
				if (rs.getTime("in_time") != null) {
					ibean.setInTime(rs.getTime("in_time").toString());
				}
				if (rs.getTime("out_time") != null) {
					ibean.setOutTime(rs.getTime("out_time").toString());
				}

				PlanBean pbean = new PlanBean();
				pbean.setContent(rs.getString("contents"));
				pbean.setFee(rs.getInt("fee"));
				pbean.setInn(ibean);

				if (rs.getDate("in_date") != null) {
					rbean.setInDate(rs.getDate("in_date").toString());
				}
				if (rs.getDate("out_date") != null) {
					rbean.setOutDate(rs.getDate("out_date").toString());
				}
				rbean.setId(rs.getInt("id"));
				rbean.setRoom(rs.getInt("room"));
				rbean.setPlan(pbean);
				list.add(rbean);
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

	//予約番号から予約を削除
	public int quitReservation(int reservationID) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;

		try {
			String sql = "UPDATE reservations SET cancel_check = ? WHERE id = ?";
			st = con.prepareStatement(sql);
			st.setBoolean(1, true);
			st.setInt(2, reservationID);
			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
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
