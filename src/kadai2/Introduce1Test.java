package kadai2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Introduce1Test {

	@ParameterizedTest
	@CsvSource({
			"佐藤太郎,25,佐藤太郎の10年後は35歳です",
			"山田花子,-50,年齢は0以上、130以下で入力してください",
			"田中一郎,200,年齢は0以上、130以下で入力してください",
			"さとうやまだたなかさとうやまだたなかさとうやまだたなか,25,名前は20文字以内で入力してください",
	})
	void judgeTest(String name, int age, String expected) {
		Introduce1 target = new Introduce1();
		String result = target.judge(name, age);
		assertEquals(expected, result);
		//		System.out.println(expected);
		//		System.out.println("--------------");
	}

}
