package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class CommonDAO {

	private Connection con;

	public CommonDAO() {
	}

	public Connection getConnection() throws DAOException {

		try {
			Class.forName("org.postgresql.Driver");

			String url = "jdbc:postgresql:library";
			String user = "sd";
			String pass = "himitu";
			// データベースへの接続
			return DriverManager.getConnection(url, user, pass);

		} catch (Exception e) {
			throw new DAOException("接続に失敗しました");
		}
	}
}
