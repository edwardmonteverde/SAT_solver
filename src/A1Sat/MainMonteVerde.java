package A1Sat;

public class MainMonteVerde {
	
	
	//File infile;

	public static void main(String[] args) {
		// File inputFile = new File(args[0]);
		// Scanner scan = new Scanner(inputFile);
		//
		// Scanner scan = new Scanner(System.in);
		// // prompt
		// System.out.print("Enter file to be read: ");
		//
		// // check if file is entered
		// if (args.length == 0) {
		// System.out.println("No file entered.");
		// System.exit(1);
		// }
		//
		// File inputFile = new File(args[0]);
		// // handle exception for failure to open file
		// try {
		// scan = new Scanner(inputFile);
		// } catch (IOException ioException) {
		// System.err.println("File failed to open.");
		// System.exit(1);
		// }

		FormulaReaderMonteVerde formulaReader = new FormulaReaderMonteVerde();
		try {
			System.out.println(args[0]);
			formulaReader.read(args[0]);
		} catch (Exception e) {
			System.out.println("Fail" + e);
			System.exit(1);

		}

		// System.out.println("Get formula: " + reader.getFormula());
		
		System.out.println("test");
		formulaReader.printFormula();
		
		
		// create DPSolver object
		DPSolverMonteVerde solver = new DPSolverMonteVerde(args[0]);
		
		FormulaMonteVerde formula = new FormulaMonteVerde();
		
		solver.read(args[0]);
		
		// create Timer object
		TimerMonteVerde timer = new TimerMonteVerde();
		
		
		// start the timer
		timer.start();

		
		// solve the sat problem
		solver.solve();
		
		// stop the timer
		timer.stop();
		
		timer.printTime();
		System.out.println();
		
		if (solver.getSuccess() == true){
			System.out.println("Number of variables: " + formulaReader.getNumVars());
			System.out.println("Number of clauses " + formulaReader.getNumClauses());
		}
		

		formula.read(args[0]);
		formula.printFormula();
		formula.printAssignment();
		// formula.print(); 
	}
}
