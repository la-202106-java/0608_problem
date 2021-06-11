package la.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

//パラメタライズドテスト
//一つのテストメソッドに入力と期待値などをまとめて記述できる機能

class Sample1Test {

	@AfterEach
	void tearDown() throws Exception {
	}

	//どんなテストが必要かな？
	//[境界値テスト]
	///0 ,2, -2
	//1,-1
	@Test
	void test1() {
		Sample1 target = new Sample1();
		String result = target.judge(2); //２が偶数だったら"2は偶数です"と返却されてほしい
		assertEquals("2は偶数です", result);

		//fail("まだ実装されていません");
	}

	@Test
	void test2() {
		Sample1 target = new Sample1();
		String result = target.judge(0); //0が偶数だったら"0は偶数です"と返却されてほしい
		assertEquals("0は偶数です", result);

		//fail("まだ実装されていません");
	}

	@Test
	void test3() {
		Sample1 target = new Sample1();
		String result = target.judge(-2); //-2が偶数だったら"-2は偶数です"と返却されてほしい
		assertEquals("-2は偶数です", result);

		//fail("まだ実装されていません");
	}

	@Test
	void test4() {
		Sample1 target = new Sample1();
		String result = target.judge(1); //1が奇数だったら"1は奇数です"と返却されてほしい
		assertEquals("1は奇数です", result);

		//fail("まだ実装されていません");
	}

	@Test
	void test5() {
		Sample1 target = new Sample1();
		String result = target.judge(-1); //1が奇数だったら"1は奇数です"と返却されてほしい
		assertEquals("-1は奇数です", result);

		//fail("まだ実装されていません");
	}

	@ParameterizedTest
	@CsvSource({
			"0, 0は偶数です",
			"1, 1は奇数です",
			"2, 2は偶数です",
			"-1, -1は奇数です",
			"-2, -2は偶数です",
	})
	//@ValueSource(ints = { 0, 1, 2, -1, -2 })
	void judgeTest(int number, String expected) {
		Sample1 target = new Sample1();
		String result = target.judge(number); //1が奇数だったら"1は奇数です"と返却されてほしい
		//		System.out.println(result);
		//		System.out.println(number);
		//		System.out.println(expected);
		//		System.out.println("------------");
		assertEquals(expected, result);
	}
}
