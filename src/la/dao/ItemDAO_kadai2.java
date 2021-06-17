package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.ItemBean2;

public class ItemDAO_kadai2 {
	private Connection con;

	public ItemDAO_kadai2() throws DAOE {
		getConnection();
	}

	public List<ItemBean2> findAll() throws DAOE {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM item";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			List<ItemBean2> list = new ArrayList<ItemBean2>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int category_code = rs.getInt("category_code");
				ItemBean2 bean = new ItemBean2(code, name, price, category_code);
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
