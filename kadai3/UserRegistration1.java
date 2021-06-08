package kadai3;

import java.util.Scanner;

public class UserRegistration1 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Scanner scan1 = new Scanner(System.in);
		System.out.print("登録するユーザIDを入力してください：");
		String userId = scan1.nextLine();

		Scanner scan2 = new Scanner(System.in);
		System.out.print("登録するパスワードを入力してください：");
		String userPass = scan2.nextLine();

		System.out.println("ユーザID：" + userId + "、パスワード：" + userPass + "で登録しました。");

	}

}
