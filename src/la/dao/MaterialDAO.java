package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.Material;

public class MaterialDAO {

	public List<Material> findAll() throws DAOException {
		String sql = "SELECT * FROM material_ledger LEFT JOIN material_catalog ON material_ledger.isbn = material_catalog.isbn";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			List<Material> list = new ArrayList<Material>();
			while (rs.next()) {
				Material bean = new Material();
				bean.setId(rs.getString("material_id"));
				bean.setIsbn(rs.getString("isbn"));
				bean.setStockDate(rs.getDate("stock_date"));
				bean.setTitle(rs.getString("title"));
				list.add(bean);
			}
			return list;
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

	public List<Material> findById(int id) throws DAOException {
		String sql = "SELECT * FROM material_ledger LEFT JOIN material_catalog ON material_ledger.isbn = material_catalog.isbn WHERE material_id = ?";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, id);
			try (ResultSet rs = st.executeQuery()) {
				List<Material> list = new ArrayList<Material>();
				while (rs.next()) {
					Material bean = new Material();
					bean.setId(rs.getString("material_id"));
					bean.setIsbn(rs.getString("isbn"));
					bean.setStockDate(rs.getDate("stock_date"));
					bean.setTitle(rs.getString("title"));
					list.add(bean);
				}
				return list;
			}
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

	public List<Material> findByName(String title) throws DAOException {
		String sql = "SELECT * FROM material_ledger LEFT JOIN material_catalog ON material_ledger.isbn = material_catalog.isbn WHERE title LIKE ?";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, "%" + title + "%");
			try (ResultSet rs = st.executeQuery()) {
				List<Material> list = new ArrayList<Material>();
				while (rs.next()) {
					Material bean = new Material();
					bean.setId(rs.getString("material_id"));
					bean.setIsbn(rs.getString("isbn"));
					bean.setStockDate(rs.getDate("stock_date"));
					bean.setTitle(rs.getString("title"));
					list.add(bean);
				}
				return list;
			}
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}
}
