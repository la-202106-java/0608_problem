package kadai1_0609;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CheckInteger2_0609Test {

	//パラメタライズドテスト
	//一つのテストメソッドに入力と期待値などをまとめて記述できる機能
	@ParameterizedTest
	@CsvSource({
			"2,2は正の偶数です",
			"-2,-2は負の偶数です",
			"1,1は正の奇数です",
			"-1,-1は負の奇数です",
			"0,0は正の偶数です",
	})
	//好きな値を配列のようにjudgeTestに渡すことができる
	//@ValueSource(ints = { 0, 1, 2, -1, -2 })
	void judge(int number, String expected) {
		CheckInteger2_0609 target = new CheckInteger2_0609();
		assertEquals(expected, target.judge(number));
	}

}
