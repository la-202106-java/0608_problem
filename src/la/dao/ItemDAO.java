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

	//ソート追加箇所
	public List<ItemBean> sortPrice(boolean isAscending) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql;
			if (isAscending)
				sql = "SELECT * FROM item ORDER BY price";
			else
				sql = "SELECT * FROM item ORDER BY price desc";
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
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}

	//add追加箇所
	public int addItem(String name, int price) throws DAOException {
		if (con == null) {
			getConnection();
		}
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
				if (st != null) {
					st.close();
				}
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}

	//find追加箇所
	public List<ItemBean> findByPrice(int lePrice, int hePrice) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql;
			if (lePrice != -1 && hePrice != -1) {
				sql = "SELECT * FROM item WHERE price BETWEEN ? AND ?";
				st = con.prepareStatement(sql);
				st.setInt(1, lePrice);
				st.setInt(2, hePrice);
			} else if (hePrice == -1) {
				sql = "SELECT * FROM item WHERE price >= ?";
				st = con.prepareStatement(sql);
				st.setInt(1, lePrice);
			} else {
				sql = "SELECT * FROM item WHERE price <= ?";
				st = con.prepareStatement(sql);
				st.setInt(1, hePrice);
			}

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
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}

	//delete追加箇所
	public int deleteByPrimaryKey(int key) throws DAOException {
		if (con == null) {
			getConnection();
		}
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
				throw new DAOException("リソースの解放に失敗しました。");
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
