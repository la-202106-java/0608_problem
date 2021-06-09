package kadai1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CheckInteger3Test {

	// 奇数・偶数の判定
	// 正・負の判定
	// 3回繰り返す

	@ParameterizedTest
	@CsvSource({
			"0, true",
			"1, true",
			"-1, false",
	})
	void testPositiveNegative(int num, boolean judge) {
		assertEquals(judge, CheckInteger3.isPositiveNegative(num));
	}

	@ParameterizedTest
	@CsvSource({
			"0, true",
			"1, false",
			"2, true",
			"-1, false",
			"-2, true",
	})
	void testOddEven(int num, boolean judge) {
		assertEquals(judge, CheckInteger3.isOddEven(num));
	}

	@Test
	void testStrPN1() {
		String str = "";
		str = CheckInteger3.strPositiveNegative(str, true);
		assertEquals(str, "正の");
	}

	@Test
	void testStrPN2() {
		String str = "";
		str = CheckInteger3.strPositiveNegative(str, false);
		assertEquals(str, "負の");
	}

	@Test
	void testStrOE1() {
		String str = "";
		str = CheckInteger3.strOddEven(str, true);
		assertEquals(str, "偶数です");
	}

	@Test
	void testStrOE2() {
		String str = "";
		str = CheckInteger3.strOddEven(str, false);
		assertEquals(str, "奇数です");
	}

}
