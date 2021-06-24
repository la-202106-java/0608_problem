package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import la.bean.NowUserBean;

public class NowUserDAO {
	private Connection con;
	private CommonDAO dao;

	public NowUserDAO() throws DAOException {
		dao = new CommonDAO();
		con = dao.getConnection();
	}

	public NowUserBean register(String name, Date birthDate, Date joinDate, String address, String tel, String email)
			throws DAOException {

		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//会員IDの取得
			int id = 0;
			String id_sql = "SELECT nextval('now_user_id_seq')";
			st = con.prepareStatement(id_sql);
			rs = st.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			rs.close();
			st.close();

			//会員情報の追加SQL文
			String sql = "INSERT INTO now_user(id,name,birth_date,join_date,address,tel,email) VALUES(?,?,?,?,?,?,?)";
			st = con.prepareStatement(sql);

			st.setInt(1, id);
			st.setString(2, name);
			st.setDate(3, new java.sql.Date(birthDate.getTime()));
			st.setDate(4, new java.sql.Date(joinDate.getTime()));
			st.setString(5, address);
			st.setString(6, tel);
			st.setString(7, email);

			NowUserBean bean = new NowUserBean(id, name, birthDate, joinDate, address, tel, email);

			int rows = st.executeUpdate();
			return bean;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました");
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

	public NowUserBean findbyEmail(String email) throws DAOException {

		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM now_user WHERE email=?";
			st = con.prepareStatement(sql);
			st.setString(1, email);
			rs = st.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Date bitrhDate = rs.getDate("birth_date");
				Date joinDate = rs.getDate("join_date");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				NowUserBean bean = new NowUserBean(id, name, bitrhDate, joinDate, address, tel, email);
				return bean;
			} else {
				return null;

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}

	}

	public NowUserBean findByPrimaryKey(int key) throws DAOException {

		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM now_user WHERE now_user_id_seq=?";
			st = con.prepareStatement(sql);
			st.setInt(1, key);
			rs = st.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Date bitrhDate = rs.getDate("birth_date");
				Date joinDate = rs.getDate("join_date");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String email = rs.getString("email");
				NowUserBean bean = new NowUserBean(id, name, bitrhDate, joinDate, address, tel, email);
				return bean;
			} else {
				return null;

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}

	}

	public int deleteByID(int id) throws DAOException {

		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;

		try {
			String sql = "DELETE FROM now_user WHERE id=?";
			st = con.prepareStatement(sql);
			st.setInt(1, id);
			int rows = st.executeUpdate();
			return rows;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました");
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

	public int updateByID(int id, String name, String address, String tel, String email) throws DAOException {
		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;

		try {

			String sql = "UPDATE now_user SET name = ? , address = ? , tel = ? , email = ? WHERE id =?";
			st = con.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, address);
			st.setString(3, tel);
			st.setString(4, email);
			st.setInt(5, id);
			//SQLの実行
			int rows = st.executeUpdate();
			return rows;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました");
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

	// private
	private void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}
}
