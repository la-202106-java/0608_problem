package kadai1;

import java.util.Scanner;

public class CheckInteger1 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner scan = new Scanner(System.in);
		System.out.print("数字を入力してください:");
		int input = scan.nextInt();

		if (input % 2 == 0) {
			System.out.print(input + "は偶数です");
		} else {
			System.out.print(input + "は奇数です");
		}

	}

}
