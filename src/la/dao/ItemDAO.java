package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.CategoryBean;
import la.bean.ItemBean;

public class ItemDAO {
	private Connection con;

	private void getConnection() throws DAOException {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql:sample";
			String user = "student";
			String pass = "himitu";
			con = DriverManager.getConnection(url, user, pass);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}

	public ItemDAO() throws DAOException {
		getConnection();
	}

	public List<ItemBean> findItemByPriceRange(int lower, int upper) throws DAOException {
		if (con == null)
			getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select * from item where price >= ? and  price <= ?   order by price";
			st = con.prepareStatement(sql);
			st.setInt(1, lower);
			st.setInt(2, upper);
			rs = st.executeQuery();
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int category_code = rs.getInt("category_code");
				ItemBean bean = new ItemBean(code, name, price, category_code);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				close();
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public List<ItemBean> findItemByPriceRangeAndName(int lower, int upper, String str) throws DAOException {
		if (con == null)
			getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select * from item where price >= ? and  price <= ? and name like '%" + str
					+ "%'  order by price";
			st = con.prepareStatement(sql);
			st.setInt(1, lower);
			st.setInt(2, upper);
			rs = st.executeQuery();
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int category_code = rs.getInt("category_code");
				ItemBean bean = new ItemBean(code, name, price, category_code);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				close();
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public List<CategoryBean> findAllCategory() throws DAOException {
		if (con == null)
			getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select * from category order by code";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			List<CategoryBean> list = new ArrayList<CategoryBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				CategoryBean bean = new CategoryBean(code, name);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				close();
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public List<ItemBean> findByCategory(int categoryCode) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from item where category_code=? order by code";
			st = con.prepareStatement(sql);
			st.setInt(1, categoryCode);
			rs = st.executeQuery();
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int category_code = rs.getInt("category_code");
				ItemBean bean = new ItemBean(code, name, price, category_code);
				list.add(bean);

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");

		} finally {
			try {
				close();
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public ItemBean findByPrimaryKey(int key) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from item where code=?";
			st = con.prepareStatement(sql);
			st.setInt(1, key);
			rs = st.executeQuery();

			if (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int category_code = rs.getInt("category_code");
				ItemBean bean = new ItemBean(code, name, price, category_code);
				return bean;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");

		} finally {
			try {
				close();
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public int updatePriceByCode(int code, int price) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		try {
			String sql = "UPDATE item set price=?   where code=? ";
			st = con.prepareStatement(sql);
			st.setInt(1, price);
			st.setInt(2, code);
			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {

			try {
				close();
				if (st != null)
					st.close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}

		}

	}

	public int updateBeanByCode(ItemBean bean) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		try {
			String sql = "UPDATE item set category_code=? , name=? , price=?  where code=? ";
			st = con.prepareStatement(sql);
			st.setInt(1, bean.getCategory());
			st.setString(2, bean.getName());
			st.setInt(3, bean.getPrice());
			st.setInt(4, bean.getCode());
			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("ItemBeanの更新は失敗しました。");
		} finally {

			try {
				close();
				if (st != null)
					st.close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}

		}

	}

	public int deleteBeanByCode(int code) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		try {
			String sql = "delete from item where code=?";
			st = con.prepareStatement(sql);
			st.setInt(1, code);
			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("ItemBeanの削除は失敗しました。");
		} finally {

			try {
				close();
				if (st != null)
					st.close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}

		}

	}

	public int addItem(ItemBean bean) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		try {
			String sql = "Insert into item(name,price,category_code) values(?,?,?)";
			st = con.prepareStatement(sql);
			st.setString(1, bean.getName());
			st.setInt(2, bean.getPrice());
			st.setInt(3, bean.getCategory());
			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("商品の追加に失敗しました。");

		} finally {
			try {
				close();
				if (st != null)
					st.close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}

	}

	public List<ItemBean> findAll() throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * from item";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int category_code = rs.getInt("category_code");
				ItemBean bean = new ItemBean(code, name, price, category_code);
				list.add(bean);

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");

		} finally {
			try {
				close();
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}

	}
}
