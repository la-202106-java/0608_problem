package la.dao;

import java.util.List;

import la.bean.DiscardedBookBean;

public class Test {
	public static void main(String[] args) {
		DiscardedBookDAO book;
		try {
			book = new DiscardedBookDAO();
			List<DiscardedBookBean> list = book.findAll();
			for (DiscardedBookBean bean : list) {
				System.out.println(bean.getTitle());
			}

		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}
