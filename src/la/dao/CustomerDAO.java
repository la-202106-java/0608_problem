package la.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import la.bean.CustomerBean;

public class CustomerDAO {

	private Connection con;
	private String filePath;

	public CustomerDAO(String filePath) throws DAOException {
		this.filePath = filePath;
		getConnection();
	}

	public CustomerBean findByEmailAndPassword(String email, String password) throws DAOException {
		if (con == null) {
			getConnection();
		}

		String sql = "SELECT * FROM customer WHERE email=? AND password=?";
		ResultSet rs = null;
		try (PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, email);
			st.setString(2, password);
			rs = st.executeQuery();

			if (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				CustomerBean bean = new CustomerBean(code, name, address, tel, email);
				return bean;
			} else {
				return null;
			}

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

	public int addCustomer(String name, String address, String tel, String email, String password)
			throws DAOException {
		if (con == null) {
			getConnection();
		}
		String sql = "INSERT INTO customer (name, address, tel, email, password) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, name);
			st.setString(2, address);
			st.setString(3, tel);
			st.setString(4, email);
			st.setString(5, password);

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
