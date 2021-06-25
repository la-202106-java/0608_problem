package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import la.bean.BookBean;
import la.bean.DiscardedBookBean;

public class DiscardedBookDAO {
	private Connection con;
	private CommonDAO dao;

	public DiscardedBookDAO() throws DAOException {
		dao = new CommonDAO();
		con = dao.getConnection();
	}

	public List<DiscardedBookBean> findAll() throws DAOException {

		if (con == null) {
			con = dao.getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM discarded_book";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			List<DiscardedBookBean> list = new ArrayList<DiscardedBookBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				Date arrivalDate = rs.getDate("arrival_date");
				Date discardDate = rs.getDate("discard_date");
				String note = rs.getString("note");
				DiscardedBookBean bean = new DiscardedBookBean(id, isbn, title, arrivalDate, discardDate, note);
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

	public int addDiscardedBook(BookBean book) throws DAOException {
		if (con == null)
			con = dao.getConnection();
		PreparedStatement st = null;

		java.util.Date today = new java.util.Date();
		try {
			String sql = "INSERT INTO discarded_book(id, isbn, title, arrival_date, discard_date, note) VALUES(?,?,?,?,?,?)";
			st = con.prepareStatement(sql);
			st.setInt(1, book.getId());
			st.setString(2, book.getIsbn());
			st.setString(3, book.getTitle());
			st.setDate(4, new java.sql.Date(book.getArrivalDate().getTime()));
			st.setDate(5, new java.sql.Date(today.getTime()));
			st.setString(6, book.getNote());

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
