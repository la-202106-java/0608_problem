package kadai1;

import java.util.Scanner;

public class CheckInteger2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("��������͂��Ă��������F");
		int input = scan.nextInt();
		scan.close();

		if (input < 0) {
			System.out.print(input + "�͕���");
		} else {
			System.out.print(input + "�͐���");
		}

		if ((input % 2) == 0) {
			System.out.println("�����ł�");
		} else {
			System.out.println("��ł�");
		}

	}

}
