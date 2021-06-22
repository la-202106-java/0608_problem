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
	}

	//検索
	//金額が空欄の場合は、サーブレット側で下限ゼロ/上限intのmax値を入れる
	public List<ListedItemBean> findItem(String isbn, String title, int departmentCode,
			String author, int priceMin, int priceMax, String condition, boolean inStock, int sellerId)
			throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "SELECT * FROM listed_item WHERE isbn LIKE ? AND title LIKE ?"
				+ " AND author=? AND price>=? AND price<=? AND conditon=?";

		//departmentCodeが0～10の場合のみ検索条件に追加
		String sql2 = "";
		if (0 <= departmentCode && departmentCode <= 10) {
			sql2 = " AND department_code=?";
		}

		//在庫ありのみ
		String sql3 = "";
		if (inStock) {
			sql3 = " AND orderd_date=NULL AND byer_id=NULL";
		}

		//出品者ID指定（自分が出品したもののみ）
		//空欄の場合はサーブレットでマイナスの値を入れる
		String sql4 = "";
		if (sellerId > 0) {
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
			st.setString(6, condition);
			if (0 <= departmentCode && departmentCode <= 10) {
				st.setInt(7, priceMax);
			}
			if (sellerId > 0) {
				st.setInt(8, sellerId);
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

				ListedItemBean bean = new ListedItemBean(id, _isbn, _title, _departmentCode,
						_author, price, _condition, seller_id);
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
	public void addItem(String isbn, String title, int departmentCode,
			String author, int price, String condition) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "INSERT INTO listed_item(isbn, title, department_code, author,"
				+ " price, condition) VALUES(?, ?, ?, ?, ?, ?)";

		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, isbn);
			st.setString(2, title);
			st.setInt(3, departmentCode);
			st.setString(4, author);
			st.setInt(5, price);
			st.setString(6, condition);

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
	public void updateItem(int id, int byerId) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "UPDATE listed_item SET orderd_date=? byer_id=? WHERE id=?";

		//今日の日付取得
		Date today = new Date(System.currentTimeMillis());

		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setDate(1, today);
			st.setInt(2, byerId);
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
			String url = "jdbc:postgresql:sample";
			String user = "student";
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
