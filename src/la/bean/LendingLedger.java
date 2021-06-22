package la.bean;

import java.util.Date;

public class LendingLedger {
	private int id;
	private int memeberID;
	private int materialID;
	private Date checkoutDate;
	private Date returnDeadline;
	private Date returnDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMemeberID() {
		return memeberID;
	}

	public void setMemeberID(int memeberID) {
		this.memeberID = memeberID;
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

	public Date getReturnDeadline() {
		return returnDeadline;
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
		this.memeberID = memeberID;
		this.materialID = materialID;
		this.checkoutDate = checkoutDate;
		this.returnDeadline = returnDeadline;
		this.returnDate = returnDate;
	}

}
