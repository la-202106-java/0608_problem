package la.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Introduce0609Test {

	@ParameterizedTest
	@CsvSource({
			"田中, 25, 田中さんの10年後は35歳です",
			"A, -60, Aさんの10年後は-50歳です"
	})
	void test(String name, int age, String expected) {
		Introduce0609 introduce = new Introduce0609();
		String result = introduce.futureAge(name, age);
		assertEquals(expected, result);

	}

	@ParameterizedTest
	@CsvSource({
			"田中, true",
			"AAAAAAAAAAAAAAAAAAAAA, false",
			"20202020202020202020, true"
	})
	void test2(String name, boolean expected) {
		Introduce0609 introduce = new Introduce0609();
		boolean result = introduce.checkName(name);
		assertEquals(expected, result);
	}

	@ParameterizedTest
	@CsvSource({
			"0, true",
			"-1, false",
			"1, true",
			"130, true",
			"131, false"
	})
	void test3(int age, boolean expected) {
		Introduce0609 introduce = new Introduce0609();
		boolean result = introduce.checkAge(age);
		assertEquals(expected, result);
	}

}
