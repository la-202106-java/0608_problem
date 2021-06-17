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

	public ItemDAO2() throws DAOE {
		getConnection();
	}

	public List<ItemBean> findAll() throws DAOE {
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
			throw new DAOE("失敗");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOE("失敗");
			}
		}

	}

	public List<ItemBean> sortPrice(boolean isAscending) throws DAOE {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql;
			if (isAscending)
				sql = "SELECT *FROM item ORDER BY price";
			else
				sql = "SELECT *FROM item ORDER BY price desc";
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
			throw new DAOE("レコード操作失敗");

		} finally

		{
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();

				close();
			} catch (Exception e) {
				throw new DAOE("失敗");
			}
		}
	}

	public int addItem(String name, int price) throws DAOE {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		try {
			String sql = "INSERT INTO item(name,price) VALUES(?,?)";
			st = con.prepareStatement(sql);
			st.setString(1, name);
			st.setInt(2, price);

			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			throw new DAOE("失敗");

		} finally {
			try {
				if (st != null)
					st.close();

				close();
			} catch (Exception e) {
				throw new DAOE("失敗");
			}
		}
	}

	public List<ItemBean> findByPrice(int minP, int maxP) throws DAOE {

		if (con == null)
			getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;

		//SQL文の作成

		try {

			String sql = "SELECT * FROM item WHERE price >= ? AND  price <= ? ";
			st = con.prepareStatement(sql);

			st.setInt(1, minP);
			st.setInt(2, maxP);
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
			throw new DAOE("レコード操作失敗");

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOE("失敗");
			}

		}
	}

	public int deleteByPrimaryKey(int key) throws DAOE {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		try {
			String sql = "DELETE FROM item WHERE code =?";
			st = con.prepareStatement(sql);
			st.setInt(1, key);
			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();

			throw new DAOE("失敗");

		} finally {
			try {
				if (st != null)
					st.close();

				close();
			} catch (Exception e) {
				throw new DAOE("失敗");
			}
		}
	}

	private void getConnection() throws DAOE {
		// TODO 自動生成されたメソッド・スタブ
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql:sample";
			String user = "student";
			String pass = "himitu";

			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			throw new DAOE("失敗");
		}
	}

	private void close() throws SQLException {
		// TODO 自動生成されたメソッド・スタブ

		if (con != null) {
			con.close();
			con = null;
		}
	}

}
