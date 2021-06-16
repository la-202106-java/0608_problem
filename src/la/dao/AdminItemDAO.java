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

	public List<AdminItemBean> findAll() throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			//SQL作成
			String sql = "SELECT * FROM item";
			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			//SQLの実行
			rs = st.executeQuery();
			//結果の取得
			List<AdminItemBean> list = new ArrayList<AdminItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				int category_code = rs.getInt("category_code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				AdminItemBean bean = new AdminItemBean(code, category_code, name, price);
				list.add(bean);

			}

			//商品一覧をListとして返す
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました");
		} finally {
			try {
				//リソースの開放
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

	//登録
	public int registItem(int category_code, String name, int price) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;

		try {
			//SQL作成
			String sql = "INSERT INTO item(category_code,name,price) VALUES(?,?,?)";
			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			st.setInt(1, category_code);
			st.setString(2, name);
			st.setInt(3, price);
			//SQLの実行
			int rows = st.executeUpdate();
			return rows;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
				//リソースの開放
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	//修正
	public int updateItem(int code, int category_code, String name, int price) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;

		try {
			//SQL作成
			String sql = "UPDATE item SET category_code = ? , name = ? , price = ? WHERE code =?";
			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			st.setInt(1, category_code);
			st.setString(2, name);
			st.setInt(3, price);
			st.setInt(4, code);
			//SQLの実行
			int rows = st.executeUpdate();
			return rows;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
				//リソースの開放
				if (st != null)
					st.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	//主キーで削除
	public int deleteByPrimaryKey(int key) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;

		try {
			//SQL作成
			String sql = "DELETE FROM item WHERE code = ?";
			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			st.setInt(1, key);
			//SQLの実行
			int rows = st.executeUpdate();
			return rows;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
				//リソースの開放
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
			//JDBCドライバの登録
			Class.forName("org.postgresql.Driver");
			//URL,username,password設定
			String url = "jdbc:postgresql:sample";
			String user = "student";
			String pass = "himitu";
			//データベースへの接続
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
