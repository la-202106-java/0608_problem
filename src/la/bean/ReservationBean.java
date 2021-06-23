package la.bean;

import java.time.LocalDateTime;
import java.util.Date;

public class ReservationBean {
	private int id;
	private String isbn;
	private int userId;
	private LocalDateTime reservationTime;
	private Date reservedDate;

	public ReservationBean() {

	}

	public ReservationBean(int id, String isbn, int userId, LocalDateTime reservationTime, Date reservedDate) {
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

	public LocalDateTime getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(LocalDateTime reservationTime) {
		this.reservationTime = reservationTime;
	}

	public Date getReservedDate() {
		return reservedDate;
	}

	public void setReservedDate(Date reservedDate) {
		this.reservedDate = reservedDate;
	}

}
