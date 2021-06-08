package kadai2;

import java.util.Scanner;

public class introduce1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.print("名前を入力してください：");
		String name = scan.nextLine();

		System.out.print("年齢を入力してください：");
		int old = scan.nextInt();

		System.out.print(name + "さんの10年後は" + (old + 10) + "です");

	}

}
