package la.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import la.bean.LendingLedger;
import la.bean.MaterialCatalog;
import la.bean.MaterialLedger;
import la.bean.Member;

public class LendingLedgerDAO {

	public List<LendingLedger> findUnreturn(int mid) {
		Member member = new Member();

		member.setId(mid);
		List<LendingLedger> list = this.findByBean(member);
		List<LendingLedger> result = new ArrayList<LendingLedger>();
		for (LendingLedger ll : list) {
			if (ll.getReturnDate() == null) {
				result.add(ll);
			}
		}
		return result;
	}

	public static Timestamp addDays(Timestamp date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return new Timestamp(cal.getTime().getTime());

	}

	public boolean isLending(int sid) {
		String sql = "select COUNT( * ) cnt from lending_ledger where material_id=? and return_date is null";
		System.out.println(sql);
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, sid);
			try (ResultSet rs = st.executeQuery()) {
				rs.next();
				int count = rs.getInt("cnt");
				if (count > 0) {
					return true;
				} else {
					return false;
				}

			}
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

	public int addLendingRecord(int mid, int sid) {
		//		MaterialLedger ml=new MaterialLedger();
		//		ml.setId(null);
		//		LendingLedger ll=this.findAll()
		MaterialLedgerDAO mlDAO = new MaterialLedgerDAO();
		MaterialLedger ml = null;
		try {
			ml = mlDAO.findById(sid);
		} catch (DAOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		MemberDAO memDAO = new MemberDAO();
		Member member = null;
		try {
			member = memDAO.findById(mid);
		} catch (DAOException e2) {
			// TODO 自動生成された catch ブロック
			e2.printStackTrace();
		}

		if (ml == null || member == null) {
			//会員もしくは資料は存在しません。
			return -1;
		}
		if (ml.getDisposalDate() != null) {
			//廃棄済みの資料
			return -2;
		}
		if (member.getWithdrawalDate() != null) {
			//退会済みの会員
			return -3;
		}
		MaterialCatalogDAO mcDAO = new MaterialCatalogDAO();
		MaterialCatalog mc = null;
		try {
			mc = mcDAO.findByISBNkey(ml.getIsbn());
		} catch (DAOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		if (mc == null) {
			//　資料目録は存在しません。
			return -4;
		}
		if (isLending(sid)) {
			//　すでに貸出されている資料
			return -5;
		}
		Date shiryoDate = mc.getPublicationDate();
		long diff = new Date().getTime() - shiryoDate.getTime();
		long duration = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		Timestamp returnDeadline = new Timestamp(System.currentTimeMillis());

		if (duration <= 90) {
			returnDeadline = addDays(returnDeadline, 10);
		} else {
			returnDeadline = addDays(returnDeadline, 15);
		}

		String sql = "INSERT INTO lending_ledger "
				+ "(member_id,material_id,checkout_date,return_deadline)"
				+ "values(?,?,?,?)";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, mid);
			st.setInt(2, sid);
			st.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			st.setTimestamp(4, returnDeadline);
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

	public int returnMaterial(int ledgerId) {
		String sql = "UPDATE lending_ledger set return_date=? where ledger_id=?";
		try (Connection con = ConnectionFactory.createConnection();
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			st.setInt(2, ledgerId);
			int rows = st.executeUpdate();
			return rows;
		} catch (SQLException e) {
			throw new IllegalStateException("SQL実行時に例外が発生しました。", e);
		}
	}

	public List<LendingLedger> findAll() {
		String sql = "select "
				+ "l.ledger_id id, "
				+ "m.member_id mid, "
				+ "m.member_name mname, "
				+ "mc.title title , "
				+ "l.checkout_date c_date, "
				+ "l.return_deadline deadline, "
				+ "l.return_date r_date "
				+ "from lending_ledger l "
				+ "left join member m  on l.member_id=m.member_id "
				+ "left join material_ledger ml on l.material_id=ml.material_id"
				+ " left join material_catalog mc on ml.isbn=mc.isbn ORDER BY l.checkout_date desc ;";

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
		String sql = "select "
				+ "l.ledger_id id, "
				+ "m.member_id mid, "
				+ "m.member_name mname, "
				+ "mc.title title , "
				+ "l.checkout_date c_date, "
				+ "l.return_deadline deadline, "
				+ "l.return_date r_date "
				+ "from lending_ledger l "
				+ "left join member m  on l.member_id=m.member_id "
				+ "left join material_ledger ml on l.material_id=ml.material_id"
				+ " left join material_catalog mc on ml.isbn=mc.isbn ";
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
		sql = sql + " ORDER BY l.checkout_date desc";

		//		System.out.println(sql);
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
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		System.out.println(sdf.format(d));
	}
}
