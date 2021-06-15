package la;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Lesson10 {
	public static void main(String[] args) {
		Connection con;
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql:sample";
			String user = "student";
			String pass = "himitu";
			con = DriverManager.getConnection(url, user, pass);
			//			String sql = "INSERT INTO emp(code,name,age,tel) VALUES (8,'近藤',29,'09-999-9999')";
			//			PreparedStatement st = con.prepareStatement(sql);
			//			int rows = st.executeUpdate();
			//			System.out.println(rows + "件、データベースに追加しました。");
			//			String sql = "INSERT INTO emp(code,name,age,tel) VALUES (8,'近藤',29,'09-999-9999')";
			//			PreparedStatement st = con.prepareStatement(sql);
			String sql = "select * from emp order by age ";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				System.out.print(rs.getInt("code") + ":");
				System.out.print(rs.getString("name") + ":");
				System.out.print(rs.getInt("age") + ":");
				System.out.println(rs.getString("tel"));
			}
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		} finally {

		}
	}
}
