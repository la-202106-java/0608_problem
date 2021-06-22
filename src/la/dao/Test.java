package la.dao;

import java.util.List;

import la.bean.BookBean;
import la.bean.NowUserBean;

public class Test {
	public static void main(String[] args) {
		BookDAO book;
		NowUserDAO user;
		try {
			book = new BookDAO();
			List<BookBean> list = book.findAll();
			for (BookBean bean : list) {
				System.out.println(bean.getTitle());
			}

			user = new NowUserDAO();
			NowUserBean now = user.findbyEmail("shinjuku@gmail.com");
			System.out.println(now.getName());

		} catch (DAOException e) {
			e.printStackTrace();
		}

	}
}
