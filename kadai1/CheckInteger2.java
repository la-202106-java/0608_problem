package kadai1;

import java.util.Scanner;

public class CheckInteger2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("数字を入力してください：");
		int input = scan.nextInt();

		String negaPosi = "正";
		String evenOdd = "奇数";

		if (input < 0) {
			negaPosi = "負";
		}

		if (input % 2 == 0) {
			evenOdd = "偶数";
		}
		System.out.println(input + "は" + negaPosi + "の" + evenOdd + "です");

	}

}
