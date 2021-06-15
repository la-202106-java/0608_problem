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
	String preSql = "";

	public ItemDAO() throws DAOException {
		getConnection();
	}

	public List<ItemBean> findAll() throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//SQL文の作成
			String sql = "select * from item";
			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			//SQLの実行
			rs = st.executeQuery();
			//結果の取得
			preSql = sql;
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				int category = rs.getInt("category_code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
				list.add(bean);
			}
			//商品一覧をListとして返す
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				//リソースの開放
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

	public List<ItemBean> findAll2() throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//SQL文の作成
			String sql = "select * from item";
			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			//SQLの実行
			rs = st.executeQuery();
			//結果の取得
			preSql = sql;
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				int category = rs.getInt("category_code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, category, name, price);
				list.add(bean);
			}
			//商品一覧をListとして返す
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				//リソースの開放
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

	public List<ItemBean> sortPrice(boolean asc, String name, String getMinPrice, String getMaxPrice)
			throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			String sql;
			//ソートキーの指定
			if (name != "" && getMinPrice == "" && getMaxPrice == "") {
				if (asc) {
					sql = "select * from item where name like ? order by price";
				} else {
					sql = "select * from item where name like ? order by price desc";
				}
				st = con.prepareStatement(sql);
				st.setString(1, "%" + name + "%");
				rs = st.executeQuery();
			} else if (name != "" && getMinPrice != "" && getMaxPrice == "") {
				if (asc) {
					sql = "select * from item where name like ? and price >= ? order by price";
				} else {
					sql = "select * from item where name like ? and price >= ? order by price desc";
				}
				int minPrice = Integer.parseInt(getMinPrice);
				st = con.prepareStatement(sql);
				st.setString(1, "%" + name + "%");
				st.setInt(2, minPrice);
				rs = st.executeQuery();
			} else if (name != "" && getMinPrice == "" && getMaxPrice != "") {
				if (asc) {
					sql = "select * from item where name like ? and price <= ? order by price";
				} else {
					sql = "select * from item where name like ? and price <= ? order by price desc";
				}
				int maxPrice = Integer.parseInt(getMaxPrice);
				st = con.prepareStatement(sql);
				st.setString(1, "%" + name + "%");
				st.setInt(2, maxPrice);
				rs = st.executeQuery();
			} else if (name != "" && getMinPrice != "" && getMaxPrice != "") {
				if (asc) {
					sql = "select * from item where name like ? and price >= ? and price <= ? order by price";
				} else {
					sql = "select * from item where name like ? and price >= ? and price <= ? order by price desc";
				}
				int minPrice = Integer.parseInt(getMinPrice);
				int maxPrice = Integer.parseInt(getMaxPrice);
				st = con.prepareStatement(sql);
				st.setString(1, "%" + name + "%");
				st.setInt(2, minPrice);
				st.setInt(3, maxPrice);
				rs = st.executeQuery();
			} else if (name == "" && getMinPrice != "" && getMaxPrice == "") {
				if (asc) {
					sql = "select * from item where price >= ? order by price";
				} else {
					sql = "select * from item where price >= ? order by price desc";
				}
				int minPrice = Integer.parseInt(getMinPrice);
				st = con.prepareStatement(sql);
				st.setInt(1, minPrice);
				rs = st.executeQuery();
			} else if (name == "" && getMinPrice != "" && getMaxPrice != "") {
				if (asc) {
					sql = "select * from item where price >= ? and price <= ? order by price";
				} else {
					sql = "select * from item where price >= ? and price <= ? order by price desc";
				}
				int minPrice = Integer.parseInt(getMinPrice);
				int maxPrice = Integer.parseInt(getMaxPrice);
				st = con.prepareStatement(sql);
				st.setInt(1, minPrice);
				st.setInt(2, maxPrice);
				rs = st.executeQuery();
			} else if (name == "" && getMinPrice == "" && getMaxPrice != "") {
				if (asc) {
					sql = "select * from item where price <= ? order by price";
				} else {
					sql = "select * from item where price <= ? order by price desc";
				}
				int maxPrice = Integer.parseInt(getMaxPrice);
				st = con.prepareStatement(sql);
				st.setInt(1, maxPrice);
				rs = st.executeQuery();
			} else if (name == "" && getMinPrice == "" && getMaxPrice == "") {
				if (asc) {
					sql = "select * from item order by price";
				} else {
					sql = "select * from item order by price desc";
				}
				int maxPrice = Integer.parseInt(getMaxPrice);
				st = con.prepareStatement(sql);
				st.setInt(1, maxPrice);
				rs = st.executeQuery();
			}

			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String named = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, named, price);
				list.add(bean);
			}
			//商品一覧をListとして返す
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				//リソースの開放
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
			String sql = "insert into item(name,price) values(?,?)";
			st = con.prepareStatement(sql);
			st.setString(1, name);
			st.setInt(2, price);
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

	//	public List<ItemBean> findByPrice(int minPrice, int maxPrice) throws DAOException {
	//		if (con == null)
	//			getConnection();
	//
	//		PreparedStatement st = null;
	//		ResultSet rs = null;
	//		try {
	//			String sql = "select * from item where price >= ? and price <= ?";
	//			st = con.prepareStatement(sql);
	//
	//			st.setInt(1, minPrice);
	//			st.setInt(2, maxPrice);
	//
	//			rs = st.executeQuery();
	//
	//			List<ItemBean> list = new ArrayList<ItemBean>();
	//			while (rs.next()) {
	//				int code = rs.getInt("code");
	//				String name = rs.getString("name");
	//				int price = rs.getInt("price");
	//				ItemBean bean = new ItemBean(code, name, price);
	//				list.add(bean);
	//			}
	//			//商品一覧をListとして返す
	//			return list;
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			throw new DAOException("レコードの操作に失敗しました。");
	//		} finally {
	//			try {
	//				// リソースの開放
	//				if (rs != null)
	//					rs.close();
	//				if (st != null)
	//					st.close();
	//				close();
	//			} catch (Exception e) {
	//				throw new DAOException("リソースの開放に失敗しました。");
	//			}
	//		}
	//	}
	//
	//	public List<ItemBean> findByMinPrice(int minPrice) throws DAOException {
	//		if (con == null)
	//			getConnection();
	//
	//		PreparedStatement st = null;
	//		ResultSet rs = null;
	//		try {
	//			String sql = "select * from item where price >= ?";
	//			st = con.prepareStatement(sql);
	//
	//			st.setInt(1, minPrice);
	//			rs = st.executeQuery();
	//
	//			List<ItemBean> list = new ArrayList<ItemBean>();
	//			while (rs.next()) {
	//				int code = rs.getInt("code");
	//				String name = rs.getString("name");
	//				int price = rs.getInt("price");
	//				ItemBean bean = new ItemBean(code, name, price);
	//				list.add(bean);
	//			}
	//			//商品一覧をListとして返す
	//			return list;
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			throw new DAOException("レコードの操作に失敗しました。");
	//		} finally {
	//			try {
	//				// リソースの開放
	//				if (rs != null)
	//					rs.close();
	//				if (st != null)
	//					st.close();
	//				close();
	//			} catch (Exception e) {
	//				throw new DAOException("リソースの開放に失敗しました。");
	//			}
	//		}
	//	}
	//
	//	public List<ItemBean> findByMaxPrice(int maxPrice) throws DAOException {
	//		if (con == null)
	//			getConnection();
	//
	//		PreparedStatement st = null;
	//		ResultSet rs = null;
	//		try {
	//			String sql = "select * from item where price <= ?";
	//			st = con.prepareStatement(sql);
	//
	//			st.setInt(1, maxPrice);
	//			rs = st.executeQuery();
	//
	//			List<ItemBean> list = new ArrayList<ItemBean>();
	//			while (rs.next()) {
	//				int code = rs.getInt("code");
	//				String name = rs.getString("name");
	//				int price = rs.getInt("price");
	//				ItemBean bean = new ItemBean(code, name, price);
	//				list.add(bean);
	//			}
	//			//商品一覧をListとして返す
	//			return list;
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			throw new DAOException("レコードの操作に失敗しました。");
	//		} finally {
	//			try {
	//				// リソースの開放
	//				if (rs != null)
	//					rs.close();
	//				if (st != null)
	//					st.close();
	//				close();
	//			} catch (Exception e) {
	//				throw new DAOException("リソースの開放に失敗しました。");
	//			}
	//		}
	//	}

	public List<ItemBean> findByNameAndPrice(String name, String getMinPrice, String getMaxPrice) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			if (name != "" && getMinPrice == "" && getMaxPrice == "") {
				String sql = "select * from item where name like ?";
				st = con.prepareStatement(sql);
				st.setString(1, "%" + name + "%");
				preSql = sql;
				rs = st.executeQuery();
			} else if (name != "" && getMinPrice != "" && getMaxPrice == "") {
				String sql = "select * from item where name like ? and price >= ?";
				int minPrice = Integer.parseInt(getMinPrice);
				st = con.prepareStatement(sql);
				st.setString(1, "%" + name + "%");
				st.setInt(2, minPrice);
				preSql = sql;
				rs = st.executeQuery();
			} else if (name != "" && getMinPrice == "" && getMaxPrice != "") {
				String sql = "select * from item where name like ? and price <= ?";
				int maxPrice = Integer.parseInt(getMaxPrice);
				st = con.prepareStatement(sql);
				st.setString(1, "%" + name + "%");
				st.setInt(2, maxPrice);
				preSql = sql;
				rs = st.executeQuery();
			} else if (name != "" && getMinPrice != "" && getMaxPrice != "") {
				String sql = "select * from item where name like ? and price >= ? and price <= ?";
				int minPrice = Integer.parseInt(getMinPrice);
				int maxPrice = Integer.parseInt(getMaxPrice);
				st = con.prepareStatement(sql);
				st.setString(1, "%" + name + "%");
				st.setInt(2, minPrice);
				st.setInt(3, maxPrice);
				preSql = sql;
				rs = st.executeQuery();
			} else if (name == "" && getMinPrice != "" && getMaxPrice == "") {
				int minPrice = Integer.parseInt(getMinPrice);
				String sql = "select * from item where price >= ?";
				st = con.prepareStatement(sql);
				st.setInt(1, minPrice);
				preSql = sql;
				rs = st.executeQuery();
			} else if (name == "" && getMinPrice != "" && getMaxPrice != "") {
				String sql = "select * from item where price >= ? and price <= ?";
				int minPrice = Integer.parseInt(getMinPrice);
				int maxPrice = Integer.parseInt(getMaxPrice);
				st = con.prepareStatement(sql);
				st.setInt(1, minPrice);
				st.setInt(2, maxPrice);
				preSql = sql;
				rs = st.executeQuery();
			} else if (name == "" && getMinPrice == "" && getMaxPrice != "") {
				String sql = "select * from item where price <= ?";
				int maxPrice = Integer.parseInt(getMaxPrice);
				st = con.prepareStatement(sql);
				st.setInt(1, maxPrice);
				preSql = sql;
				rs = st.executeQuery();
			}

			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String named = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, named, price);
				list.add(bean);
			}
			//商品一覧をListとして返す
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
			String sql = "delete from item where code = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, key);
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
