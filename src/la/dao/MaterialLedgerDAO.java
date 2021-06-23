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
					bean.setId(rs.getInt("material_id"));
					bean.setIsbn(rs.getString("isbn"));
					bean.setStockDate(rs.getDate("stock_date"));
					bean.setDisposalDate(rs.getDate("disposal_date"));
					return bean;
				} else {
					return null;
				}

			} catch (SQLException e) {
				throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

}
