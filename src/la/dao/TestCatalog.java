package la.dao;

import java.util.List;

import la.bean.CatalogBean;

public class TestCatalog {
	public static void main(String[] args) {
		CatalogDAO catalog;
		try {
			catalog = new CatalogDAO();
			List<CatalogBean> list = catalog.findAll();
			for (CatalogBean bean : list) {
				System.out.println(bean.getIsbn());
				System.out.println(bean.getTitle());
				System.out.println(bean.getCode());
				System.out.println(bean.getAuther());
				System.out.println(bean.getPublisher());
				System.out.println(bean.getPublicationDate());
			}

			List<CatalogBean> list2 = catalog.findByTitle("7つの習慣");
			for (CatalogBean bean : list2) {
				System.out.println(bean.getIsbn());
				System.out.println(bean.getTitle());
				System.out.println(bean.getCode());
				System.out.println(bean.getAuther());
				System.out.println(bean.getPublisher());
				System.out.println(bean.getPublicationDate());
			}

		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}
