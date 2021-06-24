package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.BookBean;

public class ReservedDAO {
	private Connection con;
	private CommonDAO dao;

	public ReservedDAO() throws DAOException {
		dao = new CommonDAO();
		con = dao.getConnection();
	}

	// UserIDから取り置きを検索して、Bookのlistを返す
	public List<BookBean> findByUserID(int id) throws DAOException {

		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM reserved WHERE user_id=?";
			st = con.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();

			List<BookBean> list = new ArrayList<BookBean>();
			while (rs.next()) {
				int bookID = rs.getInt("book_id");
				BookDAO dao = new BookDAO();
				BookBean bean = dao.findByPrimaryKey(bookID);
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

	// UserIDから取り置きを検索して、true or false
	public boolean reservedExists(int id) throws DAOException {

		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM reserved WHERE user_id=?";
			st = con.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				return false;
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

	// UserIDとBookIDと日付を指定して取り置きを追加する
	public int addReserved(int userID, int bookID, java.util.Date reservedDate) throws DAOException {

		if (con == null) {
			con = dao.getConnection();
		}

		// utilとsqlの変換
		java.sql.Date date = new java.sql.Date(reservedDate.getTime());

		String sql = "INSERT INTO reserved(user_id, book_id, reserved_date) VALUES(?, ?, ?)";

		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, userID);
			st.setInt(2, bookID);
			st.setDate(3, date);

			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました");
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
