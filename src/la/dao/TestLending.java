package la.dao;

import java.util.List;

import la.bean.LendingBean;

public class TestLending {
	public static void main(String[] args) {
		LendingDAO admin;
		try {
			admin = new LendingDAO();
			List<LendingBean> list = admin.findAll();
			for (LendingBean bean : list) {
				System.out.println(bean.getId());
				System.out.println(bean.getBookId());
				System.out.println(bean.getUserId());
				System.out.println(bean.getLendingDate());
				System.out.println(bean.getDeadline());
			}

			List<LendingBean> list2 = admin.findByUserId(1);
			for (LendingBean bean : list2) {
				System.out.println(bean.getId());
				System.out.println(bean.getBookId());
				System.out.println(bean.getUserId());
				System.out.println(bean.getLendingDate());
				System.out.println(bean.getDeadline());
			}

		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
