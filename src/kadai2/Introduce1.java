package kadai2;

import java.util.Scanner;

public class Introduce1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("名前を入力してください:");
		String name = scan.nextLine();
		if (name.length() > 20) {
			Introduce1 format = new Introduce1();
			int age = 0;
			String result = format.judge(name, age);
			System.out.println(result);
			System.exit(0);
		}

		System.out.print("年齢を入力してください:");
		int age = scan.nextInt();

		Introduce1 format = new Introduce1();
		String result = format.judge(name, age);
		System.out.println(result);

	}

	String judge(String name, int age) {
		if (name.length() > 20) {
			return "名前は20文字以内で入力してください";
		}
		if (age > 130 || age < 0) {
			return "年齢は0以上、130以下で入力してください";

		}
		return name + "の10年後は" + (age + 10) + "歳です";
	}
}
