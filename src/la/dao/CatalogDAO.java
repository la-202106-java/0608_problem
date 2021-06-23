package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import la.bean.CatalogBean;

public class CatalogDAO {
	private Connection con;
	private CommonDAO dao;

	public CatalogDAO() throws DAOException {
		dao = new CommonDAO();
		con = dao.getConnection();
	}

	public List<CatalogBean> findAll() throws DAOException {

		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM catalog";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			List<CatalogBean> list = new ArrayList<CatalogBean>();
			while (rs.next()) {
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				int code = rs.getInt("code");
				String auther = rs.getString("auther");
				String publisher = rs.getString("publisher");
				Date publicationDate = rs.getDate("publication_date");
				CatalogBean bean = new CatalogBean(isbn, title, code, auther, publisher, publicationDate);
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

	public List<CatalogBean> findByTitle(String title) throws DAOException {
		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM catalog WHERE title LIKE ?";
			st = con.prepareStatement(sql);
			st.setString(1, "%" + title + "%");
			rs = st.executeQuery();
			List<CatalogBean> list = new ArrayList<CatalogBean>();
			while (rs.next()) {
				String isbn = rs.getString("isbn");
				int code = rs.getInt("code");
				String auther = rs.getString("auther");
				String publisher = rs.getString("publisher");
				Date publicationDate = rs.getDate("publication_date");
				CatalogBean bean = new CatalogBean(isbn, title, code, auther, publisher, publicationDate);
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

	public int addBooktoCatalog(String title, String isbn, int code, String author, String publisher,
			String publicationDate) throws DAOException {
		if (con == null) {
			con = dao.getConnection();
		}

		java.sql.Date publication_date = java.sql.Date.valueOf(publicationDate);

		PreparedStatement st = null;

		try {
			String sql = "INSERT INTO catalog VALUES (?,?,?,?,?,?)";
			st = con.prepareStatement(sql);
			st.setString(1, isbn);
			st.setString(2, title);
			st.setInt(3, code);
			st.setString(4, author);
			st.setString(5, publisher);
			st.setDate(6, publication_date);

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

	public boolean checkIsbn(String isbn) throws DAOException {
		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM catalog WHERE isbn = ?";
			st = con.prepareStatement(sql);
			st.setString(1, isbn);
			rs = st.executeQuery();

			return rs.next();
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

	// private
	private void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}

}
