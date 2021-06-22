package black.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDAO {
	private Connection con;

	public DepartmentDAO() throws DAOException {
		getConnection();
	}

	//分類コードを渡すと学部が返ってくる
	public String codeToDepartment(int code) throws DAOException {
		if (code < 0 || 10 < code) {
			return null;
		}

		if (con == null) {
			getConnection();
		}

		String sql = "SELECT department FROM department WHERE code=?";

		ResultSet rs = null;
		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, code);

			// SQLの実行
			rs = st.executeQuery();

			if (rs.next()) {
				return rs.getString("department");
			}
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if (rs != null)
					rs.close();
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
