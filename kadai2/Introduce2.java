package kadai2;

import java.util.Scanner;

public class Introduce2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.print("名前を入力してください:");
		String name = scan.nextLine();
		System.out.print("年齢を入力してください:");
		String age = scan.nextLine();
		int num = Integer.parseInt(age);
		if (name.length() < 0 || 130 < name.length()) {
			System.out.println("名前は20文字以内で入力してください");
		}

		System.out.print(name + "さんの10年後は" + (num + 10) + "歳です");
	}
}