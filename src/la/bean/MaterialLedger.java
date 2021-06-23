package la.bean;

import java.util.Date;

public class MaterialLedger {
	private int id;
	private String isbn;
	private Date stockDate;
	private Date disposalDate;
	private String remark;

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

	public Date getStockDate() {
		return stockDate;
	}

	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}

	public Date getDisposalDate() {
		return disposalDate;
	}

	public void setDisposalDate(Date disposalDate) {
		this.disposalDate = disposalDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public MaterialLedger(int id, String isbn, Date stockDate, Date disposalDate, String remark) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.stockDate = stockDate;
		this.disposalDate = disposalDate;
		this.remark = remark;
	}

	public MaterialLedger() {
	}
}
