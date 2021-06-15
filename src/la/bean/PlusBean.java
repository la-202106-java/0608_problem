package la.bean;

import java.io.Serializable;

public class PlusBean implements Serializable {
	/**
	 * @return value1
	 */
	public int getValue1() {
		return value1;
	}

	/**
	 * @param value1 セットする value1
	 */
	public void setValue1(int value1) {
		this.value1 = value1;
	}

	/**
	 * @return value2
	 */
	public int getValue2() {
		return value2;
	}

	/**
	 * @param value2 セットする value2
	 */
	public void setValue2(int value2) {
		this.value2 = value2;
	}

	/**
	 * @return answer
	 */
	public int getAnswer() {
		return answer;
	}

	/**
	 * @param answer セットする answer
	 */
	public void setAnswer(int answer) {
		this.answer = answer;
	}

	private int value1;
	private int value2;
	private int answer;

	public PlusBean() {
		// TODO 自動生成されたコンストラクター・スタブ

	}

	public PlusBean(int value1, int value2, int answer) {
		super();
		this.value1 = value1;
		this.value2 = value2;
		this.answer = answer;
	}

}
