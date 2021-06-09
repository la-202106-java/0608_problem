package kadai2;

import java.util.Scanner;

public class Introduce {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("名前を入力してください:");
		String name = scan.nextLine();
		Introduce intro = new Introduce();
		boolean hantei = intro.judgeName(name);
		if (hantei) {
			System.out.println("名前は20文字以内で入力してください");
			System.exit(0);
		}
		System.out.print("年齢を入力してください:");
		int age = scan.nextInt();
		System.out.println(intro.judgeAge(name, age, hantei));
	}

	Boolean judgeName(String name) {
		return name.length() > 20;
	}

	String judgeAge(String name, int age, boolean hantei) {
		if (hantei) {
			return "こうはならない";
		} else {
			if (age < 0 || 130 < age) {
				return "年齢は0以上、130以下で入力してください";
			}
			return name + "さんの10年後は" + (age + 10) + "歳です";
		}
	}
}
