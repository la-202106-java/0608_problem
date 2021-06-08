package kadai3;

import java.util.Scanner;

public class UserRegistration1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("登録するユーザーIDを入力してください：");
		String userId = scan.nextLine();
		System.out.print("登録するユーザーパスワードを入力してください：");
		String userPwd = scan.nextLine();
		System.out.println("ユーザーID:" + userId + "、パスワード：" + userPwd + "で登録しました。");
	}
}
