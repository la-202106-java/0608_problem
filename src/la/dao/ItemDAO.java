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
			throw new DAOException("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}

	}

	public List<ItemBean> findAll2() throws DAOException {

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
				int category_code = rs.getInt("category_code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, category_code, name, price);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}

	}

	public List<ItemBean> sortPrice(boolean isAscending) throws DAOException {
		if (con == null)
			getConnection();

		String sql;
		if (isAscending)
			sql = "SELECT * FROM item ORDER BY price";
		else
			sql = "SELECT * FROM item ORDER BY price desc";

		try (PreparedStatement st = con.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
			List<ItemBean> list = new ArrayList<ItemBean>();

			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
				list.add(bean);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました");
		}

	}

	public int addItem(String name, int price) throws DAOException {
		if (con == null)
			getConnection();
		//		PreparedStatement st = null;

		String sql = "INSERT INTO item(name, price) VALUES(?, ?)";

		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, name);
			st.setInt(2, price);

			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました");
		}

	}

	public int addItem2(int categoryCode, String name, int price) throws DAOException {
		if (con == null)
			getConnection();

		String sql = "INSERT INTO item(category_code, name, price) VALUES(?, ?, ?)";

		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, categoryCode);
			st.setString(2, name);
			st.setInt(3, price);

			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました");
		}

	}

	public List<ItemBean> findByPrice(int minPrice, int maxPrice) throws DAOException {

		if (con == null)
			getConnection();

		if (maxPrice == 0)
			maxPrice = 2147483647;

		String sql = "SELECT * FROM item WHERE price >= ? AND price <= ?";

		try (PreparedStatement st = con.prepareStatement(sql)) {
			List<ItemBean> list = new ArrayList<ItemBean>();

			st.setInt(1, minPrice);
			st.setInt(2, maxPrice);
			try (ResultSet rs = st.executeQuery()) {

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

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました");
		}

	}

	public List<ItemBean> findBy3Inputs(String itemName, int minPrice, int maxPrice) throws DAOException {
		if (con == null)
			getConnection();

		if (maxPrice == 0)
			maxPrice = 2147483647;
		String sql = "SELECT * FROM item WHERE name LIKE ? AND price >= ? AND price <= ?";

		try (PreparedStatement st = con.prepareStatement(sql)) {
			List<ItemBean> list = new ArrayList<ItemBean>();

			st.setString(1, "%" + itemName + "%");
			st.setInt(2, minPrice);
			st.setInt(3, maxPrice);
			try (ResultSet rs = st.executeQuery()) {

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

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました");
		}

	}

	public List<ItemBean> findByInputs(String itemName, int minPrice, int maxPrice) throws DAOException {
		List<ItemBean> list = new ArrayList<ItemBean>();
		if (itemName.isEmpty()) {
			if (minPrice == 0 && maxPrice == 0)
				list = findAll();
			else
				list = findByPrice(minPrice, maxPrice);
		} else
			list = findBy3Inputs(itemName, minPrice, maxPrice);

		return list;
	}

	public int deleteByPrimartKey(int key) throws DAOException {

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
			throw new DAOException("レコードの操作に失敗しました");
		} finally {
			try {
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}

	}

	public int updateByInputs(int key, int categoryCode, String name, int price) throws DAOException {

		if (con == null)
			getConnection();
		PreparedStatement st = null;

		try {
			String sql = "UPDATE item SET category_code=?, name=?, price=? WHERE code = ?";
			st = con.prepareStatement(sql);
			st.setInt(1, categoryCode);
			st.setString(2, name);
			st.setInt(3, price);
			st.setInt(4, key);

			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました");
		} finally {
			try {
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}

	}

	// private
	private void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}

	private void getConnection() throws DAOException {

		try {
			Class.forName("org.postgresql.Driver");

			String url = "jdbc:postgresql:sample";
			String user = "student";
			String pass = "himitu";
			// データベースへの接続
			con = DriverManager.getConnection(url, user, pass);

		} catch (Exception e) {
			throw new DAOException("接続に失敗しました");
		}
	}

}
