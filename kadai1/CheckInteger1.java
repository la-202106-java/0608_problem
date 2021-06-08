package kadai1;

import java.util.Scanner;

public class CheckInteger1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("数字を入力してください：");
		int input = scan.nextInt();

		if (input % 2 == 0) {
			System.out.println(input + "は偶数です");
		} else {
			System.out.println(input + "は奇数です");

		}

	}

}
