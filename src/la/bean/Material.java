package la.bean;

import java.util.Date;

public class Material {
	private String material_id;
	private String isbn;
	private Date stockDate;
	private String title;

	public String getMaterial_id() {
		return material_id;
	}

	public void setMaterial_id(String material_id) {
		this.material_id = material_id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Date getStockDate() {
		return stockDate;
	}

	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Material(String material_id, String isbn, Date stockDate, String title) {
		super();
		this.material_id = material_id;
		this.isbn = isbn;
		this.stockDate = stockDate;
		this.title = title;
	}

	public Material() {
	}

}
