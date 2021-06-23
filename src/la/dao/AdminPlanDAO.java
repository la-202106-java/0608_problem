package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminPlanDAO {
	private Connection con;

	public AdminPlanDAO() throws DAOException {
		getConnection();
	}

	public int addPlan(int innID, String content, int fee, int roomMax, String imgUrl) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		String sql = "INSERT INTO stay_plans(inn_id, contents, fee, room_max, img_url) VALUES(?,?,?,?,?)";
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, innID);
			st.setString(2, content);
			st.setInt(3, fee);
			st.setInt(4, roomMax);
			st.setString(5, imgUrl);
			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		} finally {
			try {
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}
	}

	private void getConnection() throws DAOException {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql:reservationsystem";
			String user = "adminuser";
			String pass = "himitu";
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			throw new DAOException("接続に失敗しました");
		}

	}

	private void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}

}
