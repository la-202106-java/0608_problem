package kadai1;

import java.util.Scanner;

public class CheckInteger2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("数字を入力してください:");
		int input = scan.nextInt();

		CheckInteger3 target = new CheckInteger3();
		String result = target.judge(input);
		System.out.println(result);
	}

	String judge(int number) {
		if (number % 2 == 0) {
			if (number >= 0) {
				return number + "は正の偶数です";
			}
			return number + "は負の偶数です";
		} else {
			if (number >= 0) {
				return number + "は正の奇数です";
			}
			return number + "は負の奇数です";
		}
	}
}