package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import la.bean.MaterialLedger;

public class MaterialLedgerDAO {

	public MaterialLedger findByID(int material_id) throws DAOException {
		//		String sql = "SELECT * FROM material_ledger where material_id = ?";
		String sql = "SELECT * FROM material_ledger order by material_id desc limit 1";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			// カテゴリの設定
			//			st.setInt(1, material_id);
			try (ResultSet rs = st.executeQuery()) {
				// 結果の取得および表示
				if (rs.next()) {
					int id = rs.getInt("material_id");
					String isbn = rs.getString("isbn");
					Date stockDate = rs.getDate("stock_date");
					Date disposalDate = rs.getDate("disposal_date");
					String remark = rs.getString("remark");
					//					String isbn2 = rs.getString("isbn");
					//					String title = rs.getString("title");
					//					int categoryCode = rs.getInt("category_code");
					//					String authur = rs.getString("authur");
					//					String publisher = rs.getString("publisher");
					//					Date publicationDate = rs.getDate("publication_date");
					MaterialLedger bean = new MaterialLedger(id, isbn, stockDate, disposalDate, remark);
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

	public MaterialLedger findBynewID() throws DAOException {
		//		String sql = "SELECT * FROM material_ledger where material_id = ?";
		String sql = "SELECT * FROM material_ledger order by material_id desc limit 1";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			// カテゴリの設定
			//			st.setInt(1, material_id);
			try (ResultSet rs = st.executeQuery()) {
				// 結果の取得および表示
				if (rs.next()) {
					int id = rs.getInt("material_id");
					String isbn = rs.getString("isbn");
					Date stockDate = rs.getDate("stock_date");
					Date disposalDate = rs.getDate("disposal_date");
					String remark = rs.getString("remark");
					//					String isbn2 = rs.getString("isbn");
					//					String title = rs.getString("title");
					//					int categoryCode = rs.getInt("category_code");
					//					String authur = rs.getString("authur");
					//					String publisher = rs.getString("publisher");
					//					Date publicationDate = rs.getDate("publication_date");
					MaterialLedger bean = new MaterialLedger(id, isbn, stockDate, disposalDate, remark);
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

	public void add(String isbn, Timestamp d4) {
		// TODO 自動生成されたメソッド・スタブ
		String sql = " INSERT INTO material_ledger(isbn,stock_date) VALUES(?,?)";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, isbn);
			st.setTimestamp(2, d4);
			int rows = st.executeUpdate();
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

}
