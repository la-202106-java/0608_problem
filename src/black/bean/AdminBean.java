package black.bean;

import java.io.Serializable;

public class AdminBean implements Serializable {
	private int id;
	private String email;
	private String pass;

	public AdminBean(int id, String email, String pass) {
		this.id = id;
		this.email = email;
		this.pass = pass;
	}

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email セットする email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass セットする pass
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

}
