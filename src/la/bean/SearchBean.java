package la.bean;

import java.io.Serializable;

public class SearchBean implements Serializable {
	private String productName;
	private int minPrice;
	private int maxPrice;

	public SearchBean(String productName, int minPrice, int maxPrice) {
		this.productName = productName;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	public SearchBean() {
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

}
