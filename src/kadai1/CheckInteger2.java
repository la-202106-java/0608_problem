package kadai1;

import java.util.Scanner;

public class CheckInteger2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("数字を入力してください：");
		int input = scan.nextInt();
		scan.close();

		CheckInteger2 target = new CheckInteger2();
		String result = target.judgeMinus(input);
		System.out.print(result);

		CheckInteger2 target2 = new CheckInteger2();
		String result2 = target2.judgeNum(input);
		System.out.println(result2);

		//		if (input < 0) {
		//			System.out.print(input + "は負の");
		//		} else {
		//			System.out.print(input + "は正の");
		//		}

		//		if ((input % 2) == 0) {
		//			System.out.println("偶数です");
		//		} else {
		//			System.out.println("奇数です");
		//		}

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
