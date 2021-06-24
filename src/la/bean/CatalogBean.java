package la.bean;

import java.util.Date;

public class CatalogBean {
	private String isbn;
	private String title;
	private int code;
	private String auther;
	private String publisher;
	private Date publicationDate;
	private int stockCount; //在庫数

	public CatalogBean() {
	}

	public CatalogBean(String isbn, String title, int code, String auther, String publisher, Date publicationDate) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.code = code;
		this.auther = auther;
		this.publisher = publisher;
		this.publicationDate = publicationDate;
	}

	public CatalogBean(String isbn, String title, int stockCount) {
		this.isbn = isbn;
		this.title = title;
		this.stockCount = stockCount;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getAuther() {
		return auther;
	}

	public void setAuther(String auther) {
		this.auther = auther;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public int getStockCount() {
		return stockCount;
	}

	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}

}
