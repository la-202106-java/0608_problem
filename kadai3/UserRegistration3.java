package kadai3;

import java.util.Scanner;

public class UserRegistration3 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("登録するユーザIDを入力してください：");
		String userId = scan.nextLine();
		System.out.print("パスワードを入力してください：");
		String password = scan.nextLine();
		System.out.println("ユーザID：" + userId + "パスワード:" + password + "で登録しました");

		System.out.println("ログイン処理を行います");
		System.out.print("ログインするユーザIDを入力してください：");
		String userId2 = scan.nextLine();
		System.out.print("登loginUserId録するパスワードを入力してください：");
		String password2 = scan.nextLine();

		if (!(userId.equals(userId2) && password.equals(password2))) {
			System.out.println("ユーザIDとパスワードが一致しませんでした");
		}
	}

}
