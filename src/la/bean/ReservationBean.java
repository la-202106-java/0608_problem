package la.bean;

import java.io.Serializable;
import java.time.LocalDate;

public class ReservationBean implements Serializable {
	private int id;
	private int memberId;
	private int planId;
	private LocalDate date;
	private String inDate;
	private String outDate;
	private int room;
	private boolean cancelCheck;
	private String note;

	public ReservationBean() {

	}

	public ReservationBean(int id, int memberId, int planId, LocalDate date, String inDate, String outDate, int room,
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
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
