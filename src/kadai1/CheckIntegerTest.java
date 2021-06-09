package kadai1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CheckIntegerTest {

	@ParameterizedTest
	@CsvSource({
			"0,0は正の偶数です",
			"1,1は正の奇数です",
			"2,2は正の偶数です",
			"-1,-1は負の奇数です",
			"-2,-2は負の偶数です",
	})
	void judgeTest(int number, String expected) {
		CheckInteger target = new CheckInteger();
		String result = target.judgeInput(number);
		assertEquals(expected, result);
	}
}
