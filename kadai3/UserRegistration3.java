package kadai3;

import java.util.Scanner;

public class UserRegistration3 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("登録するユーザIDを入力してください:");
		String userId = scan.nextLine();
		if (userId.length() > 20) {
			System.out.println("ユーザIDは20文字以内で入力してください");
			System.exit(0);
		}

		System.out.print("登録するパスワードを入力してください:");
		String passwd = scan.nextLine();
		if (passwd.length() < 8) {
			System.out.println("パスワードは8文字以上で入力してください");
			System.exit(0);
		}

		System.out.println("ユーザID:" + userId + "、" + "パスワード:" + passwd);
		System.out.println("ログイン処理を行います");

		//equalsで処理する
	}
}
