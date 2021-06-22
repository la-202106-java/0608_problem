package la.dao;

import java.util.List;

import la.bean.DiscardedBookBean;
import la.bean.FormerUserBean;
import la.bean.NowUserBean;

public class Test {
	public static void main(String[] args) {
		DiscardedBookDAO book;
		NowUserDAO user;

		try {
			book = new DiscardedBookDAO();
			List<DiscardedBookBean> list = book.findAll();
			for (DiscardedBookBean bean : list) {
				System.out.println(bean.getTitle());
			}

			user = new NowUserDAO();
			NowUserBean now = user.findbyEmail("shinjuku@gmail.com");
			System.out.println(now.getName());
			System.out.println(now.getBirthDate());

			FormerUserDAO former = new FormerUserDAO();
			FormerUserBean fuser = former.findbyEmail("shinjuku@gmail.com");
			System.out.println(fuser.getName());
			System.out.println(fuser.getQuitDate());

		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}
