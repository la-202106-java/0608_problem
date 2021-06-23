package la.dao;

import java.util.ArrayList;
import java.util.List;

import la.bean.InnBean;
import la.bean.PlanBean;

public class PlansDAOSub {
	public List<PlanBean> find(String checkIn, String checkOut) {
		List<PlanBean> plans = new ArrayList<PlanBean>();
		PlanBean plan = new PlanBean();
		InnBean inn = new InnBean();

		inn.setName("テスト宿");
		plan.setInn(inn);
		plan.setContent("テスト夕食付");
		plan.setFee(70000);
		plan.setRoomMax(2);
		plan.setImgUrl("test.png");

		plans.add(plan);

		return plans;
	}

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
}
