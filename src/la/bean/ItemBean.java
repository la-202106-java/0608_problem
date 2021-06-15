package la.bean;

import java.io.Serializable;

public class ItemBean implements Serializable {
	private int code;
	private String name;
	private int price;

	public ItemBean(int code, String name, int price) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.code = code;
		this.name = name;
		this.price = price;
	}

	/**
	 * @return code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code セットする code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price セットする price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

}
