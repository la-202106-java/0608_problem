package kadai1;

import java.util.Scanner;

public class CheckInteger1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("数字を入力してください:");
		int input = scan.nextInt();

		if (input % 2 == 0) {
			System.out.println(input + "は偶数です");
		}
	}

	String judge(int number) {
		if (number % 2 == 0) {
			return number + "は偶数です";
		}
		return number + "は奇数です";
	}
}
