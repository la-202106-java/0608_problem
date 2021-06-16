package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.AdminItemBean;

public class AdminItemDAO {
	private Connection con;

	public AdminItemDAO() throws DAOException {
		getConnection();
	}

	//show all items
	public List<AdminItemBean> findAll() throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "select * from item order by code";
			st = con.prepareStatement(sql);

			rs = st.executeQuery();

			List<AdminItemBean> list = new ArrayList<AdminItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				int category_code = rs.getInt("category_code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				AdminItemBean bean = new AdminItemBean(code, category_code, name, price);
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

	//add
	public int addItem(int category_code, String name, int price) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		try {
			String sql = "insert into item(category_code, name, price) values(?, ?, ?)";
			st = con.prepareStatement(sql);
			st.setInt(1, category_code);
			st.setString(2, name);
			st.setInt(3, price);

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

	//update
	public int updateItem(int code, int category_code, String name, int price) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		try {
			String sql = "update item set category_code=?, name=?, price=? where code=?";
			st = con.prepareStatement(sql);
			st.setInt(1, category_code);
			st.setString(2, name);
			st.setInt(3, price);
			st.setInt(4, code);

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

	//delete
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
