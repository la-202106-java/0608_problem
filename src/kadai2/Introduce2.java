package kadai2;

import java.util.Scanner;

public class Introduce2 {
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner scan = new Scanner(System.in);
		System.out.print("名前を入力してください:");
		String name = scan.nextLine();

		Introduce2 na = new Introduce2();

	}

	if(!Introduce2Test.judge(name))

	{
		System.out.println("20文字以内で入力してください");
		System.exit(0);
	}

	Introduce2 ag = new Introduce2();System.out.print("年齢を入力してください:");
	int age = scan.nextInt();

	if(!Introduce2Test.judge2(age))
	{
		System.out.println("20文字以内で入力してください");
		System.exit(0);
	}

}}

//		if (name.length() >= 20) {
//			System.out.println("名前は20文字以内で入力してください");
//			System.exit(0);
//		}
//		System.out.print("年齢を入力してください:");
//		int toshi = scan.nextInt();
//
//		if ((0 > toshi) || (toshi >= 130)) {
//			System.out.println("年齢は0以上、130以下で入力してください");
//
//		} else {
//			int future = toshi + 10;
//			System.out.print(name + "さんの10年後は" + future + "歳です");
//		}
