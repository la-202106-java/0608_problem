package la.bean;

import java.util.Date;

public class NowUserBean {

	private int id;
	private String name;
	private Date birth_date;
	private Date join_date;
	private String address;
	private String tel;
	private String email;

	public NowUserBean() {
	}

	public NowUserBean(int id, String name, Date birth_date, Date join_date, String address, String tel, String email) {
		super();
		this.id = id;
		this.name = name;
		this.birth_date = birth_date;
		this.join_date = join_date;
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
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public Date getJoin_date() {
		return join_date;
	}

	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
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
