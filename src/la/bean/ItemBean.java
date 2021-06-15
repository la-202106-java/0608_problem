package la.bean;

import java.io.Serializable;

public class ItemBean implements Serializable {
	private int code;
	private String name;
	private int price;
	private int category_code;

	public int getCategory_code() {
		return category_code;
	}

	public void setCategory_code(int category_code) {
		this.category_code = category_code;
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

	ItemBean() {
	}

	/**
	 * @param code
	 * @param name
	 * @param price
	 */
	public ItemBean(int code, String name, int price) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public ItemBean(int code, String name, int price, int category_code) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.category_code = category_code;
	}

}
