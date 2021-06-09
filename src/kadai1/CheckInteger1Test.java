package kadai1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CheckInteger1Test {
	//どんなテストが必要か
	//境界値テスト
	//０
	//2
	//-2
	//1
	//-1
	@Test
	void test2() {
		CheckInteger1 target = new CheckInteger1();
		String result = target.judge(2);//2が偶数だったら"２は偶数です"と返却されてほしい
		assertEquals("2は偶数です", result);
	}

	@Test
	void test0() {
		CheckInteger1 target = new CheckInteger1();
		String result = target.judge(0);//0が偶数だったら"0は偶数です"と返却されてほしい
		assertEquals("0は偶数です", result);
	}

	@Test
	void test3() {
		CheckInteger1 target = new CheckInteger1();
		String result = target.judge(-2);//0が偶数だったら"0は偶数です"と返却されてほしい
		assertEquals("-2は偶数です", result);
	}

	@Test
	void test4() {
		CheckInteger1 target = new CheckInteger1();
		String result = target.judge(1);//0が偶数だったら"0は偶数です"と返却されてほしい
		assertEquals("1は奇数です", result);
	}

	@Test
	void test5() {
		CheckInteger1 target = new CheckInteger1();
		String result = target.judge(-1);//0が偶数だったら"0は偶数です"と返却されてほしい
		assertEquals("-1は奇数です", result);
	}

	@ParameterizedTest
	@CsvSource({
			"0,0は偶数です",
			"1,1は奇数です",
			"2,2は偶数です",
			"-1,-1は奇数です",
			"-2,-2は偶数です",
	})
	@ValueSource(ints = { 0, 1, 2, -1, -2 })
	void judgeTest(int number, String expected) {
		CheckInteger1 target = new CheckInteger1();
		String result = target.judge(number);//0が偶数だったら"0は偶数です"と返却されてほしい
		//System.out.println(number);
		//System.out.println(expected);
		//System.out.println("------------");
		//assertEquals(expected, number);
	}
}
