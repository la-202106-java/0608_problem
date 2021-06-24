package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.BookBean;

public class BookDAO {
	private Connection con;
	private CommonDAO dao;

	public BookDAO() throws DAOException {
		dao = new CommonDAO();
		con = dao.getConnection();
	}

	public List<BookBean> findAll() throws DAOException {

		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM book";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			List<BookBean> list = new ArrayList<BookBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				java.util.Date arrivalDate = rs.getDate("arrival_date");
				String note = rs.getString("note");
				BookBean bean = new BookBean(id, isbn, title, arrivalDate, note);
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

	public BookBean findByPrimaryKey(int bookID) throws DAOException {
		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM book WHERE id=?";
			st = con.prepareStatement(sql);
			st.setInt(1, bookID);
			rs = st.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				java.util.Date arrivalDate = rs.getDate("arrival_date");
				String note = rs.getString("note");
				BookBean bean = new BookBean(id, isbn, title, arrivalDate, note);
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

	public int addBook(String title, String isbn, String arrivalDate) throws DAOException {
		if (con == null) {
			con = dao.getConnection();
		}

		java.sql.Date arrival_date = java.sql.Date.valueOf(arrivalDate);

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			int id = 0;
			String id_sql = "SELECT nextval('book_id_seq')";
			st = con.prepareStatement(id_sql);
			rs = st.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			rs.close();
			st.close();

			String sql = "INSERT INTO book VALUES (?,?,?,?)";
			st = con.prepareStatement(sql);
			st.setInt(1, id);
			st.setString(2, isbn);
			st.setString(3, title);
			st.setDate(4, arrival_date);
			int rows = st.executeUpdate();
			return id;
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

	public List<BookBean> findByISBN(String isbn) throws DAOException {
		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM book WHERE isbn=?";
			st = con.prepareStatement(sql);
			st.setString(1, isbn);
			rs = st.executeQuery();

			List<BookBean> list = new ArrayList<BookBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				java.util.Date arrivalDate = rs.getDate("arrival_date");
				String note = rs.getString("note");
				BookBean bean = new BookBean(id, isbn, title, arrivalDate, note);
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

	public List<BookBean> findByTitle(String title) throws DAOException {
		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM book WHERE title LIKE ?";
			st = con.prepareStatement(sql);
			st.setString(1, "%" + title + "%");
			rs = st.executeQuery();

			List<BookBean> list = new ArrayList<BookBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String isbn = rs.getString("isbn");
				String fullTitle = rs.getString("title");
				java.util.Date arrivalDate = rs.getDate("arrival_date");
				String note = rs.getString("note");
				BookBean bean = new BookBean(id, isbn, fullTitle, arrivalDate, note);
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

	public int deleteByPrimaryKey(int id) throws DAOException {
		if (con == null)
			con = dao.getConnection();
		PreparedStatement st = null;

		try {
			String sql = "DELETE FROM book WHERE id = ?";
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

	// private
	private void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}

}
