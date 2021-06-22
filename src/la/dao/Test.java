package la.dao;

import java.util.List;

import la.bean.BookBean;

public class Test {
	public static void main(String[] args) {
		BookDAO book;
		try {
			book = new BookDAO();
			List<BookBean> list = book.findAll();
			for (BookBean bean : list) {
				System.out.println(bean.getTitle());
			}

		} catch (DAOException e) {
			e.printStackTrace();
		}

	}
}
