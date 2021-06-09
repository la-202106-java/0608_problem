package kadai1;

import java.util.Scanner;

public class CheckInteger3 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		CheckInteger3 target = new CheckInteger3();
		CheckInteger3 target2 = new CheckInteger3();

		String result, result2;

		for (int i = 0; i < 3; i++) {
			System.out.print("数字を入力してください：");
			int input = scan.nextInt();

			result = target.judgeMinus(input);
			System.out.print(result);

			result2 = target2.judgeNum(input);
			System.out.println(result2);
		}
		scan.close();

	}

	String judgeMinus(int num1) {
		if (num1 < 0) {
			return num1 + "は負の";
		} else {
			return num1 + "は正の";
		}
	}

	String judgeNum(int num2) {
		if ((num2 % 2) == 0) {
			return "偶数です";
		} else {
			return "奇数です";
		}
	}

}
