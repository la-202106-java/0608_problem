package kadai2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Introducer2Test {

	@Test
	void testName() {
		String test_name = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		assertTrue(Introducer2.judgeName(test_name), test_name + "の長さは20以下です。 ");

	}

	@ParameterizedTest
	@ValueSource(ints = { 5, 0, 130 })
	void testAge1(int age) {
		assertTrue(Introducer2.judgeAge(age), age + "は0以上130以下です。 ");
	}

	@ParameterizedTest
	@ValueSource(ints = { -1, 131 })
	void testAge2(int age) {
		assertFalse(Introducer2.judgeAge(age), age + "は範囲外です。 ");
	}

}
