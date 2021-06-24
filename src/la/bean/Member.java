package la.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Member {
	private int id;
	private String name;
	private String address;
	private String tel;
	private String eMail;
	private Date birth;
	private Date joinDate;
	private Date withdrawalDate;

	public Member() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getBirth() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(birth);
	}

	/*
		public Date getBirth() {
			return birth;
		}
	*/
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getJoinDate() {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(joinDate);
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getWithdrawalDate() {
		if (withdrawalDate != null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return df.format(withdrawalDate);
		} else {
			return null;
		}
	}

	/*
		public Date getWithdrawalDate() {
			return withdrawalDate;
		}
	*/
	public void setWithdrawalDate(Date withdrawalDate) {
		this.withdrawalDate = withdrawalDate;
	}

	public Member(int id, String name, String address, String tel, String eMail, Date birth, Date joinDate,
			Date withdrawalDate) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.eMail = eMail;
		this.birth = birth;
		this.joinDate = joinDate;
		this.withdrawalDate = withdrawalDate;
	}

	public Member(int id, String name, String address, String tel, String eMail, Date birth) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.eMail = eMail;
		this.birth = birth;
		this.joinDate = joinDate;
		this.withdrawalDate = withdrawalDate;
	}

	public Member(String name, String address, String tel, String eMail, Date birth) {
		super();
		//		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.eMail = eMail;
		this.birth = birth;
		//		this.joinDate = joinDate;
		//		this.withdrawalDate = withdrawalDate;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", address=" + address + ", tel=" + tel + ", eMail=" + eMail
				+ ", birth=" + birth + ", joinDate=" + joinDate + ", withdrawalDate=" + withdrawalDate + "]";
	}

}
