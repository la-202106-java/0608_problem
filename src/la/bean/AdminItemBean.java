package la.bean;

import java.io.Serializable;

public class AdminItemBean implements Serializable {
	private int code;
	private int categoryCode;
	private String name;
	private int price;

	public AdminItemBean(int code, int categoryCode, String name, int price) {
		super();
		this.code = code;
		this.categoryCode = categoryCode;
		this.name = name;
		this.price = price;
	}

	public AdminItemBean() {

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

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
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
