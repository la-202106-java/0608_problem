package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import la.bean.MaterialCatalog;

public class MaterialCatalogDAO {

	public MaterialCatalog findByISBNkey(String isbn) throws DAOException {
		String sql = "SELECT * FROM material_catalog where isbn = ?";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			// カテゴリの設定
			st.setString(1, isbn);
			try (ResultSet rs = st.executeQuery()) {
				// 結果の取得および表示
				if (rs.next()) {
					String isbn2 = rs.getString("isbn");
					String title = rs.getString("title");
					int categoryCode = rs.getInt("category_code");
					String authur = rs.getString("authur");
					String publisher = rs.getString("publisher");
					Date publicationDate = rs.getDate("publication_date");
					MaterialCatalog bean = new MaterialCatalog(isbn2, title, categoryCode,
							authur, publisher, publicationDate);
					return bean;
				} else {
					return null; // 主キーに該当するレコードなし
				}
			}
			// 商品一覧をListとして返す
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public MaterialCatalog findByName(String name) throws DAOException {
		String sql = "SELECT * FROM material_catalog where title = ?";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			// カテゴリの設定
			st.setString(1, name);
			try (ResultSet rs = st.executeQuery()) {
				// 結果の取得および表示
				if (rs.next()) {
					String isbn2 = rs.getString("isbn");
					String title = rs.getString("title");
					int categoryCode = rs.getInt("category_code");
					String authur = rs.getString("authur");
					String publisher = rs.getString("publisher");
					Date publicationDate = rs.getDate("publication_date");
					MaterialCatalog bean = new MaterialCatalog(isbn2, title, categoryCode,
							authur, publisher, publicationDate);
					return bean;
				} else {
					return null; // 主キーに該当するレコードなし
				}
			}
			// 商品一覧をListとして返す
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}
	}

	public void add(String title, String author, String publisher, java.sql.Date publisher_date, int categoryCode,
			String ISBN) {
		String sql = " INSERT INTO material_catalog(isbn,title,category_code,authur,publisher,publication_date) VALUES(?,?,?,?,?,?)";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//			Date date = sdf.parse(publisher_date);

			st.setString(1, ISBN);
			st.setString(2, title);
			st.setInt(3, categoryCode);
			st.setString(4, author);
			st.setString(5, publisher);
			st.setDate(6, publisher_date);
			int rows = st.executeUpdate();
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

}
