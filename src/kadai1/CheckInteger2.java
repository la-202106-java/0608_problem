package kadai1;

import java.util.Scanner;

public class CheckInteger2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("数字を入力して下さい：");
		int input = scan.nextInt();

		CheckInteger2 checkinteger = new CheckInteger2();
		String result = checkinteger.judge(input);
		System.out.println(result);

	}

	String judge(int input) {
		if (input % 2 == 0) {

			if (input >= 0) {
				//System.out.println(input + "は正の偶数です");
				return input + "は正の偶数です";
			} else {
				//System.out.println(input + "は負の偶数です");
				return input + "は負の偶数です";
			}

		} else {

			if (input >= 0) {
				//System.out.println(input + "は正の奇数です");
				return input + "は正の奇数です";
			} else {
				//System.out.println(input + "は負の奇数です");
				return input + "は負の奇数です";
			}
		}

	}

}
