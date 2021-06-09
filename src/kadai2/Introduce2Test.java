package kadai2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Introduce2Test {

	@ParameterizedTest
	@CsvSource({
			"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa,aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaは20文字以上です",
	})
	void judgeTest(String na, String expected) {
		Introduce2 target = new Introduce2();
		target.judge(na);
		System.out.println(na);
		System.out.println(expected);
		assertEquals(expected, na);
	}

	void judgeTest2(int to, String expected) {
		Introduce2 target = new Introduce2();
		target.judge2(to);
		System.out.println(to);
		System.out.println(expected);
		assertEquals(expected, to);
	}

}
