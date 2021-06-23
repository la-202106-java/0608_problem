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

	public NowUserBean(String name, Date birthDate, Date joinDate, String address, String tel, String email) {
		super();
		this.name = name;
		this.birthDate = birthDate;
		this.joinDate = joinDate;
		this.address = address;
		this.tel = tel;
		this.email = email;
	}

	public NowUserBean(int id, String name, Date birthDate, Date joinDate, String address, String tel, String email) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.joinDate = joinDate;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
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
