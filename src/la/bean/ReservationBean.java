package la.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class ReservationBean {
	private int id;
	private String isbn;
	private int userId;
	private Timestamp reservationTime;
	private Date reservedDate;

	public ReservationBean() {

	}

	public ReservationBean(int id, String isbn, int userId, Timestamp reservationTime, Date reservedDate) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.userId = userId;
		this.reservationTime = reservationTime;
		this.reservedDate = reservedDate;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Timestamp getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(Timestamp reservationTime) {
		this.reservationTime = reservationTime;
	}

	public Date getReservedDate() {
		return reservedDate;
	}

	public void setReservedDate(Date reservedDate) {
		this.reservedDate = reservedDate;
	}

}
