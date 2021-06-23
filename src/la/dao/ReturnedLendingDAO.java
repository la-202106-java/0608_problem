package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import la.bean.LendingBean;
import la.bean.ReturnedLendingBean;

public class ReturnedLendingDAO {
	private Connection con;
	private CommonDAO dao;

	public ReturnedLendingDAO() throws DAOException {
		dao = new CommonDAO();
		con = dao.getConnection();
	}

	public List<ReturnedLendingBean> findAll() throws DAOException {

		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM returned_lending";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			List<ReturnedLendingBean> list = new ArrayList<ReturnedLendingBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int bookId = rs.getInt("book_id");
				int userId = rs.getInt("user_id");
				Date lendingDate = rs.getDate("lending_date");
				Date deadline = rs.getDate("deadline");
				Date returnDate = rs.getDate("return_date");
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
			String sql = "SELECT * FROM returned_lending where user_id = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, userId);
			rs = st.executeQuery();
			List<ReturnedLendingBean> list = new ArrayList<ReturnedLendingBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int bookId = rs.getInt("book_id");
				Date lendingDate = rs.getDate("lending_date");
				Date deadline = rs.getDate("deadline");
				Date returnDate = rs.getDate("return_date");
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

	// private
	private void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}

	public int addReturnedLending(LendingBean lending) throws DAOException {
		if (con == null)
			con = dao.getConnection();
		PreparedStatement st = null;

		try {
			String sql = "INSERT INTO returned_lending(book_id, user_id, lending_date, deadline, return_date, note) VALUES (?,?,?,?,?,?)";
			st = con.prepareStatement(sql);
			st.setInt(1, lending.getBookId());
			st.setInt(2, lending.getUserId());
			st.setDate(3, new java.sql.Date(lending.getLendingDate().getTime()));
			st.setDate(4, new java.sql.Date(lending.getDeadline().getTime()));
			st.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
			st.setString(6, lending.getNote());

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
