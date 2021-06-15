package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.ItemBean;

public class ItemDAO2 {
	private Connection con;

	public ItemDAO2() throws DAOException {
		getConnection();
	}

	public List<ItemBean> findAll() throws DAOException {
		if (con == null) {
			getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM item";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();

			List<ItemBean> list = new ArrayList<ItemBean>();

			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
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

	public List<ItemBean> sortPrice(boolean isAscending, String productName, String minPrice, String maxPrice)
			throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM item";

			// if文のネストをどうにかしたい
			// 下記のfindByNamePrice()と処理が重複しているので、切り出して呼び出すように変更する
			if (minPrice != null && minPrice != "") {
				sql += " WHERE price >= " + minPrice;
				if (maxPrice != null && maxPrice != "") {
					sql += " AND price <= " + maxPrice;
					if (productName != null && productName != "") {
						sql += " AND name LIKE '%" + productName + "%'";
					}
				} else {
					if (productName != null && productName != "") {
						sql += " AND name LIKE '%" + productName + "%'";
					}
				}
			} else {
				if (maxPrice != null && maxPrice != "") {
					sql += " WHERE price <= " + maxPrice;
					if (productName != null && productName != "") {
						sql += " AND name LIKE '%" + productName + "%'";
					}
				} else {
					if (productName != null && productName != "") {
						sql += " WHERE name LIKE '%" + productName + "%'";
					}
				}
			}

			if (isAscending) {
				sql += " ORDER BY price";
			} else {
				sql += " ORDER BY price desc";
			}

			st = con.prepareStatement(sql);
			rs = st.executeQuery();

			List<ItemBean> list = new ArrayList<ItemBean>();

			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
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
			String sql = "INSERT INTO item(name, price) VALUES(?, ?)";

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
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}

	}

	public List<ItemBean> findByNamePrice(String productName, String minPrice, String maxPrice) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM item";

			// if文のネストをどうにかしたい
			if (minPrice != null && minPrice != "") {
				sql += " WHERE price >= " + minPrice;
				if (maxPrice != null && maxPrice != "") {
					sql += " AND price <= " + maxPrice;
					if (productName != null && productName != "") {
						sql += " AND name LIKE '%" + productName + "%'";
					}
				} else {
					if (productName != null && productName != "") {
						sql += " AND name LIKE '%" + productName + "%'";
					}
				}
			} else {
				if (maxPrice != null && maxPrice != "") {
					sql += " WHERE price <= " + maxPrice;
					if (productName != null && productName != "") {
						sql += " AND name LIKE '%" + productName + "%'";
					}
				} else {
					if (productName != null && productName != "") {
						sql += " WHERE name LIKE '%" + productName + "%'";
					}
				}
			}

			st = con.prepareStatement(sql);

			rs = st.executeQuery();

			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
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

	public List<ItemBean> findByName(String productName) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM item WHERE name LIKE ?";

			st = con.prepareStatement(sql);
			st.setString(1, "%" + productName + "%");

			rs = st.executeQuery();

			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
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

	public List<ItemBean> findByPrice(int minPrice, int maxPrice) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM item WHERE price >= ? AND price <= ?";

			st = con.prepareStatement(sql);
			st.setInt(1, minPrice);
			st.setInt(2, maxPrice);

			rs = st.executeQuery();

			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
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

	public List<ItemBean> findByPrice(int minPrice) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM item WHERE price >= ?";

			st = con.prepareStatement(sql);
			st.setInt(1, minPrice);

			rs = st.executeQuery();

			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
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
			String sql = "DELETE FROM item WHERE code = ?";

			st = con.prepareStatement(sql);
			st.setInt(1, key);

			int rows = st.executeUpdate();

			return rows;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
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
			Class.forName("org.postgresql.Driver");

			String url = "jdbc:postgresql:sample";
			String user = "student";
			String pass = "himitu";

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
