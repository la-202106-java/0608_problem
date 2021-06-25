package black.bean;

import java.io.Serializable;
import java.sql.Date;

public class ListedItemBean implements Serializable {
	private int id;
	private String isbn;
	private String title;
	private int departmentCode;
	private String author;
	private int price;
	private String condition;

	private int sellerId;
	private Date orderdDate = null;
	private int buyerId = -1;

	private boolean inStock = true;

	public ListedItemBean(int id, String isbn, String title, int departmentCode, String author, int price,
			String condition, int sellerId) {
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.departmentCode = departmentCode;
		this.author = author;
		this.price = price;
		this.condition = condition;
		this.sellerId = sellerId;
	}

	public ListedItemBean(int id, String isbn, String title, int departmentCode, String author, int price,
			String condition, int sellerId, Date orderdDate, int buyerId) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.departmentCode = departmentCode;
		this.author = author;
		this.price = price;
		this.condition = condition;
		this.sellerId = sellerId;
		this.orderdDate = orderdDate;
		this.buyerId = buyerId;
	}

	public ListedItemBean() {
		super();
	}

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn セットする isbn
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title セットする title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return departmentCode
	 */
	public int getDepartmentCode() {
		return departmentCode;
	}

	/**
	 * @param departmentCode セットする departmentCode
	 */
	public void setDepartmentCode(int departmentCode) {
		this.departmentCode = departmentCode;
	}

	/**
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author セットする author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price セットする price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * @param condition セットする condition
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * @return sellerId
	 */
	public int getSellerId() {
		return sellerId;
	}

	/**
	 * @param sellerId セットする sellerId
	 */
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	/**
	 * @return orderdDate
	 */
	public Date getOrderdDate() {
		return orderdDate;
	}

	/**
	 * @param orderdDate セットする orderdDate
	 */
	public void setOrderdDate(Date orderdDate) {
		this.orderdDate = orderdDate;
	}

	/**
	 * @return buyerId
	 */
	public int getBuyerId() {
		return buyerId;
	}

	/**
	 * @param buyerId セットする buyerId
	 */
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock() {
		if (orderdDate == null && buyerId <= 0) {
			inStock = true;
		} else {
			inStock = false;
		}
	}
}
