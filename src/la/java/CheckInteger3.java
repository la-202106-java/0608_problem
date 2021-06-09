package la.java;

import java.util.Scanner;

public class CheckInteger3 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		for (int i = 0; i < 3; i++) {
			System.out.print("数字を入力してください：");
			int input = scan.nextInt();

			CheckInteger3 c = new CheckInteger3();
			String result = c.judge(input);
			System.out.println(result);
		}
	}

	String judge(int number) {
		if (number < 0 && number % 2 == 0) {
			return (number + "は負の偶数です。");
		} else if (number < 0 && number % 2 != 0) {
			return (number + "は負の奇数です。");
		} else if (number >= 0 && number % 2 == 0) {
			return (number + "は正の偶数です。");
		} else
			return (number + "は正の奇数です。");
	}
}