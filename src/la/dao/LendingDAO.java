package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import la.bean.LendingBean;

public class LendingDAO {
	private Connection con;
	private CommonDAO dao;

	public LendingDAO() throws DAOException {
		dao = new CommonDAO();
		con = dao.getConnection();
	}

	public List<LendingBean> findAll() throws DAOException {

		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM lending";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			List<LendingBean> list = new ArrayList<LendingBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int bookId = rs.getInt("book_id");
				int userId = rs.getInt("user_id");
				Date lendingDate = rs.getDate("lending_date");
				Date deadline = rs.getDate("deadline");
				String note = rs.getString("note");
				LendingBean bean = new LendingBean(id, bookId, userId, lendingDate, deadline, note);
				list.add(bean);
			}
			return list;
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

	public List<LendingBean> findByUserId(int userId) throws DAOException {

		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM lending where user_id = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, userId);
			rs = st.executeQuery();
			List<LendingBean> list = new ArrayList<LendingBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int bookId = rs.getInt("book_id");
				Date lendingDate = rs.getDate("lending_date");
				Date deadline = rs.getDate("deadline");
				String note = rs.getString("note");
				LendingBean bean = new LendingBean(id, bookId, userId, lendingDate, deadline, note);
				list.add(bean);
			}
			return list;
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

	// private
	private void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}

	// 資料IDから探す
	public LendingBean findByBookId(int bookId) throws DAOException {
		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM lending where book_id = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, bookId);
			rs = st.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				Date lendingDate = rs.getDate("lending_date");
				Date deadline = rs.getDate("deadline");
				String note = rs.getString("note");
				LendingBean bean = new LendingBean(id, bookId, userId, lendingDate, deadline, note);
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

	// 削除する
	public int removeByBookId(int bookID) throws DAOException {
		if (con == null)
			con = dao.getConnection();
		PreparedStatement st = null;

		try {
			String sql = "DELETE FROM lending WHERE book_id = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, bookID);

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
}
