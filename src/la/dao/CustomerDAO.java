package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import la.bean.CustomerBean;

public class CustomerDAO {
	private Connection con;

	public CustomerDAO() throws DAOException {
		getConnection();
	}

	public CustomerBean findByEmailAndPassword(String email, String pass) throws DAOException {
		if (con == null) {
			getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM customer WHERE email = ? AND password = ?";
			st = con.prepareStatement(sql);
			st.setString(1, email);
			st.setString(2, pass);

			rs = st.executeQuery();

			CustomerBean customer = null;

			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				customer = new CustomerBean(code, name, address, tel,
						email, pass);
			}

			return customer;
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
	}

	private void getConnection() throws DAOException {
		try {
			Class.forName("org.postgresql.Driver");

			String url = "jdbc:postgresql:sample";
			String user = "student";
			String pass = "himitu";

			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			throw new DAOException("接続に失敗しました。");
		}
	}

	private void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}
}
