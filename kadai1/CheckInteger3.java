package kadai1;

import java.util.Scanner;

public class CheckInteger3 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < 3; i++) {
			System.out.print("数字を入力して下さい:");
			int input = scan.nextInt();
			if (input % 2 == 0) {
				if (input >= 0) {
					System.out.println(input + "は正の偶数です");
				} else {
					System.out.println(input + "は負の偶数です");
				}
			} else {
				if (input >= 0) {
					System.out.println(input + "は正の奇数です");
				} else {
					System.out.println(input + "は負の奇数です");
				}
			}
		}
	}
}
