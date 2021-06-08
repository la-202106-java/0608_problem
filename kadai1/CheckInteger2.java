package kadai1;

import java.util.Scanner;

public class CheckInteger2 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Scanner scan = new Scanner(System.in);
		System.out.print("数字を入力してください：");
		int n = scan.nextInt();

		if (n % 2 == 0) {

			if (n >= 0) {
				System.out.println(n + "は正の偶数です");
			} else {
				System.out.println(n + "は負の偶数です");
			}
		}

		else {
			if (n >= 0) {
				System.out.println(n + "は正の奇数です");
			} else {
				System.out.println(n + "は負の奇数です");
			}
		}

	}
}
