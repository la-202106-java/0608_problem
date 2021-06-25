
package black.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import black.bean.ListedItemBean;

public class ListedItemDAO {
	private Connection con;

	public ListedItemDAO() throws DAOException {
		getConnection();
		//setSeq();
	}

	private void setSeq() throws DAOException {
		//idのシーケンスがなぜか常に1から始まってしまうので、
		//シーケンスの開始の値を現在のレコード数+1に設定
		//addの際のエラー回避

		String sql = "SELECT id FROM listed_item ORDER BY id DESC LIMIT 1";
		String sql2 = "SELECT SETVAL('listed_item_id_seq', ? , false)";

		try (PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {
			if (rs.next()) {
				//レコード数取得
				int i = rs.getInt(1);

				try (PreparedStatement st2 = con.prepareStatement(sql2)) {
					st2.setInt(1, i + 1);
					st2.executeQuery();
				} catch (Exception e) {
					e.printStackTrace();
					throw new DAOException("レコードの取得に失敗しました。");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	//検索
	//金額が空欄の場合は、サーブレット側で下限ゼロ/上限intのmax値を入れる
	public List<ListedItemBean> findItem(String isbn, String title, int departmentCode,
			String author, int priceMin, int priceMax, String condition,
			boolean onlyInStock, int sellerId) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "SELECT * FROM listed_item WHERE isbn LIKE ? AND title LIKE ?"
				+ " AND author LIKE ? AND price>=? AND price<=?";

		//conditionが空でない場合のみ追加
		String sql2 = "";
		if (condition.length() != 0) {
			sql2 = " AND condition=?";
		}

		//departmentCodeが0～10の場合のみ検索条件に追加
		String sql3 = "";
		if (0 <= departmentCode && departmentCode <= 10) {
			sql3 = " AND department_code=?";
		}

		//sellerIdが0以上の場合のみ条件に追加
		String sql4 = "";
		if (0 <= sellerId) {
			sql4 = " AND seller_id=?";
		}

		//新しい順
		String sqlOrder = " ORDER BY id DESC";

		ResultSet rs = null;
		try (PreparedStatement st = con.prepareStatement(sql + sql2 + sql3 + sql4 + sqlOrder)) {
			st.setString(1, "%" + isbn + "%");
			st.setString(2, "%" + title + "%");
			st.setString(3, "%" + author + "%");
			st.setInt(4, priceMin);
			st.setInt(5, priceMax);
			int i = 6;
			if (condition.length() != 0) {
				st.setString(i, condition);
				i++;
			}
			if (0 <= departmentCode && departmentCode <= 10) {
				st.setInt(i, departmentCode);
				i++;
			}
			if (0 <= sellerId) {
				st.setInt(i, sellerId);
				i++;
			}

			// SQLの実行
			rs = st.executeQuery();

			//検索結果をひとつずつ取り出してリストに入れる
			List<ListedItemBean> list = new ArrayList<ListedItemBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String _isbn = rs.getString("isbn");
				String _title = rs.getString("title");
				int _departmentCode = rs.getInt("department_code");
				String _author = rs.getString("author");
				int price = rs.getInt("price");
				String _condition = rs.getString("condition");
				int seller_id = rs.getInt("seller_id");
				Date orderd_date = rs.getDate("orderd_date");
				int buyer_id = rs.getInt("buyer_id");

				ListedItemBean bean = new ListedItemBean(id, _isbn, _title, _departmentCode,
						_author, price, _condition, seller_id, orderd_date, buyer_id);
				bean.setInStock();
				//bean.setOrderdDate(rs.getDate("orderd_date"));

				//String buyerIdStr = rs.getString("buyer_id");
				//if (buyerIdStr != null && buyerIdStr.length() != 0) {
				//	bean.setBuyerId(Integer.parseInt(buyerIdStr));
				//}

				if (onlyInStock) {
					//在庫ありのみ指定の場合
					if (bean.isInStock()) {
						//beanのメソッドで在庫をチェック
						list.add(bean);
					}
				} else {
					list.add(bean);
				}
			}

			//教科書情報一覧リストを返す
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if (rs != null)
					rs.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public ListedItemBean findItem(int id) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "SELECT * FROM listed_item WHERE id = ?";

		ResultSet rs = null;
		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, id);
			// SQLの実行
			rs = st.executeQuery();

			if (rs.next()) {
				String _isbn = rs.getString("isbn");
				String _title = rs.getString("title");
				int _departmentCode = rs.getInt("department_code");
				String _author = rs.getString("author");
				int price = rs.getInt("price");
				String _condition = rs.getString("condition");
				int seller_id = rs.getInt("seller_id");
				Date orderd_date = rs.getDate("orderd_date");
				int buyer_id = rs.getInt("buyer_id");

				ListedItemBean bean = new ListedItemBean(id, _isbn, _title, _departmentCode,
						_author, price, _condition, seller_id, orderd_date, buyer_id);
				bean.setInStock();
				return bean;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if (rs != null)
					rs.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	//新規n件取得
	public List<ListedItemBean> findNewItems(int num) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "SELECT * FROM listed_item ORDER BY id DESC LIMIT ?";

		ResultSet rs = null;
		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, num);
			// SQLの実行
			rs = st.executeQuery();

			//検索結果をひとつずつ取り出してリストに入れる
			List<ListedItemBean> list = new ArrayList<ListedItemBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String _isbn = rs.getString("isbn");
				String _title = rs.getString("title");
				int _departmentCode = rs.getInt("department_code");
				String _author = rs.getString("author");
				int price = rs.getInt("price");
				String _condition = rs.getString("condition");
				int seller_id = rs.getInt("seller_id");
				Date orderd_date = rs.getDate("orderd_date");
				int buyer_id = rs.getInt("buyer_id");

				ListedItemBean bean = new ListedItemBean(id, _isbn, _title, _departmentCode,
						_author, price, _condition, seller_id, orderd_date, buyer_id);
				bean.setInStock();
				list.add(bean);

			}
			//教科書情報一覧リストを返す
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if (rs != null)
					rs.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	//教科書登録
	//登録したidを返す
	public int addItem(String isbn, String title, int departmentCode,
			String author, int price, String condition, int sellerId) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "INSERT INTO listed_item(isbn, title, department_code, author,"
				+ " price, condition, seller_id) VALUES(?, ?, ?, ?, ?, ?, ?)";
		String sql2 = "SELECT currval('listed_item_id_seq')";

		ResultSet rs = null;
		try (PreparedStatement st = con.prepareStatement(sql);
				PreparedStatement st2 = con.prepareStatement(sql2)) {
			st.setString(1, isbn);
			st.setString(2, title);
			st.setInt(3, departmentCode);
			st.setString(4, author);
			st.setInt(5, price);
			st.setString(6, condition);
			st.setInt(7, sellerId);

			//SQL実行
			st.executeUpdate();

			rs = st2.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return -1;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if (rs != null)
					rs.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	//教科書更新
	public void updateItem(int id, String isbn, String title, int departmentCode,
			String author, int price, String condition) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "UPDATE listed_item SET isbn=? title=? department_code=?"
				+ " author=? price=? condition=? WHERE id=?";

		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, isbn);
			st.setString(2, title);
			st.setInt(3, departmentCode);
			st.setString(4, author);
			st.setInt(5, price);
			st.setString(6, condition);
			st.setInt(7, id);

			//SQL実行
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	//購入手続き
	public void updateItem(int id, int buyerId) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "UPDATE listed_item SET orderd_date=?, buyer_id=? WHERE id=?";

		//今日の日付取得
		Date today = new Date(System.currentTimeMillis());

		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setDate(1, today);
			st.setInt(2, buyerId);
			st.setInt(3, id);

			//SQL実行
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	//教科書削除
	public void deleteItem(int id) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "DELETE FROM listed_item WHERE id=?";

		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, id);

			//SQL実行
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	private void getConnection() throws DAOException {
		try {
			// JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
			// URL、ユーザ名、パスワードの設定
			String url = "jdbc:postgresql:webtext";
			String user = "webtextuser";
			String pass = "himitu";
			// データベースへの接続
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("接続に失敗しました。");
		}
	}

	private void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}
}
