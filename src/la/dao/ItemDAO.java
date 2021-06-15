package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.ItemBean;

public class ItemDAO {
	private Connection con;

	public ItemDAO() throws DAOException {
		getConnection();
	}

	public List<ItemBean> findAll() throws DAOException {
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
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
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

	public List<ItemBean> sortPrice(boolean isAscending) throws DAOException {
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
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
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

	//*************************************1STEP1**********************************
	public List<ItemBean> findByPrice(String pname, int minPrice, int maxPrice) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		String sql;
		try {
			// SQL文の作成

			if (pname.equals("")) {
				if (minPrice == 0) {
					sql = "SELECT * FROM item WHERE price <= ?";
					st = con.prepareStatement(sql);
					st.setInt(1, maxPrice);
				} else if (maxPrice == 0) {
					sql = "SELECT * FROM item WHERE price >= ?";
					st = con.prepareStatement(sql);
					st.setInt(1, minPrice);
				} else {
					sql = "SELECT * FROM item WHERE price between ? and ?";
					st = con.prepareStatement(sql);
					st.setInt(1, minPrice);
					st.setInt(2, maxPrice);
				}
			}
			/*
						if (!pname.equals("")) {
							if (minPrice == 0 && maxPrice == 0) {
								//		sql = "SELECT * FROM item WHERE name LIKE '%?%' ";
								sql = "SELECT * FROM item WHERE name LIKE  ? ";
								st = con.prepareStatement(sql);
								st.setString(1, "%" + pname + "%");
							}
						}
			*/
			if (!pname.equals("")) {
				if (minPrice == 0 && maxPrice == 0) {
					sql = "SELECT * FROM item WHERE name LIKE ?";
					st = con.prepareStatement(sql);
					st.setString(1, "%" + pname + "%");
				} else if (minPrice == 0) {
					sql = "SELECT * FROM item WHERE name LIKE ? and price <= ?";
					st = con.prepareStatement(sql);
					st.setString(1, "%" + pname + "%");
					st.setInt(2, maxPrice);
				} else if (maxPrice == 0) {
					sql = "SELECT * FROM item WHERE name LIKE ? and price >= ?";
					st = con.prepareStatement(sql);
					st.setString(1, "%" + pname + "%");
					st.setInt(2, minPrice);
				} else {
					sql = "SELECT * FROM item WHERE price between ? and ?";
					st = con.prepareStatement(sql);
					st.setInt(1, minPrice);
					st.setInt(2, maxPrice);
				}
			}

			if (pname.equals("") && minPrice == 0 && maxPrice == 0) {
				sql = "SELECT * FROM item";
				st = con.prepareStatement(sql);
			}
			/*
			if (pname == null) {
			if (minPrice == 0 && maxPrice == 0) {
			sql = "SELECT * FROM item";
			st = con.prepareStatement(sql);
			} else if (minPrice == 0) {
			sql = "SELECT * FROM item WHERE price <= ?";
			st = con.prepareStatement(sql);
			st.setInt(1, maxPrice);
			} else if (maxPrice == 0) {
			sql = "SELECT * FROM item WHERE price >= ?";
			st = con.prepareStatement(sql);
			st.setInt(1, minPrice);
			} else if (minPrice != 0 && maxPrice != 0) {
			sql = "SELECT * FROM item WHERE price between ? and ?";
			st = con.prepareStatement(sql);
			st.setInt(1, minPrice);
			st.setInt(2, maxPrice);
			} else {
			System.exit(0);
			}
			}
			if (pname != null) {
			if (minPrice == 0 && maxPrice == 0) {
			sql = "SELECT * FROM item WHERE name = ?";
			st = con.prepareStatement(sql);
			st.setString(1, pname);
			} else if (minPrice == 0) {
			sql = "SELECT * FROM item WHERE price <= ?";
			st = con.prepareStatement(sql);
			st.setInt(1, maxPrice);
			} else if (maxPrice == 0) {
			sql = "SELECT * FROM item WHERE price >= ?";
			st = con.prepareStatement(sql);
			st.setInt(1, minPrice);
			
			} else if (minPrice != 0 && maxPrice != 0) {
			sql = "SELECT * FROM item WHERE price between ? and ?";
			st = con.prepareStatement(sql);
			st.setInt(1, minPrice);
			st.setInt(2, maxPrice);
			} else {
			System.exit(0);
			}
			}
			*/

			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
				list.add(bean);
			}
			// 商品一覧をListとして返す
			return list;
		} catch (

		Exception e) {
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
	//**************************************************************************************

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

	public int fixPrice(int code, int price) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		try {
			// SQL文の作成
			String sql = "UPDATE item SET price =? WHERE code = ?";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			//プレースホルダー
			st.setInt(1, price);
			st.setInt(2, code);
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
