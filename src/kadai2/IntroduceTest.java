package kadai2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IntroduceTest {

	@Test
	void test2() {
		Introduce target = new Introduce();
		boolean result = target.judgeName("じゅげむ じゅげむ ごこうのすりきれ かいじゃりすいぎょの");
		assertEquals(true, result);
	}

	@Test
	void test1() {
		Introduce target = new Introduce();
		String result = target.judgeAge("田中", 25, false);
		assertEquals("田中さんの10年後は35歳です", result);
	}

	@Test
	void test3() {
		Introduce target = new Introduce();
		String result = target.judgeAge("田中", -30, false);
		assertEquals("年齢は0以上、130以下で入力してください", result);
	}

	@Test
	void test4() {
		Introduce target = new Introduce();
		String result = target.judgeAge("田中", 140, false);
		assertEquals("年齢は0以上、130以下で入力してください", result);
	}

	@Test
	void test5() {
		Introduce target = new Introduce();
		String result = target.judgeAge("田中", 25, true);
		assertEquals("こうはならない", result);
	}

	@Test
	void test6() {
		Introduce target = new Introduce();
		String result = target.judgeAge("田中", -30, true);
		assertEquals("こうはならない", result);
	}

	@Test
	void test7() {
		Introduce target = new Introduce();
		String result = target.judgeAge("田中", 140, true);
		assertEquals("こうはならない", result);
	}

}
