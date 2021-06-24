package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import la.bean.InnBean;
import la.bean.MemberBean;
import la.bean.PlanBean;

public class PlansDAOSub {
	private Connection con;

	public PlansDAOSub() throws DAOException {
		getConnection();
	}

	public List<PlanBean> find(String checkIn, String checkOut) throws DAOException {
		if (con == null) {
			getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM reservations WHERE ここどうしよ";

			st = con.prepareStatement(sql);
			st.setString(1, email);

			rs = st.executeQuery();

			MemberBean member = null;

			while (rs.next()) {
				int id = rs.getInt("id");
				String password = rs.getString("pass");
				String name = rs.getString("name");
				String postalCode = rs.getString("postal_code");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String emailAddress = rs.getString("email_address");
				String birthDate = (rs.getDate("birth_date")).toString();
				LocalDate joinDate = (rs.getDate("join_date")).toLocalDate();

				LocalDate quiteDate;
				if (rs.getDate("quit_date") != null) {
					quiteDate = (rs.getDate("quit_date")).toLocalDate();
				} else {
					quiteDate = null;
				}

				member = new MemberBean(id, password, name, postalCode, address, tel, emailAddress, birthDate, joinDate,
						quiteDate);
			}

			return member;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		List<PlanBean> plans = new ArrayList<PlanBean>();
		PlanBean plan = new PlanBean();
		InnBean inn = new InnBean();

		plans.add(plan);

		return plans;
	}

	public PlanBean find(int planId) {
		PlanBean plan = new PlanBean();
		InnBean inn = new InnBean();

		inn.setName("テスト宿");
		plan.setInn(inn);
		plan.setContent("テスト夕食付");
		plan.setFee(70000);
		plan.setRoomMax(2);
		plan.setImgUrl("test.png");

		return plan;
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
