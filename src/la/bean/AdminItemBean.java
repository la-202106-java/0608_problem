package la.bean;

import java.io.Serializable;

public class AdminItemBean implements Serializable {

	private int code;
	private int category_code;
	private String name;
	private int price;

	public AdminItemBean(int code, int category_code, String name, int price) {
		super();
		this.code = code;
		this.category_code = category_code;
		this.name = name;
		this.price = price;
	}

	public int getCategory_code() {
		return category_code;
	}

	public void setCategory_code(int category_code) {
		this.category_code = category_code;
	}

	public AdminItemBean() {
		super();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
