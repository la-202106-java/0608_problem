package kadai1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CheckInteger1Test {

	//境界値：0, 2, -2, 1, -1
	@Test
	void test1() {
		CheckInteger1 target = new CheckInteger1();
		String result = target.judge(2);
		assertEquals("2は偶数です", result);
	}

	@Test
	void test2() {
		CheckInteger1 target = new CheckInteger1();
		String result = target.judge(0);
		assertEquals("0は偶数です", result);
	}

	@Test
	void test3() {
		CheckInteger1 target = new CheckInteger1();
		String result = target.judge(-2);
		assertEquals("-2は偶数です", result);
	}

	@Test
	void test4() {
		CheckInteger1 target = new CheckInteger1();
		String result = target.judge(1);
		assertEquals("1は奇数です", result);
	}

	@Test
	void test5() {
		CheckInteger1 target = new CheckInteger1();
		String result = target.judge(-1);
		assertEquals("-1は奇数です", result);
	}

	@ParameterizedTest
	@CsvSource({
			"0, 0は偶数です",
			"1, 1は奇数です",
			"2, 2は偶数です",
			"-1, -1は奇数です",
			"-2, -2は偶数です",
	})
	void judgeTest(int number, String expected) {
		CheckInteger1 target = new CheckInteger1();
		String result = target.judge(number);
		assertEquals(expected, result);
	}

}
