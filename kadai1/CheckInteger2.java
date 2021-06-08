package kadai1;

import java.util.Scanner;

public class CheckInteger2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("”š‚ğ“ü—Í‚µ‚Ä‚­‚¾‚³‚¢F");
		int input = scan.nextInt();
		scan.close();

		if (input < 0) {
			System.out.print(input + "‚Í•‰‚Ì");
		} else {
			System.out.print(input + "‚Í³‚Ì");
		}

		if ((input % 2) == 0) {
			System.out.println("‹ô”‚Å‚·");
		} else {
			System.out.println("Šï”‚Å‚·");
		}

	}

}
