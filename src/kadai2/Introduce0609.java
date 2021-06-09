package kadai2;

import java.util.Scanner;

public class Introduce0609 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Introduce0609 introduce0609 = new Introduce0609();

		System.out.print("名前を入力してください：");
		String name = scan.nextLine();
		//name check
		if (!introduce0609.checkName(name)) {
			System.out.println("名前は20字以内で入力してください");
			System.exit(0);
		}

		System.out.print("年齢を入力してください：");
		int age = scan.nextInt();
		//age check
		if (!introduce0609.checkAge(age)) {
			System.out.println("年齢は0以上130以下で入力してください");
			System.exit(0);
		}

		System.out.println(introduce0609.futureAge(name, age));

	}

	boolean checkName(String name) {
		if (name.length() <= 20) {
			return true;
		}
		return false;
	}

	boolean checkAge(int age) {
		if (0 <= age && age <= 130) {
			return true;
		}
		return false;
	}

	String futureAge(String name, int age) {
		return name + "さんの10年後は" + (age + 10) + "歳です";
	}

}
