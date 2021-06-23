package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import la.bean.MaterialLedger;

public class MaterialLedgerDAO {

	//廃棄年月日の更新
	public int disposeById(int id) throws DAOException {
		String sql = "UPDATE material_ledger SET disposal_date = CURRENT_TIMESTAMP WHERE material_id = ?";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, id);
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

	//資料台帳の情報を資料idから取得
	public MaterialLedger findById(int id) throws DAOException {
		String sql = "SELECT * FROM material_ledger WHERE material_id = ?";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, id);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					MaterialLedger bean = new MaterialLedger();
					bean.setId(rs.getString("material_id"));
					bean.setIsbn(rs.getString("isbn"));
					bean.setStockDate(rs.getDate("stock_date"));
					bean.setDisposalDate(rs.getDate("disposal_date"));
					return bean;
				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

}
