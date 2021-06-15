package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.ItemBean6;

public class ItemDAO6 {
	private Connection con;

	public ItemDAO6() throws DAOException {
		getConnection();
	}

	public List<ItemBean6> findAll() throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// SQL文の作成
			String sql = "SELECT * FROM item";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得
			List<ItemBean6> list = new ArrayList<ItemBean6>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int category = rs.getInt("category_code");
				ItemBean6 bean = new ItemBean6(code, name, price, category);
				list.add(bean);
			}
			// 商品一覧をListとして返す
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public List<ItemBean6> sortPrice(boolean isAscending) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// SQL文の作成
			String sql;
			// ソートキーの指定
			if (isAscending)
				sql = "SELECT * FROM item ORDER BY price";
			else
				sql = "SELECT * FROM item ORDER BY price desc";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			List<ItemBean6> list = new ArrayList<ItemBean6>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int category = rs.getInt("category_code");
				ItemBean6 bean = new ItemBean6(code, name, price, category);
				list.add(bean);
			}
			// 商品一覧をListとして返す
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public int addItem(String name, int price) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		try {
			// SQL文の作成
			String sql = "INSERT INTO item(name, price) VALUES(?, ?)";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			// ソートキーの指定
			st.setString(1, name);
			st.setInt(2, price);
			// SQLの実行
			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public List<ItemBean6> findByPrice(int minprice, int maxprice, String name, int category) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// SQL文の作成
			String sql = "SELECT * FROM item WHERE ? <= price AND price <= ? AND name LIKE ?";
			//String sql = "SELECT * FROM item WHERE ? <= price AND price <= ?";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			//値段と商品名のセット
			st.setInt(1, minprice);
			st.setInt(2, maxprice);
			st.setString(3, "%" + name + "%");
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			List<ItemBean6> list = new ArrayList<ItemBean6>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name1 = rs.getString("name");
				int price = rs.getInt("price");
				int category1 = rs.getInt("category_code");
				ItemBean6 bean = new ItemBean6(code, name1, price, category1);
				list.add(bean);
			}
			// 商品一覧をListとして返す
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public int deleteByPrimaryKey(int key) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		try {
			// SQL文の作成
			String sql = "DELETE FROM item WHERE code = ?";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			// 主キーの指定
			st.setInt(1, key);
			// SQLの実行
			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if (st != null)
					st.close();
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
