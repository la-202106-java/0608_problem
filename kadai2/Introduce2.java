package kadai2;

import java.util.Scanner;

public class Introduce2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("名前を入力してください:");
		String name = scan.nextLine();
		if (name.length() <= 20) {
			System.out.print("年齢を入力してください:");
			int age = scan.nextInt();
			if (age < 0 || 130 < age) {
				System.out.println("年齢は0以上、130以下で入力してください");
			} else {
				System.out.println(name + "さんの10年後は" + (age + 10) + "歳です");
			}

		} else {
			System.out.print("名前は20文字以内で入力してください");
		}

	}
}
