package la.bean;

import java.io.Serializable;

public class Omikujibean implements Serializable {

	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Omikujibean() {
		super();
	}

	public Omikujibean(String result) {
		super();
		this.result = result;
	}

}
