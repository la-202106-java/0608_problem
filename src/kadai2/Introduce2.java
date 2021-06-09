package kadai2;

import java.util.Scanner;

public class Introduce2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("名前を入力してください:");
		String name = scan.nextLine();
		if (name.length() > 20) {
			System.out.println("名前は20文字以内で入力してください");
			System.exit(0);
		}
		System.out.print("年齢を入力してください:");
		int age = scan.nextInt();
		if (age > 130 || age < 0) {
			System.out.println("年齢は0以上、130以下で入力してください");
			System.exit(0);
		}
		System.out.println(name + "の10年後は" + (age + 10) + "歳です");
	}
}
