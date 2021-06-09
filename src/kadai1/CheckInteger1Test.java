package kadai1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

//パラメタライズドテスト
//1つのテストメソッドに入力をまとめて記述できる
class CheckInteger1Test {

	//境界値テスト
	//0
	//2
	//-2
	//1
	//-1

	@Test
	void test0() {
		CheckInteger1 target = new CheckInteger1();
		String result = target.checkNumber(0);
		assertEquals("0は偶数です", result);
		//		fail("まだ実装されていません");
	}

	@Test
	void test2() {
		CheckInteger1 target = new CheckInteger1();
		String result = target.checkNumber(2);
		assertEquals("2は偶数です", result);
		//		fail("まだ実装されていません");
	}

	@Test
	void test_2() {
		CheckInteger1 target = new CheckInteger1();
		String result = target.checkNumber(-2);
		assertEquals("-2は偶数です", result);
		//		fail("まだ実装されていません");
	}

	@Test
	void test1() {
		CheckInteger1 target = new CheckInteger1();
		String result = target.checkNumber(1);
		assertEquals("1は奇数です", result);
		//		fail("まだ実装されていません");
	}

	@Test
	void test_1() {
		CheckInteger1 target = new CheckInteger1();
		String result = target.checkNumber(-1);
		assertEquals("-1は奇数です", result);
		//		fail("まだ実装されていません");
	}

	@ParameterizedTest
	@CsvSource({
			"0,0は偶数です",
			"1,1は奇数です",
			"2,2は偶数です",
			"-1,-1は奇数です",
			"-2,-2は偶数です",
	})
	//	@ValueSource(ints = { 0, 1, 2, -1, -2 })
	void checkNumberTest(int num, String expected) {
		CheckInteger1 target = new CheckInteger1();
		String result = target.checkNumber(num);
		assertEquals(expected, result);
	}
}
