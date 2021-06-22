package la.bean;

import java.util.Date;

public class MaterialCatalog {

	private String isbn;
	private String title;
	private int categoryCode;
	private String authur;
	private String publisher;
	private Date publicationDate;

	public MaterialCatalog() {
		super();
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public MaterialCatalog(String isbn, String title, int categoryCode, String authur, String publisher,
			Date publicationDate) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.categoryCode = categoryCode;
		this.authur = authur;
		this.publisher = publisher;
		this.publicationDate = publicationDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getAuthur() {
		return authur;
	}

	public void setAuthur(String authur) {
		this.authur = authur;
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
}
