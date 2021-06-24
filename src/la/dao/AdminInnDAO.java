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

public class AdminInnDAO {
	private Connection con;

	public AdminInnDAO() throws DAOException {
		getConnection();
	}

	public int addInn(String name, int classCode, String postalCode, String address, String inTime,
			String outTime) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		String sql = "INSERT INTO inns(name, class_code, postal_code, address, in_time, out_time) VALUES(?,?,?,?,?,?)";
		try {
			st = con.prepareStatement(sql);

			java.sql.Time timeInTime = java.sql.Time.valueOf(inTime);
			java.sql.Time timeOutTime = java.sql.Time.valueOf(outTime);

			st.setString(1, name);
			st.setInt(2, classCode);
			st.setString(3, postalCode);
			st.setString(4, address);
			st.setTime(5, timeInTime);
			st.setTime(6, timeOutTime);
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

	public int updateInn(int id, String name, int classCode, String postalCode, String address, String inTime,
			String outTime) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		String sql = "UPDATE inns SET name=?, class_code=?, postal_code=?, address=?, in_time=?, out_time=? WHERE id=?";
		try {
			st = con.prepareStatement(sql);

			java.sql.Time timeInTime = java.sql.Time.valueOf(inTime);
			java.sql.Time timeOutTime = java.sql.Time.valueOf(outTime);

			st.setString(1, name);
			st.setInt(2, classCode);
			st.setString(3, postalCode);
			st.setString(4, address);
			st.setTime(5, timeInTime);
			st.setTime(6, timeOutTime);
			st.setInt(7, id);
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

	public List<InnBean> searchInn(String name, boolean sakujyoiri) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql;
		if (sakujyoiri) {
			sql = "SELECT * FROM inns WHERE name LIKE ?";
		} else {
			sql = "SELECT * FROM inns WHERE name LIKE ? AND delete_date is null";
		}
		try {
			st = con.prepareStatement(sql);
			st.setString(1, "%" + name + "%");
			rs = st.executeQuery();
			List<InnBean> list = new ArrayList<InnBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String Name = rs.getString("name");
				int classCode = rs.getInt("class_code");
				String postalCode = rs.getString("postal_code");
				String address = rs.getString("address");
				String inTime = (rs.getTime("in_time")).toString();
				String outTime = (rs.getTime("out_time")).toString();
				//				java.sql.Time inTime = rs.getTime("in_time");
				//				java.sql.Time outTime = rs.getTime("out_time");
				InnBean bean = new InnBean(id, Name, classCode, postalCode, address, inTime, outTime);
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

	public int quitInn(String name) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;

		LocalDate now = LocalDate.now();
		java.sql.Date sqlDate = java.sql.Date.valueOf(now);

		try {
			String sql = "UPDATE inns SET delete_date = ? WHERE name = ?";
			st = con.prepareStatement(sql);
			st.setDate(1, sqlDate);
			st.setString(2, name);
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
