package kadai1;

import java.util.Scanner;

public class CheckInteger1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("数字を入力して下さい：");
		int input = scan.nextInt();

		CheckInteger1 checkinteger = new CheckInteger1();
		String result = checkinteger.checkNumber(input);
		System.out.println(result);
	}

	//製品で使われるのがプロダクトコード
	//テストするのがテストコード
	//テストコード実行でGreenか確認
	//プロダクトコード修正したらテストコードを動かしてRed確認
	//テストコードを修正してGreenに
	//プロダクトコードにバグが入ったら既存テストコードが正しい
	//プロダクトコードはテストに併せて修正する
	String checkNumber(int num) {
		if (num % 2 == 0) {
			return num + "は偶数です";
		}
		return num + "は奇数です";
	}

}
