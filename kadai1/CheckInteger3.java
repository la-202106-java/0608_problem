package kadai1;

import java.util.Scanner;

public class CheckInteger3 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		for (int i = 0; i < 3; i++) {
			System.out.print("��������͂��Ă��������F");
			int input = scan.nextInt();

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
		scan.close();

	}

}
