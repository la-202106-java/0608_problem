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
import la.bean.PlanBean;

public class AdminPlanDAO {
	private Connection con;

	public AdminPlanDAO() throws DAOException {
		getConnection();
	}

	public int addPlan(int innID, String content, int fee, int roomMax, String imgUrl) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		String sql = "INSERT INTO stay_plans(inn_id, contents, fee, room_max, img_url) VALUES(?,?,?,?,?)";
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, innID);
			st.setString(2, content);
			st.setInt(3, fee);
			st.setInt(4, roomMax);
			st.setString(5, imgUrl);
			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		} finally {
			try {
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}
	}

	public List<PlanBean> searchPlan(String name, boolean sakujyoiri) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql;
		if (sakujyoiri) {
			//			sql = "select * from inns a inner join stay_plans b on a.id = b.inn_id WHERE name LIKE ?";
			sql = "select plan_id, id, name, contents, fee, room_max, img_url, b.delete_date as stay_plans_delete_date from inns a inner join stay_plans b on a.id = b.inn_id WHERE name LIKE ?";
		} else {
			//			sql = "select * from inns a inner join stay_plans b on a.id = b.inn_id WHERE name LIKE ? AND stay_plans_delete_date is null";
			sql = "select plan_id, id, name, contents, fee, room_max, img_url, b.delete_date as stay_plans_delete_date from inns a inner join stay_plans b on a.id = b.inn_id WHERE name LIKE ? AND b.delete_date is null";
		}
		try {
			st = con.prepareStatement(sql);
			st.setString(1, "%" + name + "%");
			rs = st.executeQuery();
			List<PlanBean> list = new ArrayList<PlanBean>();
			while (rs.next()) {
				PlanBean bean = new PlanBean();
				bean.setPlanId(rs.getInt("plan_id"));
				int id = rs.getInt("id");
				String Name = rs.getString("name");
				bean.setContent(rs.getString("contents"));
				bean.setFee(rs.getInt("fee"));
				bean.setRoomMax(rs.getInt("room_max"));
				bean.setImgUrl(rs.getString("img_url"));
				if (rs.getDate("stay_plans_delete_date") != null) {
					bean.setDeleteDate(rs.getDate("stay_plans_delete_date").toLocalDate());
				}
				InnBean innBean = new InnBean(Name);
				innBean.setId(id);
				bean.setInn(innBean);
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

	public int updatePlan(int planID, int innID, String content, int fee, int roomMax, String imgUrl)
			throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		String sql = "UPDATE stay_plans SET inn_id=?, contents=?, fee=?, room_max=?, img_url=? WHERE plan_id=?";
		try {
			st = con.prepareStatement(sql);

			st.setInt(1, innID);
			st.setString(2, content);
			st.setInt(3, fee);
			st.setInt(4, roomMax);
			st.setString(5, imgUrl);
			st.setInt(6, planID);
			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		} finally {
			try {
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}
	}

	public int quitPlan(int id) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;

		LocalDate now = LocalDate.now();
		java.sql.Date sqlDate = java.sql.Date.valueOf(now);

		try {
			if (id == -1) {
				//id入力なしの場合intの「-1」を受けてリターン
				return 0;
			}
			String sql = "UPDATE stay_plans SET delete_date = ? WHERE plan_id = ?";
			st = con.prepareStatement(sql);
			st.setDate(1, sqlDate);
			st.setInt(2, id);
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
