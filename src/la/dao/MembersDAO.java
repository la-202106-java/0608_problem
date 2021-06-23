package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import la.bean.MemberBean;

public class MembersDAO {
	private Connection con;

	public MembersDAO() throws DAOException {
		getConnection();
	}

	//会員テーブル全表示
	public List<MemberBean> findAll() throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM members";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			List<MemberBean> list = new ArrayList<MemberBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				String postal_code = rs.getString("postal_code");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String email_address = rs.getString("email_address");
				java.sql.Date birth_date = rs.getDate("birth_date");
				java.sql.Date join_date = rs.getDate("join_date");
				java.sql.Date quit_date = rs.getDate("quit_date");
				MemberBean bean = new MemberBean(id, pass, name, postal_code, address, tel,
						email_address, birth_date, join_date, quit_date);
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
				if (st != null) {
					st.close();
				}
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}

	}

	//更新処理
	public int update(int id, String name, String pCode, String addr, String eAddr) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st1 = null;
		PreparedStatement st2 = null;
		PreparedStatement st3 = null;
		PreparedStatement st4 = null;
		try {
			String sql = null;
			int rows = 0;
			int tmprows;
			if (name != null) {
				sql = "UPDATE members SET name = ? WHERE id = ?";
				st1 = con.prepareStatement(sql);
				st1.setString(1, name);
				st1.setInt(2, id);
				tmprows = st1.executeUpdate();
				if (rows < tmprows) {
					rows = tmprows;
				}
			}

			if (pCode != null) {
				sql = "UPDATE members SET postal_code = ? WHERE id = ?";
				st2 = con.prepareStatement(sql);
				st2.setString(1, pCode);
				st2.setInt(2, id);
				tmprows = st2.executeUpdate();
				if (rows < tmprows) {
					rows = tmprows;
				}
			}

			if (addr != null) {
				sql = "UPDATE members SET address = ? WHERE id = ?";
				st3 = con.prepareStatement(sql);
				st3.setString(1, addr);
				st3.setInt(2, id);
				tmprows = st3.executeUpdate();
				if (rows < tmprows) {
					rows = tmprows;
				}
			}

			if (eAddr != null) {
				sql = "UPDATE members SET email_address = ? WHERE id = ?";
				st4 = con.prepareStatement(sql);
				st4.setString(1, eAddr);
				st4.setInt(2, id);
				tmprows = st4.executeUpdate();
				if (rows < tmprows) {
					rows = tmprows;
				}
			}
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (st1 != null)
					st1.close();
				if (st2 != null)
					st2.close();
				if (st3 != null)
					st3.close();
				if (st4 != null)
					st4.close();
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}
	}

	//退会処理
	public int quit(int id) throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;

		LocalDate now = LocalDate.now();
		java.sql.Date sqlDate = java.sql.Date.valueOf(now);

		try {
			String sql = "UPDATE members SET quit_date = ? WHERE id = ?";
			st = con.prepareStatement(sql);
			st.setDate(1, sqlDate);
			st.setInt(2, id);
			int rows = st.executeUpdate();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				close();
			} catch (Exception e) {
				throw new DAOException("リソースの解放に失敗しました。");
			}
		}

	}

	private void getConnection() throws DAOException {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql:reservationsystem";
			String user = "adminuser";
			String pass = "himitu";
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
