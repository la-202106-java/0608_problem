package la.bean;

import java.io.Serializable;

public class ItemBean2 implements Serializable {
	private int code;
	private String name;
	private int price;
	private int category_code;

	public ItemBean2(int code, String name, int price, int category_code) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.code = code;
		this.name = name;
		this.price = price;
		this.category_code = category_code;
	}

	/**
	 * @return cotegory_code
	 */
	public int getCategory_code() {
		return category_code;
	}

	public void setCategory_code(int category_code) {
		this.category_code = category_code;
	}

	public ItemBean2() {

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
