package la.bean;

import java.io.Serializable;

public class FortuneBean implements Serializable {
	private int month;
	private String color;
	private String item;
	private int rank;

	public FortuneBean() {
	}

	public FortuneBean(int month, String color, String item, int rank) {
		super();
		this.month = month;
		this.color = color;
		this.item = item;
		this.rank = rank;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

}
