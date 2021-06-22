package la.bean;

import java.util.Date;

public class Material {
	private String id;
	private String isbn;
	private Date stockDate;
	private String title;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Material(String id, String isbn, Date stockDate, String title) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.stockDate = stockDate;
		this.title = title;
	}

	public Material() {
	}

}
