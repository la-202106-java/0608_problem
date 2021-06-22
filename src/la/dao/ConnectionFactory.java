package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public final class ConnectionFactory {

	private static Connection con;

	public static Connection createConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql:library";
			String user = "admin_user";
			String pass = "himitu";
			con = DriverManager.getConnection(url, user, pass);

		} catch (Exception e) {
			System.out.println("Connectionを作成する時に例外が発生しました。");
			e.printStackTrace();
		}
		return con;
	}
}
