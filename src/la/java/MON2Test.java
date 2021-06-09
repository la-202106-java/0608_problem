package la.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MON2Test {

	@AfterEach
	void tearDown() throws Exception {
	}

	@ParameterizedTest
	@CsvSource({
			"石田, あなたの名前の文字数は：2",
			"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa, 名前は20字以内で入力してください",
	})
	void judgeTest(String name, String expected) {
		MON2 target = new MON2();
		String result = target.judge(name);
		assertEquals(expected, result);
	}

	@ParameterizedTest
	@CsvSource({
			"-1, 年齢は0以上、130以下で入力してください",
			"1, 貴方の年齢は：1",
			"129, 貴方の年齢は：129",
			"131, 年齢は0以上、130以下で入力してください",
	})
	void judgeTest2(int old, String expected) {
		MON2 target = new MON2();
		String result = target.judge2(old);
		assertEquals(expected, result);
	}
}
