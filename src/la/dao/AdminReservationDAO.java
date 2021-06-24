package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.InnBean;
import la.bean.MemberBean;
import la.bean.PlanBean;
import la.bean.ReservationBean;

public class AdminReservationDAO {
	private Connection con;

	public AdminReservationDAO() throws DAOException {
		getConnection();
	}

	public List<ReservationBean> searchReservation(int PlanID) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "SELECT i.name AS innsName, r.id, s.contents, r.in_date,"
				+ "	r.out_date,"
				+ "	m.name AS memberName,"
				+ "	m.postal_code,"
				+ "	m.address,"
				+ "	m.tel,"
				+ "	m.email_address "
				+ "FROM"
				+ "	inns i "
				+ "INNER JOIN "
				+ "	stay_plans s "
				+ "ON\r\n"
				+ "	i.id = s.inn_id "
				+ "INNER JOIN "
				+ "	reservations r "
				+ "ON "
				+ "	r.plan_id = s.plan_id "
				+ "INNER JOIN "
				+ "	members m "
				+ "ON "
				+ "	m.id = r.member_id WHERE r.plan_id = ?";
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, PlanID);
			rs = st.executeQuery();
			List<ReservationBean> list = new ArrayList<ReservationBean>();
			while (rs.next()) {
				ReservationBean rbean = new ReservationBean();

				String InnName = rs.getString("innsName");
				InnBean ibean = new InnBean(InnName);

				PlanBean pbean = new PlanBean();
				pbean.setContent(rs.getString("contents"));
				pbean.setInn(ibean);

				MemberBean mbean = new MemberBean();
				String MemberName = rs.getString("memberName");
				mbean.setName(MemberName);
				String MemberPostalcode = rs.getString("postal_code");
				mbean.setPostalCode(MemberPostalcode);
				String MemberAddress = rs.getString("address");
				mbean.setAddress(MemberAddress);
				mbean.setEmailAddress(rs.getString("email_address"));
				mbean.setTel(rs.getString("tel"));

				if (rs.getDate("in_date") != null) {
					rbean.setInDate(rs.getDate("in_date").toString());
				}
				if (rs.getDate("out_date") != null) {
					rbean.setOutDate(rs.getDate("out_date").toString());
				}
				rbean.setId(rs.getInt("id"));
				rbean.setPlan(pbean);
				rbean.setMember(mbean);
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
