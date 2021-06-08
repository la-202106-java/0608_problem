import java.util.Scanner;

public class CheckInteger3 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		for (int i = 0; i < 3; i++) {
			System.out.print("数字を入力してください：");
			int input = scan.nextInt();

			if (input < 0 && input % 2 == 0) {
				System.out.println(input + "は負の偶数です。");
			} else if (input < 0 && input % 2 != 0) {
				System.out.println(input + "は負の奇数です。");
			} else if (input >= 0 && input % 2 == 0) {
				System.out.println(input + "は正の偶数です。");
			} else if (input >= 0 && input % 2 != 0) {
				System.out.println(input + "は正の奇数です。");
			} else {
				System.out.println("入力が正しくありあません");
			}
		}

	}

}
