package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.ItemBean;

public class AdminItemDAO {
	private Connection con;

	public AdminItemDAO() throws DAOException {
		getConnection();
	}

	public List<ItemBean> findAll() throws DAOException {
		if (con == null) {
			getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM item";

		try {
			List<ItemBean> list = new ArrayList<ItemBean>();
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				int code = rs.getInt("code");
				int categoryCode = rs.getInt("category_code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, categoryCode, name, price);
				list.add(bean);
			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}
	}

	public List<ItemBean> sortPrice(boolean isAscending) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql;
		if (isAscending) {
			sql = "SELECT * FROM item ORDER BY price";
		} else {
			sql = "SELECT * FROM item ORDER BY price DESC";
		}
		List<ItemBean> list = new ArrayList<ItemBean>();
		try (PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {
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
			throw new DAOException("レコードの操作に失敗しました");
		} finally {
			try {
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}

	}

	public List<ItemBean> sortPrice(boolean isAscending, int min, int max, String namePart) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql1 = "SELECT * FROM item WHERE price >= ? AND price <= ? AND name LIKE ?";
		String sql2 = "";
		if (isAscending) {
			sql2 = " ORDER BY price";
		} else {
			sql2 = " ORDER BY price DESC";
		}
		List<ItemBean> list = new ArrayList<ItemBean>();
		ResultSet rs = null;
		try (PreparedStatement st = con.prepareStatement(sql1 + sql2)) {
			st.setInt(1, min);
			st.setInt(2, max);
			st.setString(3, "%" + namePart + "%");

			rs = st.executeQuery();
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
			throw new DAOException("レコードの操作に失敗しました");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}

	}

	public int addItem(String name, int price) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "INSERT INTO item(name, price) VALUES(?, ?)";
		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, name);
			st.setInt(2, price);

			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました");
		} finally {
			try {
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}
	}

	public List<ItemBean> findByPriceAndName(int minPrice, int maxPrice, String namePart)
			throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "SELECT * FROM item WHERE price >= ? AND price <= ? AND name LIKE ?";
		List<ItemBean> list = new ArrayList<ItemBean>();

		ResultSet rs = null;

		try (PreparedStatement st = con.prepareStatement(sql);) {

			st.setInt(1, minPrice);
			st.setInt(2, maxPrice);
			st.setString(3, "%" + namePart + "%");
			rs = st.executeQuery();

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
			throw new DAOException("レコードの操作に失敗しました");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}
	}

	public int deleteByPrimaryKey(int key) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "DELETE FROM item WHERE code = ?";
		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, key);

			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました");
		} finally {
			try {
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}
	}

	private void getConnection() throws DAOException {
		String url = "jdbc:postgresql:sample";
		String user = "student";
		String pass = "himitu";

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			throw new DAOException("接続に失敗しました");
		}
	}

	private void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}

	}

}
