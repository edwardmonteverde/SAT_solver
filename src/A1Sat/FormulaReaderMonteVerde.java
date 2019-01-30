package A1Sat;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FormulaReaderMonteVerde {

	int numClauses;
	int numVars;
	int[][] formula;

	public void read(String inputFile) throws Exception {

		Scanner scan = new Scanner(new File(inputFile));

		Pattern pattern = Pattern.compile("p cnf");
		// scan up to 'p cnf'
		while (scan.findInLine(pattern) == null) {
			scan.nextLine();
		}

		// p cnf found
		numVars = Integer.parseInt(scan.next());
		numClauses = Integer.parseInt(scan.next());
		scan.nextLine();
		formula = new int[numClauses][];

		int count = 0;
		int numC = 0;

		// System.out.println(numVars + numClauses);
		while (scan.hasNextLine() && numC < numClauses) {

			String temp = scan.nextLine();

			String[] charArray = temp.split("\\s+");

			formula[numC] = new int[charArray.length];

			while (count < charArray.length) {
				formula[numC][count] = Integer.parseInt(charArray[count++]);
			}

			count = 0;
			numC++;
		}
	}

	public int getNumVars() {
		return numVars;
	}

	public int getNumClauses() {
		return numClauses;
	}

	public int[][] getFormula() {
		return formula;
	}

	public void printFormula() {

	}
}
