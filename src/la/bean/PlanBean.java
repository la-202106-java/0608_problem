package la.bean;

import java.io.Serializable;
import java.time.LocalDate;

public class PlanBean implements Serializable {
	private int planId;
	private int innId;
	private String content;
	private int fee;
	private int roomMax;
	private String imgUrl;
	private LocalDate deleteDate;
	private String note;
	private InnBean inn;

	public PlanBean() {

	}

	public PlanBean(int innId, String content, int fee, int roomMax, String imgUrl) {
		this.innId = innId;
		this.content = content;
		this.fee = fee;
		this.roomMax = roomMax;
		this.imgUrl = imgUrl;
	}

	public PlanBean(int planId, int innId, String content, int fee, int roomMax, String imgUrl, LocalDate deleteDate,
			String note, InnBean inn) {
		super();
		this.planId = planId;
		this.innId = innId;
		this.content = content;
		this.fee = fee;
		this.roomMax = roomMax;
		this.imgUrl = imgUrl;
		this.deleteDate = deleteDate;
		this.note = note;
		this.inn = inn;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getInnID() {
		return innId;
	}

	public void setInnID(int innID) {
		this.innId = innID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public int getRoomMax() {
		return roomMax;
	}

	public void setRoomMax(int roomMax) {
		this.roomMax = roomMax;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public LocalDate getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(LocalDate deleteDate) {
		this.deleteDate = deleteDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public InnBean getInn() {
		return inn;
	}

	public void setInn(InnBean inn) {
		this.inn = inn;
	}
}
