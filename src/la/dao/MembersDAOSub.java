package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import la.bean.MemberBean;

// ログイン時の処理を行うメソッド（emailが引数として渡られた際に、emaiが一致する会員情報を返却する）
// 会員登録を行うメソッド

public class MembersDAOSub {
	private Connection con;

	public MembersDAOSub() throws DAOException {
		getConnection();
	}

	// emailを元に会員情報を取得する
	public MemberBean find(String email) throws DAOException {
		if (con == null) {
			getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM members WHERE email_address=?";

			st = con.prepareStatement(sql);
			st.setString(1, email);

			rs = st.executeQuery();

			MemberBean member = null;

			while (rs.next()) {
				int id = rs.getInt("id");
				String password = rs.getString("pass");
				String name = rs.getString("name");
				String postalCode = rs.getString("postal_code");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String emailAddress = rs.getString("email_address");
				String birthDate = (rs.getDate("birth_date")).toString();
				LocalDate joinDate = (rs.getDate("join_date")).toLocalDate();

				LocalDate quiteDate;
				if (rs.getDate("quit_date") != null) {
					quiteDate = (rs.getDate("quit_date")).toLocalDate();
				} else {
					quiteDate = null;
				}

				member = new MemberBean(id, password, name, postalCode, address, tel, emailAddress, birthDate, joinDate,
						quiteDate);
			}

			return member;
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
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}

	}

	// 会員登録を行うメソッド
	public int registration(MemberBean member) throws DAOException {
		if (con == null) {
			getConnection();
		}

		PreparedStatement st = null;

		try {
			String sql = "INSERT INTO members(pass, name, postal_code, address, tel, email_address, birth_date, join_date) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

			st = con.prepareStatement(sql);
			st.setString(1, member.getPassword());
			st.setString(2, member.getName());
			st.setString(3, member.getPostalCode());
			st.setString(4, member.getAddress());
			st.setString(5, member.getTel());
			st.setString(6, member.getEmailAddress());
			st.setDate(7, java.sql.Date.valueOf(member.getBirthDate()));
			st.setDate(8, java.sql.Date.valueOf(LocalDate.now()));

			int rows = st.executeUpdate();

			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
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
