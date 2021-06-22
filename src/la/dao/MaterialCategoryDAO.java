package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.MaterialCategory;

public class MaterialCategoryDAO {

	public List<MaterialCategory> findAll() throws DAOException {
		String sql = "SELECT * FROM material_category";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			List<MaterialCategory> list = new ArrayList<MaterialCategory>();
			while (rs.next()) {
				MaterialCategory bean = new MaterialCategory();
				bean.setId(rs.getInt("category_code"));
				bean.setName(rs.getString("category_name"));
				list.add(bean);
			}
			return list;
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}
}
