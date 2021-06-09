package Kadai1_0609;

import java.util.Scanner;

public class CheckInteger2_0609 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner scan = new Scanner(System.in);
		System.out.print("数字を入力してください：");
		int n = scan.nextInt();

		CheckInteger2_0609 checkInteger = new CheckInteger2_0609();
		String result = checkInteger.judge(n);
		System.out.println(result);
	}

	String judge(int number) {

		if (number >= 0) {
			if (number % 2 == 0) {
				return number + "は正の偶数です";
			} else {
				return number + "は正の奇数です";
			}
		}

		else {
			if (number % 2 == 0) {
				return number + "は負の偶数です";
			} else {
				return number + "は負の奇数です";
			}
		}
	}

}
