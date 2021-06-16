package la.bean;

import java.io.Serializable;

public class ShopItemBean implements Serializable {
	private int code;
	private String name;
	private int price;
	private int quantity;

	public ShopItemBean(int code, String name, int price) {
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public ShopItemBean(int code, String name, int price, int quantity) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public ShopItemBean() {

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
