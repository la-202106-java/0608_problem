package la.bean;

import java.sql.Date;

public class ReservedBean {

	private int id;
	private int userID;
	private int bookID;
	private Date reservedDate;
	private Date lendingDate;

	public ReservedBean() {
	}

	public ReservedBean(int id, int userID, int bookID, Date reservedDate, Date lendingDate) {
		super();
		this.id = id;
		this.userID = userID;
		this.bookID = bookID;
		this.reservedDate = reservedDate;
		this.lendingDate = lendingDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public Date getReservedDate() {
		return reservedDate;
	}

	public void setReservedDate(Date reservedDate) {
		this.reservedDate = reservedDate;
	}

	public Date getLendingDate() {
		return lendingDate;
	}

	public void setLendingDate(Date lendingDate) {
		this.lendingDate = lendingDate;
	}

}
