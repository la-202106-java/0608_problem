package kadai2;

import java.util.Scanner;

public class Introduce1 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner scan = new Scanner(System.in);

		System.out.print("名前を入力してください：");
		String name = scan.nextLine();
		System.out.print("年齢を入力してください：");
		int age = scan.nextInt();

		if (age >= 0) {
			System.out.println(name + "さんの10年後は" + (age + 10) + "歳です");
		} else {
			System.out.println("年齢が正しくありません");
		}

	}

}
