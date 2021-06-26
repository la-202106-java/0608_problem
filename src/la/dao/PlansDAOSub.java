package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import la.bean.InnBean;
import la.bean.PlanBean;

public class PlansDAOSub {
	private Connection con;

	public PlansDAOSub() throws DAOException {
		getConnection();
	}

	public List<PlanBean> find(String checkIn, String checkOut) throws DAOException {
		if (con == null) {
			getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT"
					+ " p.plan_id,"
					+ " m.min_can_reserve_room_count,"
					+ " p.inn_id,"
					+ " p.contents,"
					+ " p.fee,"
					+ " p.room_max,"
					+ " p.img_url,"
					+ " p.delete_date as plans_delete_date,"
					+ " p.note as plans_note,"
					+ " i.name,"
					+ " i.class_code,"
					+ " i.postal_code,"
					+ " i.address,"
					+ " i.in_time,"
					+ " i.out_time,"
					+ " i.delete_date as inns_delete_date,"
					+ " i.note as inns_note"
					+ " from stay_plans as p"
					+ " inner join inns i on p.inn_id = i.id"
					+ " inner join"
					+ " ("
					+ " SELECT"
					+ " plan_id,min(can_reserve_room_count) as min_can_reserve_room_count"
					+ " FROM"
					+ " (SELECT"
					+ " p.plan_id,"
					+ " p.room_max,"
					+ " COALESCE(r.reserved_room_count,0 ) as reserved_room_count,"
					+ " p.room_max - COALESCE(reserved_room_count, 0) as can_reserve_room_count"
					+ " FROM"
					+ " (SELECT"
					+ " r.plan_id,"
					+ " d.reserve_date,"
					+ " SUM(d.room) AS reserved_room_count"
					+ " FROM reservation_details d"
					+ " INNER JOIN reservations r on r.id = d.reservations_id"
					+ " WHERE reserve_date BETWEEN ? and ?"
					+ " GROUP BY r.plan_id, reserve_date"
					+ " ) AS r"
					+ " RIGHT OUTER JOIN stay_plans p"
					+ " on p.plan_id = r.plan_id"
					+ " ) as r"
					+ " GROUP BY plan_id"
					+ " HAVING min(can_reserve_room_count) > 0"
					+ " ) as m  on p.plan_id= m.plan_id"
					+ " WHERE p.delete_date is null";

			st = con.prepareStatement(sql);
			st.setDate(1, java.sql.Date.valueOf(checkIn));
			st.setDate(2, java.sql.Date.valueOf(checkOut));

			rs = st.executeQuery();

			List<PlanBean> plans = new ArrayList<PlanBean>();

			while (rs.next()) {
				// PlanBean
				int planId = rs.getInt("plan_id");
				int innId = rs.getInt("inn_id");
				String content = rs.getString("contents");
				int fee = rs.getInt("fee");
				int roomMax = rs.getInt("min_can_reserve_room_count");
				String imgUrl = rs.getString("img_url");
				LocalDate deleteDate = null;
				if (rs.getDate("plans_delete_date") != null) {
					deleteDate = rs.getDate("plans_delete_date").toLocalDate();
				}
				String note = rs.getString("plans_note");

				// InnBean
				int id = rs.getInt("inn_id");
				String name = rs.getString("name");
				int classCode = rs.getInt("class_code");
				String postalCode = rs.getString("postal_code");
				String address = rs.getString("address");
				String inTime = rs.getTime("in_time").toString();
				String outTime = rs.getTime("out_time").toString();
				LocalDate deleteDateInn = null;
				if (rs.getDate("inns_delete_date") != null) {
					deleteDateInn = rs.getDate("inns_delete_date").toLocalDate();
				}
				String noteInn = rs.getString("inns_note");

				PlanBean plan = new PlanBean();
				InnBean inn = new InnBean();

				inn.setId(id);
				inn.setName(name);
				inn.setClassCode(classCode);
				inn.setPostalCode(postalCode);
				inn.setAddress(address);
				inn.setInTime(inTime);
				inn.setOutTime(outTime);
				inn.setDeleteDate(deleteDateInn);
				inn.setNote(noteInn);

				plan.setPlanId(planId);
				plan.setInnId(innId);
				plan.setContent(content);
				plan.setFee(fee);
				plan.setRoomMax(roomMax);
				plan.setImgUrl(imgUrl);
				plan.setDeleteDate(deleteDate);
				plan.setNote(note);
				plan.setInn(inn);

				plans.add(plan);
			}

			return plans;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
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

	public List<PlanBean> findInnName(String innName, String checkIn, String checkOut) throws DAOException {
		if (con == null) {
			getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT"
					+ " p.plan_id,"
					+ " m.min_can_reserve_room_count,"
					+ " p.inn_id,"
					+ " p.contents,"
					+ " p.fee,"
					+ " p.room_max,"
					+ " p.img_url,"
					+ " p.delete_date as plans_delete_date,"
					+ " p.note as plans_note,"
					+ " i.name,"
					+ " i.class_code,"
					+ " i.postal_code,"
					+ " i.address,"
					+ " i.in_time,"
					+ " i.out_time,"
					+ " i.delete_date as inns_delete_date,"
					+ " i.note as inns_note"
					+ " from stay_plans as p"
					+ " inner join inns i on p.inn_id = i.id"
					+ " inner join"
					+ " ("
					+ " SELECT"
					+ " plan_id,min(can_reserve_room_count) as min_can_reserve_room_count"
					+ " FROM"
					+ " (SELECT"
					+ " p.plan_id,"
					+ " p.room_max,"
					+ " COALESCE(r.reserved_room_count,0 ) as reserved_room_count,"
					+ " p.room_max - COALESCE(reserved_room_count, 0) as can_reserve_room_count"
					+ " FROM"
					+ " (SELECT"
					+ " r.plan_id,"
					+ " d.reserve_date,"
					+ " SUM(d.room) AS reserved_room_count"
					+ " FROM reservation_details d"
					+ " INNER JOIN reservations r on r.id = d.reservations_id"
					+ " WHERE reserve_date BETWEEN ? and ?"
					+ " GROUP BY r.plan_id, reserve_date"
					+ " ) AS r"
					+ " RIGHT OUTER JOIN stay_plans p"
					+ " on p.plan_id = r.plan_id"
					+ " ) as r"
					+ " GROUP BY plan_id"
					+ " HAVING min(can_reserve_room_count) > 0"
					+ " ) as m  on p.plan_id= m.plan_id"
					+ " WHERE i.name LIKE ?"
					+ " AND p.delete_date is null";

			st = con.prepareStatement(sql);
			st.setDate(1, java.sql.Date.valueOf(checkIn));
			st.setDate(2, java.sql.Date.valueOf(checkOut));
			st.setString(3, "%" + innName + "%");

			rs = st.executeQuery();

			List<PlanBean> plans = new ArrayList<PlanBean>();

			while (rs.next()) {
				// PlanBean
				int planId = rs.getInt("plan_id");
				int innId = rs.getInt("inn_id");
				String content = rs.getString("contents");
				int fee = rs.getInt("fee");
				int roomMax = rs.getInt("min_can_reserve_room_count");
				String imgUrl = rs.getString("img_url");
				LocalDate deleteDate = null;
				if (rs.getDate("plans_delete_date") != null) {
					deleteDate = rs.getDate("plans_delete_date").toLocalDate();
				}
				String note = rs.getString("plans_note");

				// InnBean
				int id = rs.getInt("inn_id");
				String name = rs.getString("name");
				int classCode = rs.getInt("class_code");
				String postalCode = rs.getString("postal_code");
				String address = rs.getString("address");
				String inTime = rs.getTime("in_time").toString();
				String outTime = rs.getTime("out_time").toString();
				LocalDate deleteDateInn = null;
				if (rs.getDate("inns_delete_date") != null) {
					deleteDateInn = rs.getDate("inns_delete_date").toLocalDate();
				}
				String noteInn = rs.getString("inns_note");

				PlanBean plan = new PlanBean();
				InnBean inn = new InnBean();

				inn.setId(id);
				inn.setName(name);
				inn.setClassCode(classCode);
				inn.setPostalCode(postalCode);
				inn.setAddress(address);
				inn.setInTime(inTime);
				inn.setOutTime(outTime);
				inn.setDeleteDate(deleteDateInn);
				inn.setNote(noteInn);

				plan.setPlanId(planId);
				plan.setInnId(innId);
				plan.setContent(content);
				plan.setFee(fee);
				plan.setRoomMax(roomMax);
				plan.setImgUrl(imgUrl);
				plan.setDeleteDate(deleteDate);
				plan.setNote(note);
				plan.setInn(inn);

				plans.add(plan);
			}

			return plans;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
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

	public List<PlanBean> findPlace(String place, String checkIn, String checkOut) throws DAOException {
		if (con == null) {
			getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT"
					+ " p.plan_id,"
					+ " m.min_can_reserve_room_count,"
					+ " p.inn_id,"
					+ " p.contents,"
					+ " p.fee,"
					+ " p.room_max,"
					+ " p.img_url,"
					+ " p.delete_date as plans_delete_date,"
					+ " p.note as plans_note,"
					+ " i.name,"
					+ " i.class_code,"
					+ " i.postal_code,"
					+ " i.address,"
					+ " i.in_time,"
					+ " i.out_time,"
					+ " i.delete_date as inns_delete_date,"
					+ " i.note as inns_note"
					+ " from stay_plans as p"
					+ " inner join inns i on p.inn_id = i.id"
					+ " inner join"
					+ " ("
					+ " SELECT"
					+ " plan_id,min(can_reserve_room_count) as min_can_reserve_room_count"
					+ " FROM"
					+ " (SELECT"
					+ " p.plan_id,"
					+ " p.room_max,"
					+ " COALESCE(r.reserved_room_count,0 ) as reserved_room_count,"
					+ " p.room_max - COALESCE(reserved_room_count, 0) as can_reserve_room_count"
					+ " FROM"
					+ " (SELECT"
					+ " r.plan_id,"
					+ " d.reserve_date,"
					+ " SUM(d.room) AS reserved_room_count"
					+ " FROM reservation_details d"
					+ " INNER JOIN reservations r on r.id = d.reservations_id"
					+ " WHERE reserve_date BETWEEN ? and ?"
					+ " GROUP BY r.plan_id, reserve_date"
					+ " ) AS r"
					+ " RIGHT OUTER JOIN stay_plans p"
					+ " on p.plan_id = r.plan_id"
					+ " ) as r"
					+ " GROUP BY plan_id"
					+ " HAVING min(can_reserve_room_count) > 0"
					+ " ) as m  on p.plan_id= m.plan_id"
					+ " WHERE i.address LIKE ?"
					+ " AND p.delete_date is null";

			st = con.prepareStatement(sql);
			st.setDate(1, java.sql.Date.valueOf(checkIn));
			st.setDate(2, java.sql.Date.valueOf(checkOut));
			st.setString(3, "%" + place + "%");

			rs = st.executeQuery();

			List<PlanBean> plans = new ArrayList<PlanBean>();

			while (rs.next()) {
				// PlanBean
				int planId = rs.getInt("plan_id");
				int innId = rs.getInt("inn_id");
				String content = rs.getString("contents");
				int fee = rs.getInt("fee");
				int roomMax = rs.getInt("min_can_reserve_room_count");
				String imgUrl = rs.getString("img_url");
				LocalDate deleteDate = null;
				if (rs.getDate("plans_delete_date") != null) {
					deleteDate = rs.getDate("plans_delete_date").toLocalDate();
				}
				String note = rs.getString("plans_note");

				// InnBean
				int id = rs.getInt("inn_id");
				String name = rs.getString("name");
				int classCode = rs.getInt("class_code");
				String postalCode = rs.getString("postal_code");
				String address = rs.getString("address");
				String inTime = rs.getTime("in_time").toString();
				String outTime = rs.getTime("out_time").toString();
				LocalDate deleteDateInn = null;
				if (rs.getDate("inns_delete_date") != null) {
					deleteDateInn = rs.getDate("inns_delete_date").toLocalDate();
				}
				String noteInn = rs.getString("inns_note");

				PlanBean plan = new PlanBean();
				InnBean inn = new InnBean();

				inn.setId(id);
				inn.setName(name);
				inn.setClassCode(classCode);
				inn.setPostalCode(postalCode);
				inn.setAddress(address);
				inn.setInTime(inTime);
				inn.setOutTime(outTime);
				inn.setDeleteDate(deleteDateInn);
				inn.setNote(noteInn);

				plan.setPlanId(planId);
				plan.setInnId(innId);
				plan.setContent(content);
				plan.setFee(fee);
				plan.setRoomMax(roomMax);
				plan.setImgUrl(imgUrl);
				plan.setDeleteDate(deleteDate);
				plan.setNote(note);
				plan.setInn(inn);

				plans.add(plan);
			}

			return plans;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
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

	public List<PlanBean> findFee(int lower, int upper, String checkIn, String checkOut) throws DAOException {
		if (con == null) {
			getConnection();
		}

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT"
					+ " p.plan_id,"
					+ " m.min_can_reserve_room_count,"
					+ " p.inn_id,"
					+ " p.contents,"
					+ " p.fee,"
					+ " p.room_max,"
					+ " p.img_url,"
					+ " p.delete_date as plans_delete_date,"
					+ " p.note as plans_note,"
					+ " i.name,"
					+ " i.class_code,"
					+ " i.postal_code,"
					+ " i.address,"
					+ " i.in_time,"
					+ " i.out_time,"
					+ " i.delete_date as inns_delete_date,"
					+ " i.note as inns_note"
					+ " from stay_plans as p"
					+ " inner join inns i on p.inn_id = i.id"
					+ " inner join"
					+ " ("
					+ " SELECT"
					+ " plan_id,min(can_reserve_room_count) as min_can_reserve_room_count"
					+ " FROM"
					+ " (SELECT"
					+ " p.plan_id,"
					+ " p.room_max,"
					+ " COALESCE(r.reserved_room_count,0 ) as reserved_room_count,"
					+ " p.room_max - COALESCE(reserved_room_count, 0) as can_reserve_room_count"
					+ " FROM"
					+ " (SELECT"
					+ " r.plan_id,"
					+ " d.reserve_date,"
					+ " SUM(d.room) AS reserved_room_count"
					+ " FROM reservation_details d"
					+ " INNER JOIN reservations r on r.id = d.reservations_id"
					+ " WHERE reserve_date BETWEEN ? and ?"
					+ " GROUP BY r.plan_id, reserve_date"
					+ " ) AS r"
					+ " RIGHT OUTER JOIN stay_plans p"
					+ " on p.plan_id = r.plan_id"
					+ " ) as r"
					+ " GROUP BY plan_id"
					+ " HAVING min(can_reserve_room_count) > 0"
					+ " ) as m  on p.plan_id= m.plan_id"
					+ " WHERE ? <= p.fee AND p.fee <= ?"
					+ " AND p.delete_date is null";

			st = con.prepareStatement(sql);
			st.setDate(1, java.sql.Date.valueOf(checkIn));
			st.setDate(2, java.sql.Date.valueOf(checkOut));
			st.setInt(3, lower);
			st.setInt(4, upper);

			rs = st.executeQuery();

			List<PlanBean> plans = new ArrayList<PlanBean>();

			while (rs.next()) {
				// PlanBean
				int planId = rs.getInt("plan_id");
				int innId = rs.getInt("inn_id");
				String content = rs.getString("contents");
				int fee = rs.getInt("fee");
				int roomMax = rs.getInt("room_max");
				String imgUrl = rs.getString("img_url");
				LocalDate deleteDate = null;
				if (rs.getDate("plans_delete_date") != null) {
					deleteDate = rs.getDate("plans_delete_date").toLocalDate();
				}
				String note = rs.getString("plans_note");

				// InnBean
				int id = rs.getInt("inn_id");
				String name = rs.getString("name");
				int classCode = rs.getInt("class_code");
				String postalCode = rs.getString("postal_code");
				String address = rs.getString("address");
				String inTime = rs.getTime("in_time").toString();
				String outTime = rs.getTime("out_time").toString();
				LocalDate deleteDateInn = null;
				if (rs.getDate("inns_delete_date") != null) {
					deleteDateInn = rs.getDate("inns_delete_date").toLocalDate();
				}
				String noteInn = rs.getString("inns_note");

				PlanBean plan = new PlanBean();
				InnBean inn = new InnBean();

				inn.setId(id);
				inn.setName(name);
				inn.setClassCode(classCode);
				inn.setPostalCode(postalCode);
				inn.setAddress(address);
				inn.setInTime(inTime);
				inn.setOutTime(outTime);
				inn.setDeleteDate(deleteDateInn);
				inn.setNote(noteInn);

				plan.setPlanId(planId);
				plan.setInnId(innId);
				plan.setContent(content);
				plan.setFee(fee);
				plan.setRoomMax(roomMax);
				plan.setImgUrl(imgUrl);
				plan.setDeleteDate(deleteDate);
				plan.setNote(note);
				plan.setInn(inn);

				plans.add(plan);
			}

			return plans;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
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

	public List<PlanBean> find(String checkIn, String checkOut, String innName, String place, int lower, int upper)
			throws DAOException {
		if (con == null) {
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT"
					+ " p.plan_id,"
					+ " m.min_can_reserve_room_count,"
					+ " p.inn_id,"
					+ " p.contents,"
					+ " p.fee,"
					+ " p.room_max,"
					+ " p.img_url,"
					+ " p.delete_date as plans_delete_date,"
					+ " p.note as plans_note,"
					+ " i.name,"
					+ " i.class_code,"
					+ " i.postal_code,"
					+ " i.address,"
					+ " i.in_time,"
					+ " i.out_time,"
					+ " i.delete_date as inns_delete_date,"
					+ " i.note as inns_note"
					+ " from stay_plans as p"
					+ " inner join inns i on p.inn_id = i.id"
					+ " inner join"
					+ " ("
					+ " SELECT"
					+ " plan_id,min(can_reserve_room_count) as min_can_reserve_room_count"
					+ " FROM"
					+ " (SELECT"
					+ " p.plan_id,"
					+ " p.room_max,"
					+ " COALESCE(r.reserved_room_count,0 ) as reserved_room_count,"
					+ " p.room_max - COALESCE(reserved_room_count, 0) as can_reserve_room_count"
					+ " FROM"
					+ " (SELECT"
					+ " r.plan_id,"
					+ " d.reserve_date,"
					+ " SUM(d.room) AS reserved_room_count"
					+ " FROM reservation_details d"
					+ " INNER JOIN reservations r on r.id = d.reservations_id"
					+ " WHERE reserve_date BETWEEN ? and ?"
					+ " GROUP BY r.plan_id, reserve_date"
					+ " ) AS r"
					+ " RIGHT OUTER JOIN stay_plans p"
					+ " on p.plan_id = r.plan_id"
					+ " ) as r"
					+ " GROUP BY plan_id"
					+ " HAVING min(can_reserve_room_count) > 0"
					+ " ) as m  on p.plan_id= m.plan_id"
					+ " WHERE ? <= p.fee AND p.fee <= ?"
					+ " AND i.name LIKE ?"
					+ " AND i.address LIKE ?"
					+ " AND p.delete_date is null";

			st = con.prepareStatement(sql);
			st.setDate(1, java.sql.Date.valueOf(checkIn));
			st.setDate(2, java.sql.Date.valueOf(checkOut));
			st.setInt(3, lower);
			st.setInt(4, upper);
			st.setString(5, "%" + innName + "%");
			st.setString(6, "%" + place + "%");

			rs = st.executeQuery();

			List<PlanBean> plans = new ArrayList<PlanBean>();

			while (rs.next()) {
				// PlanBean
				int planId = rs.getInt("plan_id");
				int innId = rs.getInt("inn_id");
				String content = rs.getString("contents");
				int fee = rs.getInt("fee");
				int roomMax = rs.getInt("room_max");
				String imgUrl = rs.getString("img_url");
				LocalDate deleteDate = null;
				if (rs.getDate("plans_delete_date") != null) {
					deleteDate = rs.getDate("plans_delete_date").toLocalDate();
				}
				String note = rs.getString("plans_note");

				// InnBean
				int id = rs.getInt("inn_id");
				String name = rs.getString("name");
				int classCode = rs.getInt("class_code");
				String postalCode = rs.getString("postal_code");
				String address = rs.getString("address");
				String inTime = rs.getTime("in_time").toString();
				String outTime = rs.getTime("out_time").toString();
				LocalDate deleteDateInn = null;
				if (rs.getDate("inns_delete_date") != null) {
					deleteDateInn = rs.getDate("inns_delete_date").toLocalDate();
				}
				String noteInn = rs.getString("inns_note");

				PlanBean plan = new PlanBean();
				InnBean inn = new InnBean();

				inn.setId(id);
				inn.setName(name);
				inn.setClassCode(classCode);
				inn.setPostalCode(postalCode);
				inn.setAddress(address);
				inn.setInTime(inTime);
				inn.setOutTime(outTime);
				inn.setDeleteDate(deleteDateInn);
				inn.setNote(noteInn);

				plan.setPlanId(planId);
				plan.setInnId(innId);
				plan.setContent(content);
				plan.setFee(fee);
				plan.setRoomMax(roomMax);
				plan.setImgUrl(imgUrl);
				plan.setDeleteDate(deleteDate);
				plan.setNote(note);
				plan.setInn(inn);

				plans.add(plan);
			}

			return plans;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		} finally {
			try {
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
