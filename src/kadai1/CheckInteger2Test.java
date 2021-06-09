package kadai1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CheckInteger2Test {

	//正負判断処理
	@Test
	void testMinus1() {
		CheckInteger2 target = new CheckInteger2();
		String result = target.judgeMinus(0);
		assertEquals("0は正の", result);
	}

	@Test
	void testMinus2() {
		CheckInteger2 target = new CheckInteger2();
		String result = target.judgeMinus(1);
		assertEquals("1は正の", result);
	}

	@Test
	void testMinus3() {
		CheckInteger2 target = new CheckInteger2();
		String result = target.judgeMinus(-1);
		assertEquals("-1は負の", result);
	}

	//奇数偶数判断処理
	@Test
	void testNum1() {
		CheckInteger2 target = new CheckInteger2();
		String result = target.judgeNum(0);
		assertEquals("偶数です", result);
	}

	@Test
	void testNum2() {
		CheckInteger2 target = new CheckInteger2();
		String result = target.judgeNum(1);
		assertEquals("奇数です", result);
	}

	@Test
	void testNum3() {
		CheckInteger2 target = new CheckInteger2();
		String result = target.judgeNum(2);
		assertEquals("偶数です", result);
	}

	@Test
	void testNum4() {
		CheckInteger2 target = new CheckInteger2();
		String result = target.judgeNum(-1);
		assertEquals("奇数です", result);
	}

	@Test
	void testNum5() {
		CheckInteger2 target = new CheckInteger2();
		String result = target.judgeNum(-2);
		assertEquals("偶数です", result);
	}

}
