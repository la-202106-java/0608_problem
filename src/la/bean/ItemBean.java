package la.bean;

import java.io.Serializable;

public class ItemBean implements Serializable {
	private int code;
	private int category;
	private String name;
	private int price;

	public ItemBean(int code, String name, int price) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public ItemBean(int code, int category, String name, int price) {
		super();
		this.code = code;
		this.category = category;
		this.name = name;
		this.price = price;
	}

	public ItemBean() {
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
