package la.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			String sql = "SELECT * FROM reservations WHERE NOT(? < in_date OR out_date < ?)";

			st = con.prepareStatement(sql);
			st.setString(1, checkOut);
			st.setString(2, checkIn);

			rs = st.executeQuery();

			List<PlanBean> plans = new ArrayList<PlanBean>();
			PlanBean plan = new PlanBean();
			InnBean inn = new InnBean();

			List<Integer> planIds = new ArrayList<Integer>();

			while (rs.next()) {
				planIds.add(rs.getInt("plan_id"));
			}

			// 予約一覧テーブルから条件指定してとってきたplan_idの数数える
			// 同じplan_idの合計数がプラン一覧テーブルの最大部屋数になっている場合に表示しない
			// 使用可能部屋数が1部屋の場合は予約時に問題ないが、複数ある場合、予約時に可能な数まで予約部屋数を選べる機能をどうやって実現するのか
			// 無理や...
			plans.add(plan);

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

	// このメソッド多分使わん、動作確認で使用
	public PlanBean find(int planId) {
		PlanBean plan = new PlanBean();
		InnBean inn = new InnBean();

		inn.setName("テスト宿");
		plan.setInn(inn);
		plan.setContent("テスト夕食付");
		plan.setFee(70000);
		plan.setRoomMax(2);
		plan.setImgUrl("test.png");

		return plan;
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
