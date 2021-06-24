package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.Member;

public class MemberDAO {

	public int updateMember(int id, String name, String address, String tel, String mail) {
		String sql = "UPDATE member SET member_name = ?,member_address = ?,tel = ? , email = ? where member_id =?";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, name);
			st.setString(2, address);
			st.setString(3, tel);
			st.setString(4, mail);
			st.setInt(5, id);
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

	//退会日
	public int updateMember(int id) {
		String sql = "UPDATE member SET withdrawal_date=current_date where member_id =?";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, id);
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

	public int addMember(String name, String address, String tel, String mail, java.sql.Date birth) {
		String sql = " INSERT INTO member(member_name,member_address,tel,email,birth,join_date) VALUES(?,?,?,?,?,current_date)";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, name);
			st.setString(2, address);
			st.setString(3, tel);
			st.setString(4, mail);
			st.setDate(5, birth);
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

	public Member findByName2(String name) {
		//		String sql = "SELECT * FROM member where member_name = ?";
		//idでソートしてidが大きいものをとってくる
		String sql = "SELECT * FROM member order by member_id desc limit 1";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			try (ResultSet rs = st.executeQuery()) {

				if (rs.next()) {
					Member bean = new Member();
					bean.setId(rs.getInt("member_id"));
					bean.setName(rs.getString("member_name"));
					bean.setAddress(rs.getString("member_address"));
					bean.setTel(rs.getString("tel"));
					bean.seteMail(rs.getString("email"));
					bean.setBirth(rs.getDate("birth"));
					bean.setJoinDate(rs.getTimestamp("join_date"));
					bean.setWithdrawalDate(rs.getTimestamp("withdrawal_date"));
					return bean;
				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

	/*
		public Member findByName3(int id) {
			String sql = "SELECT * FROM member where member_id = ?";
			//idでソートしてidが大きいものをとってくる
			//		String sql = "SELECT * FROM member order by member_id desc limit 1";
			try (Connection con = ConnectionFactory.createConnection();
					PreparedStatement st = con.prepareStatement(sql);) {
				st.setInt(1, id);
				try (ResultSet rs = st.executeQuery()) {
	
					if (rs.next()) {
						Member bean = new Member();
						bean.setId(rs.getInt("member_id"));
						bean.setName(rs.getString("member_name"));
						bean.setAddress(rs.getString("member_address"));
						bean.setTel(rs.getString("tel"));
						bean.seteMail(rs.getString("email"));
						bean.setBirth(rs.getDate("birth"));
						bean.setJoinDate(rs.getTimestamp("join_date"));
						bean.setWithdrawalDate(rs.getTimestamp("withdrawal_date"));
						return bean;
					} else {
						return null;
					}
				}
			} catch (SQLException e) {
				throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
			}
		}
		*/
	public Member findByEmail(String email) {
		String sql = "SELECT * FROM member where email = ?";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, email);
			try (ResultSet rs = st.executeQuery()) {

				if (rs.next()) {
					Member bean = new Member();
					bean.setId(rs.getInt("member_id"));
					bean.setName(rs.getString("member_name"));
					bean.setAddress(rs.getString("member_address"));
					bean.setTel(rs.getString("tel"));
					bean.seteMail(rs.getString("email"));
					bean.setBirth(rs.getDate("birth"));
					bean.setJoinDate(rs.getTimestamp("join_date"));
					bean.setWithdrawalDate(rs.getTimestamp("withdrawal_date"));
					return bean;
				} else {
					return null;
				}
			}

		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

	public Member findById(int id) {
		String sql = "SELECT * FROM member where member_id = ?";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, id);
			try (ResultSet rs = st.executeQuery()) {

				if (rs.next()) {
					Member bean = new Member();
					bean.setId(rs.getInt("member_id"));
					bean.setName(rs.getString("member_name"));
					bean.setAddress(rs.getString("member_address"));
					bean.setTel(rs.getString("tel"));
					bean.seteMail(rs.getString("email"));
					bean.setBirth(rs.getDate("birth"));
					bean.setJoinDate(rs.getTimestamp("join_date"));
					bean.setWithdrawalDate(rs.getTimestamp("withdrawal_date"));
					return bean;
				} else {
					return null;
				}
			}

		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

	public List<Member> findAll() {
		String sql = "SELECT * FROM member";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			List<Member> list = new ArrayList<Member>();
			while (rs.next()) {
				Member bean = new Member();
				bean.setId(rs.getInt("member_id"));
				bean.setName(rs.getString("member_name"));
				bean.setAddress(rs.getString("member_address"));
				bean.setTel(rs.getString("tel"));
				bean.seteMail(rs.getString("email"));
				bean.setBirth(rs.getDate("birth"));
				bean.setJoinDate(rs.getTimestamp("join_date"));
				bean.setWithdrawalDate(rs.getTimestamp("withdrawal_date"));
				list.add(bean);

			}
			return list;
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}
	/*
		public static void main(String[] args) {
			MemberDAO md = new MemberDAO();
			Member member = new Member();
			member.setName("test");
			md.updateMember(member);
			List<Member> list = md.findAll();
			//		List<Member> list = md.findByName(member);
			for (Member m : list) {
				System.out.println(m.toString());
			}
		}
	*/
	/*
		public Member updateMember(int id, String name, String address, String tel, String mail) {
			String sql = "UPDATE member SET member_name = ?,member_address = ?,tel = ? , email = ? where member_id =?";
			try (Connection con = ConnectionFactory.createConnection();
					PreparedStatement st = con.prepareStatement(sql);) {
				st.setString(1, name);
				st.setString(2, address);
				st.setString(3, tel);
				st.setString(4, mail);
				st.setInt(5, id);
				try (ResultSet rs = st.executeUpdate() {
	
					if (rs.next()) {
						Member bean = new Member();
						bean.setId(rs.getInt("member_id"));
						bean.setName(rs.getString("member_name"));
						bean.setAddress(rs.getString("member_address"));
						bean.setTel(rs.getString("tel"));
						bean.seteMail(rs.getString("email"));
						bean.setBirth(rs.getDate("birth"));
						bean.setJoinDate(rs.getTimestamp("join_date"));
						bean.setWithdrawalDate(rs.getTimestamp("withdrawal_date"));
						return bean;
					} else {
						return null;
					}
				}
	
			} catch (SQLException e) {
				throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
			}
		}
		*/
}
