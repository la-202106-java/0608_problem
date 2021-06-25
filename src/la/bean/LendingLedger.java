package la.bean;

import java.util.Date;

public class LendingLedger {
	private int id;
	private int memberID;
	private Member member;
	private MaterialLedger materialLedger;
	private MaterialCatalog materialCatalog;

	private int materialID;
	private Date checkoutDate;
	private Date returnDeadline;
	private Date returnDate;

	private boolean isOut = false;

	public LendingLedger() {
		super();
		this.member = new Member();
		this.materialLedger = new MaterialLedger();
		this.materialCatalog = new MaterialCatalog();

	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public MaterialLedger getMaterialLedger() {
		return materialLedger;
	}

	public void setMaterialLedger(MaterialLedger materialLedger) {
		this.materialLedger = materialLedger;
	}

	public MaterialCatalog getMaterialCatalog() {
		return materialCatalog;
	}

	public void setMaterialCatalog(MaterialCatalog materialCatalog) {
		this.materialCatalog = materialCatalog;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMemeberID() {
		return memberID;
	}

	public void setMemeberID(int memeberID) {
		this.memberID = memeberID;
	}

	public int getMaterialID() {
		return materialID;
	}

	public void setMaterialID(int materialID) {
		this.materialID = materialID;
	}

	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public String getReturnDeadline() {
		if (returnDeadline.compareTo(new Date()) < 0 && returnDate == null) {
			System.out.println(returnDeadline);
			System.out.println(new Date());
			System.out.println(returnDeadline.compareTo(new Date()) >= 0);
			return "<div style='color:red;'>" + returnDeadline.toString() + "</div>";
		} else {

			return "<div>" + returnDeadline.toString() + "</div>";
		}
	}

	public boolean getIsOut() {
		if (returnDeadline.compareTo(new Date()) < 0 && returnDate == null) {
			return true;
		} else {
			return false;
		}

	}

	public void setReturnDeadline(Date returnDeadline) {
		this.returnDeadline = returnDeadline;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public LendingLedger(int id, int memeberID, int materialID, Date checkoutDate, Date returnDeadline,
			Date returnDate) {
		super();
		this.id = id;
		this.memberID = memeberID;
		this.materialID = materialID;
		this.checkoutDate = checkoutDate;
		this.returnDeadline = returnDeadline;
		this.returnDate = returnDate;
	}

}
