package la.bean;

import java.sql.Date;

public class ReservationBean {
	private int id;
	private int memberId;
	private int planId;
	private Date date;
	private Date inDate;
	private Date outDate;
	private int room;
	private boolean cancelCheck;
	private String note;

	public ReservationBean() {

	}

	public ReservationBean(int id, int memberId, int planId, Date date, Date inDate, Date outDate, int room,
			boolean cancelCheck, String note) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.planId = planId;
		this.date = date;
		this.inDate = inDate;
		this.outDate = outDate;
		this.room = room;
		this.cancelCheck = cancelCheck;
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public boolean isCancelCheck() {
		return cancelCheck;
	}

	public void setCancelCheck(boolean cancelCheck) {
		this.cancelCheck = cancelCheck;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
