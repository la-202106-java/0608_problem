package kadai2;

import java.util.Scanner;

public class Introduce2 {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner scan = new Scanner(System.in);
		System.out.print("名前を入力してください:");
		String name = scan.nextLine();
		if (name.length() >= 20) {
			System.out.println("名前は20文字以内で入力してください");
			System.exit(0);
		}
		System.out.print("年齢を入力してください:");
		int toshi = scan.nextInt();

		if ((0 > toshi) || (toshi >= 130)) {
			System.out.println("年齢は0以上、130以下で入力してください");

		} else {
			int future = toshi + 10;
			System.out.print(name + "さんの10年後は" + future + "歳です");
		}
	}

}
