import java.util.Arrays;

public class FormulaReaderMonteVerde1 {

	/*
	 * public void read(String cnfFile) throws Exception { String temp = null;
	 * int count = 0; int numClauses; int numVars; int [][] formula; Pattern
	 * pattern = Pattern.compile("p cnf");
	 * 
	 * Scanner scan = new Scanner(new File(cnfFile));
	 * 
	 * // scan up to 'p cnf' while (scan.findInLine(pattern) == null)
	 * scan.nextLine();
	 * 
	 * // p cnf found, read # of variables and clauses numVars = scan.nextInt();
	 * numClauses = scan.nextInt();
	 */

	public static void main(String[] args) {

		String[][] tester = new String[][] { { "-1 2 3 0" }, { "-1 3 4 0" }, { "-1 3 -4 0" }, { "-1 -3 4 0" } };

		int row = 0;
		int col = 0;
		int count = 0;
		int[][] formula = new int[tester.length][];

		while (count < tester.length) {
			String temp = tester[row][col];
			String[] charArray = temp.split("\\s+");
			int[] line = new int[charArray.length];

			for (int i = 0; i < charArray.length; i++) {
				String numAsStr = charArray[i];
				line[i] = Integer.parseInt(numAsStr);
				formula[count] = line;
			}
			row++;
			count++;
		}

		System.out.println(Arrays.deepToString(formula).replace("], ", "]\n"));
	}
}

/*
 * String sampleString = "101,203,405";
 * 
 * // create stringArray by splitting the string at commas String[] stringArray
 * = sampleString.split(",");
 * 
 * // create intArray, which is length of split string (9) int[] intArray = new
 * int[stringArray.length];
 * 
 * // parse each number as an integer for (int i = 0; i < stringArray.length;
 * i++) {
 * 
 * // take each cell as separate string String numberAsString = stringArray[i];
 * 
 * // take string cell and turn it into int thru intArray intArray[i] =
 * Integer.parseInt(numberAsString); }
 * 
 * 
 * System.out.println("Number of integers: " + intArray.length);
 * System.out.println("The integers are:"); for (int number : intArray) {
 * System.out.println(number); } }
 * 
 * 
 * }
 * 
 * }
 */