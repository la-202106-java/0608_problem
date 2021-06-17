package la.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import la.bean.CategoryBean;
import la.bean.SampleItemBean;

public class SampleItemDAO {
	private Connection con;
	private String filePath;

	public SampleItemDAO(String filePath) throws DAOException {
		this.filePath = filePath;
		getConnection();
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

	public List<SampleItemBean> findByCategory(int categoryCode)
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
			List<SampleItemBean> list = new ArrayList<SampleItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				SampleItemBean bean = new SampleItemBean(code, name, price);
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

	public List<SampleItemBean> findByCategory(int categoryCode, int limit, int offset)
			throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			// SQL文の作成
			String sql = "SELECT * FROM item WHERE category_code = ? ORDER BY code LIMIT ? OFFSET ?";
			// PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			// カテゴリの設定
			st.setInt(1, categoryCode);
			st.setInt(2, limit);
			st.setInt(3, offset);
			// SQLの実行
			rs = st.executeQuery();
			// 結果の取得および表示
			List<SampleItemBean> list = new ArrayList<SampleItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				SampleItemBean bean = new SampleItemBean(code, name, price);
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

	public SampleItemBean findByPrimaryKey(int key) throws DAOException {
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
				SampleItemBean bean = new SampleItemBean(code, name, price);
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

	public List<SampleItemBean> findByName(String str) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "SELECT * FROM item WHERE name LIKE ?";
		ResultSet rs = null;
		try (PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, "%" + str + "%");
			rs = st.executeQuery();

			List<SampleItemBean> list = new ArrayList<SampleItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				SampleItemBean bean = new SampleItemBean(code, name, price);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public List<SampleItemBean> findByName(String str, int limit, int offset) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "SELECT * FROM item WHERE name LIKE ? LIMIT ? OFFSET ?";
		ResultSet rs = null;
		try (PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, "%" + str + "%");
			st.setInt(2, limit);
			st.setInt(3, offset);
			rs = st.executeQuery();

			List<SampleItemBean> list = new ArrayList<SampleItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				SampleItemBean bean = new SampleItemBean(code, name, price);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public int countByCategory(int code) throws DAOException {
		String sql = "SELECT COUNT(*) FROM item WHERE category_code=?";
		ResultSet rs = null;
		try (PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, code);
			rs = st.executeQuery();
			if (rs.next()) {
				return rs.getInt("count");
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	public int countByName(String str) throws DAOException {
		String sql = "SELECT COUNT(*) FROM item WHERE name LIKE ?";
		ResultSet rs = null;
		try (PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, "%" + str + "%");
			rs = st.executeQuery();
			if (rs.next()) {
				return rs.getInt("count");
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	private void getConnection() throws DAOException {

		Properties pr = new Properties();
		try (FileInputStream in = new FileInputStream(filePath)) {
			pr.load(in);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("設定ファイルの読み込みに失敗しました。");
		}

		String className = pr.getProperty("className");
		String url = pr.getProperty("url");
		String user = pr.getProperty("user");
		String pass = pr.getProperty("pass");

		try {
			//JDBCドライバの登録
			Class.forName(className);

			// データベースへの接続
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
