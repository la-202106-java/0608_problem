package kasai.bean;

import java.io.Serializable;

public class BbsBean implements Serializable {

	/**
	 * @return message
	 */
	public int getMessage() {
		return message;
	}

	/**
	 * @param message セットする message
	 */
	public void setMessage(int message) {
		this.message = message;
	}

	private int message;

	public BbsBean() {
		// TODO 自動生成されたコンストラクター・スタブ

	}

	public BbsBean(int message) {
		super();
		this.message = message;
	}
}
