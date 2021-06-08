package kadai3;

import java.util.Scanner;

public class UserRegistration2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("登録するユーザーIDを入力してください：");
		String userId = scan.nextLine();
		if (userId.length() > 20) {
			System.out.println("ユーザーIDを20文字以内で入力してください");
			return;
		}
		System.out.print("登録するユーザーパスワードを入力してください：");
		String userPwd = scan.nextLine();

		if (userPwd.length() < 8) {
			System.out.println("パスワードを8文字以上で入力してください");
			return;
		}
		System.out.println("ユーザーID:" + userId + "、パスワード：" + userPwd + "で登録しました。");
	}
}
