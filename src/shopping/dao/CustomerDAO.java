package shopping.dao;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import shopping.bean.CustomerBean;

public class CustomerDAO {
	private Connection con;
	private String path;

	public CustomerDAO() throws DAOException {
		getConnection();
	}

	public CustomerDAO(String path) throws DAOException {
		this.path = path;
		getConnection();
	}

	public CustomerBean findByEmailAndPassword(String email, String password) throws DAOException {
		if (con == null)
			getConnection();

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		byte[] b = password.getBytes();
		String hashedCode = new String(md.digest(b));

		String sql = "SELECT * FROM customer WHERE email = ? AND password = ? ";
		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, email);
			st.setString(2, hashedCode);

			try (ResultSet rs = st.executeQuery()) {

				if (rs.next()) {
					int code = rs.getInt("code");
					String name = rs.getString("name");
					String address = rs.getString("address");
					String tel = rs.getString("tel");

					CustomerBean bean = new CustomerBean(code, name, address, tel, email, password);
					return bean;
				} else {
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("レコードの操作に失敗しました");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました");
		}
	}

	public CustomerBean findByEmail(String email) throws DAOException {
		if (con == null)
			getConnection();

		String sql = "SELECT * FROM customer WHERE email = ?";
		try (PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, email);

			try (ResultSet rs = st.executeQuery()) {

				if (rs.next()) {
					int code = rs.getInt("code");
					String name = rs.getString("name");
					String address = rs.getString("address");
					String tel = rs.getString("tel");
					String password = rs.getString("password");

					CustomerBean bean = new CustomerBean(code, name, address, tel, email, password);
					return bean;
				} else {
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new DAOException("レコードの操作に失敗しました");

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました");
		}
	}

	public CustomerBean registByInputs(String name, String address, String tel, String email, String password)
			throws DAOException {

		if (con == null)
			getConnection();

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		byte[] b = password.getBytes();
		String hashedCode = new String(md.digest(b));

		CustomerBean cu = findByEmail(email);
		if (cu != null) {
			return null;
		}

		String sql = "INSERT INTO customer(name, address, tel, email, password) VALUES(?, ?, ?, ?, ?)";

		// PreparedStatementオブジェクトの取得
		try (PreparedStatement st = con.prepareStatement(sql)) {

			st.setString(1, name);
			st.setString(2, address);
			st.setString(3, tel);
			st.setString(4, email);
			st.setString(5, hashedCode);
			// SQLの実行
			st.executeUpdate();

			return findByEmail(email);

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("登録時にエラーが発生しました");
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

}
