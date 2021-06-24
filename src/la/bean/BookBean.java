package la.bean;

import java.util.Date;

public class BookBean {

	private int id;
	private String isbn;
	private String title;
	private Date arrivalDate;
	private String note;

	public BookBean() {
	}

	public BookBean(int id, String isbn, String title, Date arrivalDate, String note) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.arrivalDate = arrivalDate;
		this.note = note;
	}

	public BookBean(String isbn, String title, Date arrivalDate) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.arrivalDate = arrivalDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
