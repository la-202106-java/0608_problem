package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.InnBean;

public class AdminInnDAO {
	private Connection con;

	public AdminInnDAO() throws DAOException {
		getConnection();
	}

	public int addInn(String name, int classCode, String postalCode, String address, java.sql.Time inTime,
			java.sql.Time outTime) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		String sql = "INSERT INTO inns(name, class_code, postal_code, address, in_time, out_time) VALUES(?,?,?,?,?,?)";
		try {
			st = con.prepareStatement(sql);
			st.setString(1, name);
			st.setInt(2, classCode);
			st.setString(3, postalCode);
			st.setString(4, address);
			st.setTime(5, inTime);
			st.setTime(6, outTime);
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

	public List<InnBean> searchInn(String name) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM inns WHERE name=?";
		try {
			st = con.prepareStatement(sql);
			st.setString(1, name);
			rs = st.executeQuery();
			List<InnBean> list = new ArrayList<InnBean>();
			while (rs.next()) {
				String Name = rs.getString("name");
				int classCode = rs.getInt("class_code");
				String postalCode = rs.getString("postal_code");
				String address = rs.getString("address");
				java.sql.Time inTime = rs.getTime("in_time");
				java.sql.Time outTime = rs.getTime("out_time");
				InnBean bean = new InnBean(Name, classCode, postalCode, address, inTime, outTime);
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
