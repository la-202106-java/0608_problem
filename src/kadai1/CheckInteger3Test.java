package kadai1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CheckInteger3Test {

	@Test
	void test0() {
		CheckInteger3 target = new CheckInteger3();
		String result = target.judge(0);
		assertEquals("0は正の偶数です", result);
	}

	@Test
	void test1() {
		CheckInteger3 target = new CheckInteger3();
		String result = target.judge(1);
		assertEquals("1は正の奇数です", result);
	}

	@Test
	void test2() {
		CheckInteger3 target = new CheckInteger3();
		String result = target.judge(-1);
		assertEquals("-1は負の奇数です", result);
	}

	@Test
	void test3() {
		CheckInteger3 target = new CheckInteger3();
		String result = target.judge(2);
		assertEquals("2は正の偶数です", result);
	}

	@Test
	void test4() {
		CheckInteger3 target = new CheckInteger3();
		String result = target.judge(-2);
		assertEquals("-2は負の偶数です", result);
	}

}
