package kadai1;

import java.util.Scanner;

public class CheckInteger3 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str;

		for (int i = 0; i < 3; i++) {
			System.out.print("数字を入力してください：");
			int input = scan.nextInt();

			str = String.valueOf(input) + "は";

			str = strPositiveNegative(str, isPositiveNegative(input));
			str = strOddEven(str, isOddEven(input));

			System.out.println(str);
		}
	}

	static boolean isPositiveNegative(int input) {
		return input >= 0;
	}

	static boolean isOddEven(int input) {
		return input % 2 == 0;
	}

	static String strPositiveNegative(String str, boolean judge) {
		if (judge) {
			str += "正の";
		} else {
			str += "負の";
		}
		return str;
	}

	static String strOddEven(String str, boolean judge) {
		if (judge) {
			str += "偶数です";
		} else {
			str += "奇数です";
		}
		return str;
	}

}
