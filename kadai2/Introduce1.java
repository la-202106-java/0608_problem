package kadai2;

import java.util.Scanner;

public class Introduce1 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner scan = new Scanner(System.in);
		System.out.print("名前を入力してください:");
		String name = scan.nextLine();
		System.out.print("年齢を入力してください:");
		int toshi = scan.nextInt();
		int future = toshi + 10;

		System.out.print(name + "さんの10年後は" + future + "歳です");

	}

}
