package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import la.bean.ReturnedLendingBean;

public class LendingSearchDAO {
	private Connection con;
	private CommonDAO dao;

	public LendingSearchDAO() throws DAOException {
		dao = new CommonDAO();
		con = dao.getConnection();
	}

	public List<ReturnedLendingBean> findAllFromLending() throws DAOException {

		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM lending";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			List<ReturnedLendingBean> list = new ArrayList<ReturnedLendingBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int bookId = rs.getInt("book_id");
				int userId = rs.getInt("user_id");
				Date lendingDate = rs.getDate("lending_date");
				Date deadline = rs.getDate("deadline");
				Date returnDate = null;
				String note = rs.getString("note");
				ReturnedLendingBean bean = new ReturnedLendingBean(id, bookId, userId, lendingDate, deadline,
						returnDate, note);
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

	public List<ReturnedLendingBean> findByUserId(int userId) throws DAOException {

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
			List<ReturnedLendingBean> list = new ArrayList<ReturnedLendingBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int bookId = rs.getInt("book_id");
				Date lendingDate = rs.getDate("lending_date");
				Date deadline = rs.getDate("deadline");
				Date retrunDate = null;
				String note = rs.getString("note");
				ReturnedLendingBean bean = new ReturnedLendingBean(id, bookId, userId, lendingDate, deadline,
						retrunDate, note);
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

	public List<ReturnedLendingBean> findAllByUserId(int userId) throws DAOException {
		if (con == null) {
			con = dao.getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT id,book_id,user_id,lending_date,deadline,NULL as return_date,note FROM lending where user_id = ? UNION ALL SELECT id,book_id,user_id,lending_date,deadline,return_date,note FROM returned_lending where user_id = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, userId);
			st.setInt(2, userId);
			rs = st.executeQuery();
			List<ReturnedLendingBean> list = new ArrayList<ReturnedLendingBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int bookId = rs.getInt("book_id");
				Date lendingDate = rs.getDate("lending_date");
				Date deadline = rs.getDate("deadline");
				Date retrunDate = rs.getDate("return_date");
				String note = rs.getString("note");
				ReturnedLendingBean bean = new ReturnedLendingBean(id, bookId, userId, lendingDate, deadline,
						retrunDate, note);
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

	// 資料IDから探す
	public ReturnedLendingBean findByBookId(int bookId) throws DAOException {
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
				Date returnDate = null;
				String note = rs.getString("note");
				ReturnedLendingBean bean = new ReturnedLendingBean(id, bookId, userId, lendingDate, deadline,
						returnDate, note);
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

	// 延滞資料を取得
	public List<ReturnedLendingBean> findOverdue() throws DAOException {
		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		Date current_date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(current_date);
		java.sql.Date currentDate = java.sql.Date.valueOf(strDate);

		try {
			String sql = "SELECT * FROM lending where ? > deadline";
			st = con.prepareStatement(sql);
			st.setDate(1, currentDate);
			rs = st.executeQuery();
			List<ReturnedLendingBean> list = new ArrayList<ReturnedLendingBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int bookId = rs.getInt("book_id");
				int userId = rs.getInt("user_id");
				Date lendingDate = rs.getDate("lending_date");
				Date deadline = rs.getDate("deadline");
				Date retrunDate = null;
				String note = rs.getString("note");
				ReturnedLendingBean bean = new ReturnedLendingBean(id, bookId, userId, lendingDate, deadline,
						retrunDate, note);
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

}
