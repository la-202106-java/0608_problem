package la;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest {
	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql:sample";
			String user = "student";
			String pass = "himitu";
			Connection con = DriverManager.getConnection(url, user, pass);
			String sql = "INSERT INTO emp(code,name,age,tel) VALUES (8,'近藤',29,'09-999-9999')";
			PreparedStatement st = con.prepareStatement(sql);
			int rows = st.executeUpdate();
			System.out.println(rows + "件、データベースに追加しました。");
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
	}
}
