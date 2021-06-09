package kadai1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CheckInteger2Test {

	@ParameterizedTest
	@CsvSource({
			"0,0は正の偶数です",
			"1,1は正の奇数です",
			"2,2は正の偶数です",
			"-1,-1は負の奇数です",
			"-2,-2は負の偶数です",
	})
	//@ValueSource(ints = { 0, 1, 2, -1, -2 })
	void judgeTest(int number, String expected) {
		CheckInteger1 target = new CheckInteger1();
		String result = target.judge(number);
	}
}
