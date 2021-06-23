package la.bean;

import java.io.Serializable;
import java.sql.Date;

public class PlanBean implements Serializable {
	private int planId;
	private int innID;
	private String content;
	private int fee;
	private int roomMax;
	private String imgUrl;
	private Date deleteDate;
	private String note;
	private InnBean inn;

	public PlanBean() {

	}

	public PlanBean(int innID, String content, int fee, int roomMax, String imgUrl) {
		this.innID = innID;
		this.content = content;
		this.fee = fee;
		this.roomMax = roomMax;
		this.imgUrl = imgUrl;
	}

	public PlanBean(int planId, int innID, String content, int fee, int roomMax, String imgUrl, Date deleteDate,
			String note, InnBean inn) {
		super();
		this.planId = planId;
		this.innID = innID;
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
		return innID;
	}

	public void setInnID(int innID) {
		this.innID = innID;
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

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
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
