package kadai3;

import java.util.Scanner;

public class UserRegistration2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("登録するユーザIDを入力してください：");
		String userId = scan.nextLine();
		if (userId.length() > 20) {
			System.out.println("ユーザIDは20字以内で入力してください");
		} else {
			System.out.print("パスワードを入力してください：");
			String password = scan.nextLine();
			if (password.length() < 8) {
				System.out.println("パスワードは8文字以上で入力してください");
			} else {
				System.out.println("ユーザID：" + userId + "パスワード:" + password + "で登録しました");
			}
		}
	}

}
