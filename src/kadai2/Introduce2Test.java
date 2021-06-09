package kadai2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Introduce2Test {

	@ParameterizedTest
	@CsvSource({
			"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa,true",
			"aaaaaa,false",
	})
	void judgeNameTest(String name, boolean expected) {
		assertEquals(expected, Introduce2.judgeName(name));
	}

	@ParameterizedTest
	@CsvSource({
			"-20,true",
			"20,false",
	})
	void judgeAgeTest(int age, boolean expected) {
		assertEquals(expected, Introduce2.judgeAge(age));
	}

}
