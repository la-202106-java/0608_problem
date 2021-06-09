package kadai2;

import java.util.Scanner;

public class Introducer2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("名前を入力してください:");
		String name = scan.nextLine();
		if (!judgeName(name)) {
			System.out.println("名前は20文字以内で入力してください");
			return;
		}
		System.out.print("年齢を入力してください:");
		int age = scan.nextInt();
		if (!judgeAge(age)) {
			System.out.println("年齢は0以上、130以下で入力してください");
			return;
		}
		System.out.print(name + "さんの10年後は" + (age + 10) + "歳です");
	}

	public static boolean judgeName(String name) {
		if (name.length() <= 20) {
			return true;
		}
		return false;
	}

	public static boolean judgeAge(int age) {
		if (age >= 0 && age <= 130) {
			return true;
		}
		return false;
	}
}
