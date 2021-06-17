package shopping.dao;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import shopping.bean.CategoryBean;
import shopping.bean.ItemBean;

public class ItemDAO {
	private Connection con;
	private String path;

	public ItemDAO() throws DAOException {
		//		getConnection();
	}

	public ItemDAO(String path) throws DAOException {
		this.path = path;
		//		getConnection();
	}

	public List<CategoryBean> findAllCategory() throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// SQL文の作成
			String sql = "SELECT * FROM category ORDER BY code";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			List<CategoryBean> list = new ArrayList<CategoryBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				CategoryBean bean = new CategoryBean(code, name);
				list.add(bean);
			}
			// カテゴリ一覧をListとして返す
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
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

	public List<ItemBean> findByCategory(int categoryCode)
			throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// SQL文の作成
			String sql = "SELECT * FROM item WHERE category_code = ? ORDER BY code";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			// カテゴリの設定
			st.setInt(1, categoryCode);
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
				list.add(bean);
			}
			// 商品一覧をListとして返す
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
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

	public ItemBean findByPrimaryKey(int key) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// SQL文の作成
			String sql = "SELECT * FROM item WHERE code = ?";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			// カテゴリの設定
			st.setInt(1, key);
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			if (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
				return bean;
			} else {
				return null; // 主キーに該当するレコードなし
			}
			// 商品一覧をListとして返す
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
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

	public List<ItemBean> findByName(String keyword)
			throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// SQL文の作成
			String sql = "SELECT * FROM item WHERE name LIKE ? ";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			// カテゴリの設定
			st.setString(1, "%" + keyword + "%");
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
				list.add(bean);
			}
			// 商品一覧をListとして返す
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
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
