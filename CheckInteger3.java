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

			if (isPositiveNegative(input)) {
				str += "正の";
			} else {
				str += "負の";
			}

			if (isOddEven(input)) {
				str += "偶数です";
			} else {
				str += "奇数です";
			}

			System.out.println(str);
		}
	}

	static boolean isOddEven(int input) {
		return input % 2 == 0;
	}

	static boolean isPositiveNegative(int input) {
		return input >= 0;
	}

}
