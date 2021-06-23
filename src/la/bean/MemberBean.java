package la.bean;

import java.io.Serializable;
import java.time.LocalDate;

public class MemberBean implements Serializable {
	private int id;
	private String password;
	private String name;
	private String postalCode;
	private String address;
	private String tel;
	private String emailAddress;
	private String birthDate;
	private LocalDate joinDate;
	private LocalDate quiteDate;

	public MemberBean() {

	}

	public MemberBean(int id, String password, String name, String postalCode, String address, String tel,
			String emailAddress, String birthDate, LocalDate joinDate, LocalDate quiteDate) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.postalCode = postalCode;
		this.address = address;
		this.tel = tel;
		this.emailAddress = emailAddress;
		this.birthDate = birthDate;
		this.joinDate = joinDate;
		this.quiteDate = quiteDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public LocalDate getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}

	public LocalDate getQuiteDate() {
		return quiteDate;
	}

	public void setQuiteDate(LocalDate quiteDate) {
		this.quiteDate = quiteDate;
	}

}
