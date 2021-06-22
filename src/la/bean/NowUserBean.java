package la.bean;

import java.util.Date;

public class NowUserBean {

	private int id;
	private String name;
	private Date birthDate;
	private Date joinDate;
	private String address;
	private String tel;
	private String email;

	public NowUserBean() {
	}

	public NowUserBean(int id, String name, Date birth_date, Date join_date, String address, String tel, String email) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birth_date;
		this.joinDate = join_date;
		this.address = address;
		this.tel = tel;
		this.email = email;
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

	public Date getBirth_date() {
		return birthDate;
	}

	public void setBirth_date(Date birth_date) {
		this.birthDate = birth_date;
	}

	public Date getJoin_date() {
		return joinDate;
	}

	public void setJoin_date(Date join_date) {
		this.joinDate = join_date;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
