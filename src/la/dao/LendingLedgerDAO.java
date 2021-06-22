package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import la.bean.LendingLedger;
import la.bean.Member;

public class LendingLedgerDAO {
	public int addLendingRecord(int mid, int sid) {
		String sql = "INSERT INTO lending_ledger "
				+ "(member_id,material_id,checkout_date,return_deadline)"
				+ "values(?,?,?,?)";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, mid);
			st.setInt(2, sid);
			st.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

	public List<LendingLedger> findAll() {
		String sql = "select l."
				+ "ledger_id id, "
				+ "m.member_id mid, "
				+ "m.member_name mname, "
				+ "mc.title title , "
				+ "l.checkout_date c_date, "
				+ "l.return_deadline deadline, "
				+ "l.return_date r_date "
				+ "from lending_ledger l "
				+ "left join member m  on l.member_id=m.member_id "
				+ "left join material_ledger ml on l.material_id=ml.material_id"
				+ " left join material_catalog mc on ml.isbn=mc.isbn;";
		System.out.println(sql);
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			List<LendingLedger> list = new ArrayList<LendingLedger>();
			while (rs.next()) {
				LendingLedger bean = new LendingLedger();
				bean.setId(rs.getInt("id"));
				bean.getMember().setId(rs.getInt("mid"));
				bean.getMember().setName(rs.getString("mname"));
				bean.getMaterialCatalog().setTitle(rs.getString("title"));
				bean.setReturnDate(rs.getDate("r_date"));
				bean.setCheckoutDate(rs.getDate("c_date"));
				bean.setReturnDeadline(rs.getDate("deadline"));
				list.add(bean);

			}
			return list;
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

	public List<LendingLedger> findByBean(Member member) {
		String sql = "select l."
				+ "ledger_id id, "
				+ "m.member_id mid, "
				+ "m.member_name mname, "
				+ "mc.title title , "
				+ "l.checkout_date c_date, "
				+ "l.return_deadline deadline, "
				+ "l.return_date r_date "
				+ "from lending_ledger l "
				+ "left join member m  on l.member_id=m.member_id "
				+ "left join material_ledger ml on l.material_id=ml.material_id"
				+ " left join material_catalog mc on ml.isbn=mc.isbn";
		if (member.getId() != -1 && member.getName() == null) {
			sql = sql + " where m.member_id=" + member.getId();
		}
		if (member.getId() == -1 && member.getName() != null) {
			sql = sql + " where m.member_name like '%" + member.getName() + "%'";

		}

		if (member.getId() != -1 && member.getName() != null) {
			sql = sql + " where m.member_id=" + member.getId();
			sql = sql + " and  m.member_name like '%" + member.getName() + "%'";

		}

		System.out.println(sql);
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {

			List<LendingLedger> list = new ArrayList<LendingLedger>();
			while (rs.next()) {
				LendingLedger bean = new LendingLedger();
				bean.setId(rs.getInt("id"));
				bean.getMember().setId(rs.getInt("mid"));
				bean.getMember().setName(rs.getString("mname"));
				bean.getMaterialCatalog().setTitle(rs.getString("title"));
				bean.setReturnDate(rs.getDate("r_date"));
				bean.setCheckoutDate(rs.getDate("c_date"));
				bean.setReturnDeadline(rs.getDate("deadline"));
				list.add(bean);

			}
			return list;
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

	public static void main(String[] args) {
		LendingLedgerDAO ld = new LendingLedgerDAO();
		List<LendingLedger> list = ld.findAll();
		for (LendingLedger ll : list) {
			System.out.println("会員id:" + ll.getId() + " 資料名:" + ll.getMaterialCatalog().getTitle());
		}
	}
}
