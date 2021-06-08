package kadai2;

import java.util.Scanner;

public class Introduce1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("名前を入力してください");
		String name = scan.nextLine();

		System.out.println("年齢を入力してください");
		int age = scan.nextInt();

		System.out.println(name + "さんの10年後は" + (age + 10) + "歳です");
	}

}
