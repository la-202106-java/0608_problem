package black.bean;

import java.io.Serializable;
import java.sql.Date;

public class MemberBean implements Serializable {
	private int id;
	private String name;
	private String address;
	private String tel;
	private String email;
	private Date birthday;
	private Date joinDate;
	private Date quitDate = null;
	private String pass;
	private int sales = 0;

	public MemberBean(int id, String name, String address, String tel, String email,
			Date birthday, Date joinDate, String pass) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.birthday = birthday;
		this.joinDate = joinDate;
		this.pass = pass;
	}

	public MemberBean(String name, String address, String tel, String email, Date birthday, String pass) {
		super();
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.birthday = birthday;
		this.pass = pass;
	}

	public MemberBean() {

	}

	public MemberBean(int id, String email, String pass) {
		// TODO 自動生成されたコンストラクター・スタブ
		super();
		this.id = id;
		this.email = email;
		this.pass = pass;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

}
