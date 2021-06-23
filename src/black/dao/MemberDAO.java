package black.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import black.bean.MemberBean;

public class MemberDAO {
	private Connection con;

	public MemberDAO() throws DAOException {

	}

	//会員検索（ID）
	public MemberBean findMember(int id) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "SELECT * FROM member WHERE id=?";

		ResultSet rs = null;
		try (PreparedStatement st = con.prepareStatement(sql)) {

			st.setInt(1, id);

			// SQLの実行
			rs = st.executeQuery();

			if (rs.next()) {
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String email = rs.getString("email");
				Date birthday = rs.getDate("birthday");
				Date joinDate = rs.getDate("join_date");
				String pass = rs.getString("pass");

				MemberBean bean = new MemberBean(id, name, address, tel, email, birthday, joinDate, pass);
				return bean;
			}
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if (rs != null)
					rs.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}

	}

	//会員検索（ID以外）
	public List<MemberBean> findMember(String name, String address,
			String tel, String email, Date birthday) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "SELECT * FROM member WHERE name LIKE ? AND adress LIKE ?"
				+ " AND tel LIKE ? AND email LIKE ?";
		String sql2 = "";

		if (birthday != null) {
			sql2 = " AND birthday=?";
		}

		ResultSet rs = null;
		try (PreparedStatement st = con.prepareStatement(sql + sql2)) {
			st.setString(1, "%" + name + "%");
			st.setString(2, "%" + address + "%");
			st.setString(3, "%" + tel + "%");
			st.setString(4, "%" + email + "%");
			if (birthday != null) {
				st.setDate(5, birthday);//aあとで直す

			}

			// SQLの実行
			rs = st.executeQuery();

			//検索結果をひとつずつ取り出してリストに入れる
			List<MemberBean> list = new ArrayList<MemberBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String _name = rs.getString("name");
				String _address = rs.getString("address");
				String _tel = rs.getString("tel");
				String _email = rs.getString("email");
				Date _birthday = rs.getDate("birthday");
				Date joinDate = rs.getDate("joinDate");
				String pass = rs.getString("pass");

				MemberBean bean = new MemberBean(id, _name, _address, _tel, _email, _birthday, joinDate, pass);
				list.add(bean);
			}
			//会員一覧リストを返す
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if (rs != null)
					rs.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	//会員登録（新規）
	public void addMember(String name, String address,
			String tel, String email, Date birthday, String pass) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "INSERT INTO member(name, address, tel, email, birthday, pass, join_date)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?)";

		Date day = new Date(System.currentTimeMillis());

		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, name);
			st.setString(2, address);
			st.setString(3, tel);
			st.setString(4, email);
			st.setDate(5, birthday);//あとでなおす
			st.setString(6, pass);
			st.setDate(7, day);

			//SQL実行
			st.executeUpdate();

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

	//会員情報更新
	public void updateMenber(int id, String name, String address,
			String tel, String email, Date birthday, String pass) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "UPDATE member SET name=? address=? tel=? email=? birthday=? pass=?"
				+ " WHERE id=?";

		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, name);
			st.setString(2, address);
			st.setString(3, tel);
			st.setString(4, email);
			st.setDate(5, birthday);//あとでなおす
			st.setString(6, pass);
			st.setInt(7, id);

			//SQL実行
			st.executeUpdate();

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

	//退会処理
	public void memberQuit(int id) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "UPDATE member SET quit_date=? WHERE id=?";

		Date today = new Date(System.currentTimeMillis());

		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setDate(1, today);
			st.setInt(2, id);

			//SQL実行
			st.executeUpdate();

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
