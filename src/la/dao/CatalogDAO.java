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

	// private
	private void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}

}
