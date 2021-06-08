package kadai2;

import java.util.Scanner;

public class Introduce2 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Scanner scan1 = new Scanner(System.in);

		System.out.print("名前を入力してください：");
		String name = scan1.nextLine();
		if (name.length() > 20) {
			System.out.println("名前は20文字以内で入力してください");
			System.exit(0);
		}

		Scanner scan2 = new Scanner(System.in);

		System.out.print("年齢を入力してください：");
		int age = scan2.nextInt();
		if (age < 0 || age > 130) {
			System.out.println("年齢は0以上、130以下で入力してください");
			System.exit(0);
		}

		System.out.println(name + "さんの10年後は" + (age + 10) + "歳です");

	}

}
