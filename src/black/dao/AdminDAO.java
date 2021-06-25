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
		setSeq();
	}

	private void setSeq() throws DAOException {
		//idのシーケンスがなぜか常に1から始まってしまうので、
		//シーケンスの開始の値を現在のレコード数+1に設定
		//addの際のエラー回避

		String sql = "SELECT COUNT(*) FROM member";
		String sql2 = "SELECT SETVAL('member_id_seq', ? , false)";

		try (PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {
			if (rs.next()) {
				//レコード数取得
				int i = rs.getInt(1);

				try (PreparedStatement st2 = con.prepareStatement(sql2)) {
					st2.setInt(1, i + 1);
					st2.executeQuery();
				} catch (Exception e) {
					e.printStackTrace();
					throw new DAOException("レコードの取得に失敗しました。");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
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
			// カテゴリの設定
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
			// カテゴリ一覧をListとして返す
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
