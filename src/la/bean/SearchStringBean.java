package la.bean;

import java.io.Serializable;

public class SearchStringBean implements Serializable {
	private String productName;
	private String minPrice;
	private String maxPrice;

	public SearchStringBean(String productName, String minPrice, String maxPrice) {
		this.productName = productName;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	public SearchStringBean() {
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
}
