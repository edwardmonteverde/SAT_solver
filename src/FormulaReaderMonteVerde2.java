import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FormulaReaderMonteVerde2 {

	String waste;
	int numClauses;
	int numVars;

	public void read(String cnfFile) throws Exception {
		Scanner scan = new Scanner(new File(cnfFile));
		// counter scanner
		Scanner counter = new Scanner(new File(cnfFile));

		Pattern pattern = Pattern.compile("p cnf");

		// scan up to 'p cnf'
		while (scan.findInLine(pattern) == null)
			scan.nextLine();

		while (counter.findInLine(pattern) == null)
			counter.nextLine();

		// p cnf found
		numVars = scan.nextInt();
		numClauses = scan.nextInt();
		int temp = 0;

		temp = counter.nextInt();
		temp = counter.nextInt();

		int num = 0;
		int count = 0;
		int row = numClauses;

		int[][] formulaArray = new int[numVars][];

		// read in characters to array
		while (scan.hasNextLine() == true && num < numClauses) {
			// reset j
			int col = 0;

			formulaArray[row] = new int[count];
			// read in ints in row
			while ((scan.nextInt() != 0) && (col < count)) {

				formulaArray[row][col] = scan.nextInt();
				col++;
			}
			row++;
			num++;

		}

		for (int x = 0; x < formulaArray.length; x++)
			System.out.print(x + " ");

	}
}
