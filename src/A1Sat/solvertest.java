package A1Sat;

import java.util.Arrays;
import java.util.Scanner;

public class solvertest {

	String[][] tester = new String[][] { { "-1 2 3 0" }, { "-1 3 4 0" }, { "-1 3 -4 0" }, { "-1 -3 4 0" } };

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int i = 0;
		String[] charArray = new String[100];

		while (scan.hasNextLine()) {
			// String str = Integer.parseInt(scan.next());
			// array[i] = scan.next();

			String temp = scan.nextLine();

			charArray = temp.split("\\s+");
			System.out.println(Arrays.toString(charArray));
		}

		System.out.println(Arrays.toString(charArray));

	}

}
