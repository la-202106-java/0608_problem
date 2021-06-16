package la.bean;

public class ItemBean implements Comparable<ItemBean> {
	private int code;
	private String name;
	private int price;
	//	private int quantity;
	private int category;

	public int getCode() {
		return code;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
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

	public ItemBean(int code, String name, int price, int category_code) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.category = category_code;
	}

	public ItemBean(int code, String name, int price) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public ItemBean(String name, int price, int category) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
	}

	@Override
	public int compareTo(ItemBean o) {
		if (this.price < o.price) {
			return -1;
		} else if (this.price > o.price) {
			return 1;
		} else {
			return 0;
		}
	}

}
