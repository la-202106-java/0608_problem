package kadai1;

import java.util.Scanner;

public class CheckInteger2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("数字を入力して下さい:");
		int input = scan.nextInt();
		if (input >= 0) {
			System.out.print(input + "は正の");
		} else {
			System.out.print(input + "は負の");
		}
		if (input % 2 == 0) {
			System.out.println("偶数です");
		} else {
			System.out.println("奇数です");
		}
	}
}