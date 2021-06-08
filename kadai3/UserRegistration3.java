package kadai3;

import java.util.Scanner;

public class UserRegistration3 {

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
		System.out.println("ログイン処理を行います");

		Scanner scan3 = new Scanner(System.in);
		System.out.print("ログインするユーザIDを入力してください：");
		String userID = scan3.nextLine();

		Scanner scan4 = new Scanner(System.in);
		System.out.print("ログインするパスワードを入力してください：");
		String userPASS = scan4.nextLine();

		if (userId.equals(userID) && userPass.equals(userPASS)) {
			System.out.println("ログインしました");
		}

		else {
			System.out.println("ユーザIDとパスワードが一致しませんでした");

		}

	}

}
