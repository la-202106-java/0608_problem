package kadai2;

import java.util.Scanner;

public class Introduce1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.print("名前を入力してください:");
		String name = scan.nextLine();
		System.out.print("年齢を入力してください:");
		String age = scan.nextLine();
		int num = Integer.parseInt(age);
		System.out.print(name + "さんの10年後は" + (num + 10) + "歳です");
	}
}
