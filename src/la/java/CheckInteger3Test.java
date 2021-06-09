package la.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CheckInteger3Test {

	@ParameterizedTest
	@CsvSource({
			"0,0は正の偶数です。",
			"1,1は正の奇数です。",
			"2,2は正の偶数です。",
			"-1,-1は負の奇数です。",
			"-2,-2は負の偶数です。",
	})
	void judgetest(int number, String expected) {
		CheckInteger3 target = new CheckInteger3();
		String result = target.judge(number);
		assertEquals(expected, result);
	}

}
