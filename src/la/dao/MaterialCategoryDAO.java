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

	public MaterialCategory findName(int categoryCode) throws DAOException {
		String sql = "SELECT * FROM material_category where category_code = ?";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			// カテゴリの設定
			st.setInt(1, categoryCode);
			try (ResultSet rs = st.executeQuery()) {
				// 結果の取得および表示
				if (rs.next()) {
					int categoryCode2 = rs.getInt("category_code");
					String category_name = rs.getString("category_name");
					MaterialCategory bean = new MaterialCategory(categoryCode2, category_name);
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
}
