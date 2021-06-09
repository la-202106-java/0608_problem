package kadai1;

import java.util.Scanner;

public class CheckInteger {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("数字を入力して下さい:");
		int input = scan.nextInt();
		CheckInteger checkInteger = new CheckInteger();
		checkInteger.judgeInput(input);
	}

	String judgeInput(int number) {
		if (number % 2 == 0) {
			if (number >= 0) {
				return number + "は正の偶数です";
			} else {
				return number + "は負の偶数です";
			}
		} else {
			if (number >= 0) {
				return number + "は正の奇数です";
			} else {
				return number + "は負の奇数です";
			}
		}
	}
}
