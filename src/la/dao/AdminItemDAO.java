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

		String sql = "SELECT * FROM item ORDER BY code";

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

	public int addItem(int categoryCode, String name, int price) throws DAOException {
		if (con == null) {
			getConnection();
		}

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
		} finally {
			try {
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました");
			}
		}
	}

	public int updateItem(int code, int categoryCode, String name, int price) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String categoryCodeSql = "";
		String nameSql = "";
		String priceSql = "";

		String comma = "";
		if (categoryCode > 0) {
			categoryCodeSql = "category_code=? ";
			comma = ", ";
		}
		if (name != null && name.length() != 0) {
			nameSql = comma + "name=? ";
			comma = ", ";
		}
		if (price > 0) {
			priceSql = comma + "price=? ";
		}

		String sql = "UPDATE item SET "
				+ categoryCodeSql + nameSql + priceSql + "WHERE code=?";

		try (PreparedStatement st = con.prepareStatement(sql)) {
			int i = 1;
			if (categoryCodeSql.length() != 0) {
				st.setInt(i, categoryCode);
				i++;
			}
			if (nameSql.length() != 0) {
				st.setString(i, name);
				i++;
			}
			if (priceSql.length() != 0) {
				st.setInt(i, price);
				i++;
			}
			st.setInt(i, code);

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
