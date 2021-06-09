package kadai2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Introduce1Test {

	@ParameterizedTest
	@CsvSource({
			"0, 10",
			"15, 25",
	})
	void judgeTest(int age, int expected) {
		assertEquals(expected, Introduce2.addAge(age));
	}

	// 例外クラスを作ってmessageをassertEquals
	// IllegalArgumentException
	@Test
	void nameErrorTest() {
		try {
			Introduce2 introduce = new Introduce2();
			introduce.checkName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		} catch (IllegalArgumentException e) {
			assertEquals("名前は20文字以内で入力してください", e.getMessage());
		}
	}

	@Test
	void ageErrorTest() {
		try {
			Introduce2 introduce = new Introduce2();
			introduce.checkAge(900);
		} catch (IllegalArgumentException e) {
			assertEquals("年齢は0以上、130以下で入力してください", e.getMessage());
		}
	}
}
