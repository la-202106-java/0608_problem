package la.dao;

import la.bean.MemberBean;

// ログイン時の処理を行うメソッド（emailが引数として渡られた際に、emaiが一致する会員情報を返却する）
//
public class MembersDAOSub {
	public void registration(MemberBean member) {

	}

	public MemberBean find(String email) {
		MemberBean member = new MemberBean();

		member.setPassword("test");

		return member;
	}
}
