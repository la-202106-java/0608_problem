package shopping.dao;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

import shopping.bean.CartBean;
import shopping.bean.CustomerBean;
import shopping.bean.ItemBean;

public class OrderDAO {
	private Connection con;
	private String path;

	public OrderDAO() throws DAOException {
		getConnection();
	}

	public OrderDAO(String path) throws DAOException {
		this.path = path;
		getConnection();
	}

	public int saveOrder(CustomerBean customer, CartBean cart)
			throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			// 注文番号の取得 Serial型の暗黙シーケンスから取得
			int orderNumber = 0;
			String sql = "SELECT nextval('ordered_code_seq')";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if (rs.next()) {
				orderNumber = rs.getInt(1);
			}
			rs.close();
			st.close();

			// 注文情報のOrderedテーブルへの追加
			sql = "INSERT INTO ordered VALUES(?, ?, ?, ?)";
			st = con.prepareStatement(sql);
			// プレースホルダーの設定
			st.setInt(1, orderNumber);
			st.setInt(2, customer.getCode());
			Date today = new Date(System.currentTimeMillis());
			st.setDate(3, today);
			st.setInt(4, cart.getTotal());
			// SQLの実行
			st.executeUpdate();
			st.close();

			// 注文明細情報のOrderedDetailテーブルへの追加
			// 商品ごとに複数レコード追加
			sql = "INSERT INTO ordered_detail VALUES(?, ?, ?)";
			st = con.prepareStatement(sql);
			Map<Integer, ItemBean> items = cart.getItems();
			Collection<ItemBean> list = items.values();
			for (ItemBean item : list) {
				st.setInt(1, orderNumber);
				st.setInt(2, item.getCode());
				st.setInt(3, item.getQuantity());
				st.executeUpdate();
			}
			st.close();
			return orderNumber;
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

	private void getConnection() throws DAOException {
		try {
			Properties properties = new Properties();
			try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(path));) {
				properties.load(in);
				Class.forName(properties.getProperty("driver"));
				String url = properties.getProperty("url");
				String user = properties.getProperty("user");
				String pass = properties.getProperty("pass");
				// データベースへの接続
				con = DriverManager.getConnection(url, user, pass);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("設定ファイル読み込みに失敗しました。");
			}

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
