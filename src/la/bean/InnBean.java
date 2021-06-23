package la.bean;

import java.io.Serializable;
import java.time.LocalDate;

public class InnBean implements Serializable {
	private int id;
	private String name;
	private int classCode;
	private String postalCode;
	private String address;
	private String inTime;
	private String outTime;
	private LocalDate date;
	private String note;

	public InnBean() {

	}

	public InnBean(String name, int classCode, String postalCode, String address, String inTime, String outTime) {
		this.name = name;
		this.classCode = classCode;
		this.postalCode = postalCode;
		this.address = address;
		this.inTime = inTime;
		this.outTime = outTime;
	}

	public InnBean(int id, String name, int classCode, String postalCode, String address, String inTime, String outTime,
			LocalDate date, String note) {
		super();
		this.id = id;
		this.name = name;
		this.classCode = classCode;
		this.postalCode = postalCode;
		this.address = address;
		this.inTime = inTime;
		this.outTime = outTime;
		this.date = date;
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getClassCode() {
		return classCode;
	}

	public void setClassCode(int classCode) {
		this.classCode = classCode;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
