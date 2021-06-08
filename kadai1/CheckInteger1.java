package kadai1;

import java.util.Scanner;

public class CheckInteger1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("”š‚ğ“ü—Í‚µ‚Ä‚­‚¾‚³‚¢F");
		int input = scan.nextInt();
		scan.close();

		if ((input % 2) == 0) {
			System.out.println(input + "‚Í‹ô”‚Å‚·");
		} else {
			System.out.println(input + "‚ÍŠï”‚Å‚·");
		}
	}

}