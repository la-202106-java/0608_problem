package la.java;

import java.util.Scanner;

public class MON2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("名前を入力してください：");
		String name = scan.nextLine();

		MON2 mon = new MON2();
		String result = mon.judge(name);
		System.out.println(result);

		System.out.print("年齢を入力してください：");
		int old = scan.nextInt();
		String result2 = mon.judge2(old);
		System.out.println(result2);

		System.out.println(name + "さんの10年後は" + (old + 10) + "歳です");
	}

	String judge(String name) {
		if (name.length() > 20) {
			return "名前は20字以内で入力してください";
		}
		return "あなたの名前の文字数は：" + name.length();
	}

	String judge2(int old) {
		if (0 > old || old > 130) {
			return ("年齢は0以上、130以下で入力してください");
		}
		return "貴方の年齢は：" + old;
	}

}
