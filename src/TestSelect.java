
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestSelect {
	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql:library";
			String user = "admin_user";
			String pass = "himitu";
			Connection con = DriverManager.getConnection(url, user, pass);
			String sql = "select * from material_category";

			PreparedStatement st = con.prepareStatement(sql);

			st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int code = rs.getInt("category_code");
				String name = rs.getString("category_name");
				System.out.println("categor_code:" + code + "  category_name:" + name);

			}
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
	}
}
