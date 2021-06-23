package la.bean;

import java.util.Date;

public class LendingBean {
	private int id;
	private int bookId;
	private int userId;
	private Date lendingDate;
	private Date deadline;
	private String note;

	public LendingBean() {
	}

	public LendingBean(int id, int bookId, int userId, Date lendingDate, Date deadline, String note) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.userId = userId;
		this.lendingDate = lendingDate;
		this.deadline = deadline;
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getLendingDate() {
		return lendingDate;
	}

	public void setLendingDate(Date lendingDate) {
		this.lendingDate = lendingDate;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
