package kadai3;

import java.util.Scanner;

public class UserRegistration2 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner scan1 = new Scanner(System.in);
		System.out.print("登録するユーザIDを入力してください：");
		String userId = scan1.nextLine();
		if (userId.length() > 20) {
			System.out.println("ユーザIDは20文字以内で入力してください");
			System.exit(0);
		}

		Scanner scan2 = new Scanner(System.in);
		System.out.print("登録するパスワードを入力してください：");
		String userPass = scan2.nextLine();
		if (userPass.length() < 8) {
			System.out.println("パスワードは8文字以上で入力してください");
			System.exit(0);
		}

		System.out.println("ユーザID：" + userId + "、パスワード：" + userPass + "で登録しました。");

	}

}
