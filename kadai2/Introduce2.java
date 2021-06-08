package kadai2;

import java.util.Scanner;

public class Introduce2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String name = null;
		int age;
		int count = 0;

		do {
			if (name != null) {
				System.out.println("名前は20文字以内で入力してください");
			}

			System.out.print("名前を入力してください：");
			name = scan.nextLine();
		} while (name.length() > 20);

		do {
			if (count != 0) {
				System.out.println("年齢は0以上、130以下で入力してください");
			} else if (count == 0) {
				count += 1;
			}

			System.out.print("年齢を入力してください：");
			age = scan.nextInt();
		} while (age < 0 || age > 130);

		System.out.println(name + "さんの10年後は" + (age + 10) + "歳です");
	}

}
