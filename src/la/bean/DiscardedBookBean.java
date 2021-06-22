package la.bean;

import java.util.Date;

public class DiscardedBookBean {
	private int id;
	private String isbn;
	private String title;
	private Date arrivalDate;
	private Date discardDate;
	private String note;

	public DiscardedBookBean() {
	}

	public DiscardedBookBean(int id, String isbn, String title, Date arrivalDate, Date discardDate, String note) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.arrivalDate = arrivalDate;
		this.discardDate = discardDate;
		this.note = note;
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

	public Date getDiscardDate() {
		return discardDate;
	}

	public void setDiscardDate(Date discardDate) {
		this.discardDate = discardDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
