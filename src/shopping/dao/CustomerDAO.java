package shopping.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import shopping.bean.CustomerBean;

public class CustomerDAO {
	private Connection con;

	public CustomerDAO() throws DAOException {
		getConnection();
	}

	public CustomerBean findByEmailAndPassword(String email, String password) throws DAOException {
		if (con == null)
			getConnection();

		String sql = "SELECT * FROM customer WHERE email = ? AND password = ? ";
		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, email);
			st.setString(2, password);

			try (ResultSet rs = st.executeQuery()) {

				if (rs.next()) {
					int code = rs.getInt("code");
					String name = rs.getString("name");
					String address = rs.getString("address");
					String tel = rs.getString("tel");

					CustomerBean bean = new CustomerBean(code, name, address, tel, email, password);
					return bean;
				} else {
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("レコードの操作に失敗しました");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました");
		}
	}

	private void getConnection() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
			// URL、ユーザ名、パスワードの設定
			String url = "jdbc:postgresql:sample";
			String user = "student";
			String pass = "himitu";
			// データベースへの接続
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("接続に失敗しました。");
		}
	}

}
