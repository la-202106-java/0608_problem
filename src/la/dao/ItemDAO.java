package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.ItemBean;

public class ItemDAO {

	private Connection con;

	public ItemDAO() throws DAOException {
		getConnection();
	}

	public List<ItemBean> findAll() throws DAOException {
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
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
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

	//ソート
	public List<ItemBean> sortPrice(Boolean isAscending) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			//SQL作成
			String sql;
			//ソートキーの指定

			if (isAscending)
				sql = "SELECT * FROM item ORDER BY price";
			else
				sql = "SELECT * FROM item ORDER BY price desc";
			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			//SQLの実行
			rs = st.executeQuery();
			//結果の取得
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
				list.add(bean);
			}
			//商品一覧をlistとして返す
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
				//リソースの開放
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

	//追加
	public int addItem(String name, int price) throws DAOException {
		if (con == null)
			getConnection();

		PreparedStatement st = null;

		try {
			//SQL作成
			String sql = "INSERT INTO item(name,price) VALUES(?,?)";
			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			st.setString(1, name);
			st.setInt(2, price);
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

	//値段検索（両方）
	public List<ItemBean> findByPrice(int minPrice, int maxPrice) throws DAOException {
		if (con == null)
			getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			//SQL作成
			String sql = "SELECT * FROM item WHERE ? <= price and price <= ?";

			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			//値段のセット
			st.setInt(1, minPrice);
			st.setInt(2, maxPrice);
			//SQLの実行
			rs = st.executeQuery();
			//結果の取得
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
				list.add(bean);
			}
			//商品一覧をlistとして返す
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
				//リソースの開放
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

	//値段検索minPriceのみ
	public List<ItemBean> findByminPrice(int minPrice) throws DAOException {
		if (con == null)
			getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			//SQL作成
			String sql = "SELECT * FROM item WHERE ? <= price";

			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			//値段のセット
			st.setInt(1, minPrice);
			//SQLの実行
			rs = st.executeQuery();
			//結果の取得
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
				list.add(bean);
			}
			//商品一覧をlistとして返す
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
				//リソースの開放
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

	//値段検索maxPriceのみ
	public List<ItemBean> findBymaxPrice(int maxPrice) throws DAOException {
		if (con == null)
			getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			//SQL作成
			String sql = "SELECT * FROM item WHERE price <= ?";

			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			//値段のセット
			st.setInt(1, maxPrice);
			//SQLの実行
			rs = st.executeQuery();
			//結果の取得
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
				list.add(bean);
			}
			//商品一覧をlistとして返す
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
				//リソースの開放
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

	//商品名で検索
	public List<ItemBean> findByName(String namePart) throws DAOException {
		if (con == null)
			getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			//SQL作成
			String sql = "SELECT * FROM item WHERE name LIKE ?";

			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			//名前のセット
			String NamePart = "%" + namePart + "%";
			st.setString(1, NamePart);
			//SQLの実行
			rs = st.executeQuery();
			//結果の取得
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
				list.add(bean);
			}
			//商品一覧をlistとして返す
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
				//リソースの開放
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

	//検索からのソート
	public List<ItemBean> findByNameSortPrice(String namePart, int minPrice, int maxPrice, Boolean isAscending)
			throws DAOException {
		if (con == null)
			getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			//SQL作成
			String sql;
			String namesql;
			if (!namePart.isEmpty()) {

			}

			if (isAscending) {
				sql = "SELECT * FROM item WHERE name LIKE ? ORDER BY price";
			} else {
				sql = "SELECT * FROM item WHERE name LIKE ? ORDER BY price desc";
			}

			//PreparedStatementオブジェクトの取得
			st = con.prepareStatement(sql);
			//名前のセット
			String NamePart = "%" + namePart + "%";
			st.setString(1, NamePart);

			//SQLの実行
			rs = st.executeQuery();
			//結果の取得
			List<ItemBean> list = new ArrayList<ItemBean>();
			while (rs.next()) {
				int code = rs.getInt("code");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				ItemBean bean = new ItemBean(code, name, price);
				list.add(bean);
			}
			//商品一覧をlistとして返す
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの操作に失敗しました。");
		} finally {
			try {
				//リソースの開放
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
