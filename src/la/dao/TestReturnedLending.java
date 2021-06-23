package la.dao;

import java.util.List;

import la.bean.ReturnedLendingBean;

public class TestReturnedLending {
	public static void main(String[] args) {
		ReturnedLendingDAO returnedlend;
		try {
			returnedlend = new ReturnedLendingDAO();
			List<ReturnedLendingBean> list = returnedlend.findAll();
			for (ReturnedLendingBean bean : list) {
				System.out.println(bean.getId());
				System.out.println(bean.getBookId());
				System.out.println(bean.getUserId());
				System.out.println(bean.getLendingDate());
				System.out.println(bean.getDeadline());
				System.out.println(bean.getReturnDate());
			}

			List<ReturnedLendingBean> list2 = returnedlend.findByUserId(1);
			for (ReturnedLendingBean bean : list2) {
				System.out.println(bean.getId());
				System.out.println(bean.getBookId());
				System.out.println(bean.getUserId());
				System.out.println(bean.getLendingDate());
				System.out.println(bean.getDeadline());
				System.out.println(bean.getReturnDate());
			}

		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}
