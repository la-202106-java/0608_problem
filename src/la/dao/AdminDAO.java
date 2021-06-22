package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
	private Connection con;

	public AdminDAO() throws DAOException {
		getConnection();
	}

	public boolean isAdmin(String email, String password) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM admin WHERE email_address=?";
		try {
			st = con.prepareStatement(sql);
			st.setString(1, email);
			rs = st.executeQuery();
			String pass = null;
			while (rs.next()) {
				pass = rs.getString("pass");
			}
			return (pass.equals(password));
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
