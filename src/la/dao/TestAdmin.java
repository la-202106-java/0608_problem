package la.dao;

import java.util.List;

import la.bean.AdminBean;

public class TestAdmin {
	public static void main(String[] args) {
		AdminDAO admin;
		try {
			admin = new AdminDAO();
			List<AdminBean> list = admin.findAll();
			for (AdminBean bean : list) {
				System.out.println(bean.getId());
			}

		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}
