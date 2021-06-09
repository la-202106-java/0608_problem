package kadai1;

import java.util.Scanner;

public class CheckInteger1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("数字を入力してください：");
		int input = scan.nextInt();
		scan.close();

		CheckInteger1 checkInteger = new CheckInteger1();
		String result = checkInteger.judge(input);
		System.out.println(result);

		//		if ((input % 2) == 0) {
		//			System.out.println(input + "は偶数です");
		//		} else {
		//			System.out.println(input + "は奇数です");
		//		}
	}

	String judge(int number) {
		if (number % 2 == 0) {
			return number + "は偶数です";
		}
		return number + "は奇数です";
	}
}
