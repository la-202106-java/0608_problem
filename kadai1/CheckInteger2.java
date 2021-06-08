package kadai1;

import java.util.Scanner;

public class CheckInteger2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("数字を入力してください: ");
		int input = scan.nextInt();

		System.out.print(input + "は");

		if (input >= 0) {
			System.out.print("正の");
		} else {
			System.out.print("負の");
		}

		if (input % 2 == 0) {
			System.out.println("偶数です");
		} else {
			System.out.println("奇数です");
		}
	}

}
