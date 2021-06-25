package black.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import black.bean.AdminBean;

public class AdminDAO {
	private Connection con;
	private AdminBean bean;

	public AdminDAO() throws DAOException {
		getConnection();
	}

	public AdminBean findByEmailAndPassword(String mail, String passwd) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// SQL文の作成
			String sql = "SELECT * FROM admin where email = ? AND pass = ?";

			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			// 設定
			st.setString(1, mail);
			st.setString(2, passwd);
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			while (rs.next()) {
				int id = rs.getInt("id");
				String email = rs.getString("email");
				String pass = rs.getString("pass");
				bean = new AdminBean(id, email, pass);
			}

			return bean;
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
			String url = "jdbc:postgresql:webtext";
			String user = "webtextuser";
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
