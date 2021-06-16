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

	public CustomerBean findByEmailAndPassword(String email, String password) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// SQL文の作成
			String sql = "SELECT * FROM customer WHERE email = ? and password = ?";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			// カテゴリの設定
			st.setString(1, email);
			st.setString(2, password);
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			if (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String r_email = rs.getString("email");
				String r_password = rs.getString("password");
				CustomerBean bean = new CustomerBean(code, name, address, tel, r_email, r_password);
				return bean;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
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

	private void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}
}
