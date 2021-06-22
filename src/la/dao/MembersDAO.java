package la.dao;

import la.bean.MemberBean;

public class MembersDAO {
	public MemberBean find(String email) {
		MemberBean member = new MemberBean();

		member.setPassword("test");

		return member;
	}
}
