package share;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class InsertTest2 {
	public static void main(String[] args) {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// URL、ユーザ名、パスワードの設定
		String url = "jdbc:postgresql:sample";
		String user = "student";
		String pass = "himitu";
		// SQL文の作成
		String sql = "INSERT INTO emp(code, name, age, tel) VALUES(8, '近藤', 29, '09-999-9999')";

		// データベースへの接続
		// PreparedStatementオブジェクトの取得
		try (Connection con = DriverManager.getConnection(url, user, pass);
				PreparedStatement st = con.prepareStatement(sql)) {
			// SQLの実行
			int rows = st.executeUpdate();
			System.out.println(rows + "件、データベースに追加しました");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}