package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.MemberBean;

public class MembersDAO {
	private Connection con;

	public MembersDAO() throws DAOException {
		getConnection();
	}

	public List<MemberBean> findAll() throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM members";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			List<MemberBean> list = new ArrayList<MemberBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				String postal_code = rs.getString("postal_code");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String email_address = rs.getString("email_address");
				java.sql.Date birth_date = rs.getDate("birth_date");
				java.sql.Date join_date = rs.getDate("join_date");
				java.sql.Date quit_date = rs.getDate("quit_date");
				MemberBean bean = new MemberBean(id, pass, name, postal_code, address, tel,
						email_address, birth_date, join_date, quit_date);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
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
