package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import la.bean.Member;

public class MemberDAO {

	public int updateMember(Member member) {
		String sql = "update member set member_name=? where member_id=? ";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, member.getName());
			st.setInt(2, member.getId());
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

	/*****会員登録 by ishida********
	//	 */
	//	public int confirmMember(String name, String address, String tel, String mail, String birth) {
	//		String sql = " INSERT INTO member(member_name,member_address,tel,email,birth) VALUES(?,?,?,?,?)";
	//		try (Connection con = ConnectionFactory.createConnection();
	//				PreparedStatement st = con.prepareStatement(sql);) {
	//			st.setString(1, name);
	//			st.setString(2, address);
	//			st.setString(3, tel);
	//			st.setString(4, mail);
	//			st.setString(5, birth);
	//			int rows = st.executeUpdate();
	//			return rows;
	//		} catch (SQLException e) {
	//			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
	//		}
	//	}

	public int addMember(String name, String address, String tel, String mail, String birth) {
		String sql = " INSERT INTO member(member_name,member_address,tel,email,birth) VALUES(?,?,?,?,?)";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, name);
			st.setString(2, address);
			st.setString(3, tel);
			st.setString(4, mail);
			st.setString(5, birth);
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

	public List<Member> findByName(Member member) {
		String sql = "SELECT * FROM member where member_id = ?";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, member.getId());
			try (ResultSet rs = st.executeQuery()) {
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
}
