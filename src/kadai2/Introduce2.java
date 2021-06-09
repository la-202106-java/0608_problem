package kadai2;

import java.util.Scanner;

public class Introduce2 {

	public static void main(String[] args) {
		try {
			Scanner scan = new Scanner(System.in);

			System.out.println("名前を入力してください: ");

			String name = scan.nextLine();

			Introduce2 introduce = new Introduce2();
			introduce.checkName(name);

			System.out.println("年齢を入力してください: ");

			int age = scan.nextInt();
			introduce.checkAge(age);

			System.out.println(name + "さんの10年後は" + addAge(age) + "歳です");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public void checkName(String name) {
		if (name.length() > 20) {
			//			System.out.println("名前は20文字以内で入力してください");
			//			System.exit(0);
			throw new IllegalArgumentException("名前は20文字以内で入力してください");
		}
	}

	public void checkAge(int age) {
		if (age < 0 || age > 130) {
			//			System.out.println("年齢は0以上、130以下で入力してください");
			//			System.exit(0);
			throw new IllegalArgumentException("年齢は0以上、130以下で入力してください");

		}
	}

	static int addAge(int age) {
		return age + 10;

	}
}
