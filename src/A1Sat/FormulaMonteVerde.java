package A1Sat;

import java.util.LinkedList;

public class FormulaMonteVerde {

	int clauses, vars;
	LinkedList<Integer> clCount = new LinkedList<>();
	LinkedList<Integer> temp = new LinkedList<>();
	int[] truthValues;
	int formula[][];
	LinkedList<Integer> unsatisfied;
	LinkedList<Integer> satisfied;

	// LinkedList<Integer> tempX = new LinkedList<>();
	// LinkedList<Integer> tempY = new LinkedList<>();

	// public FormulaMonteVerde (String inputFile){
	// read(inputFile);
	// }

	public void read(String inputFile) {

		// start read in formula array
		FormulaReaderMonteVerde reader = new FormulaReaderMonteVerde();

		try {
			// System.out.println(args[0]);
			reader.read(inputFile);
		} catch (Exception e) {
			System.out.println("Failed because" + e);
			System.exit(1);
		}

		formula = reader.getFormula();
		clauses = reader.getNumClauses();
		vars = reader.getNumVars();

		// int[][] assignArray = new int[clauses][];
		truthValues = new int[vars + 1];

		for (int i = 0; i < clauses; i++) {
			clCount.addLast(i);
		}
		clCount.addLast(-1);

	}
	// for (int x = 0; x < clauses; x++) {
	// assignArray[x] = new int[vars + 1];
	// }

	// end initial formula read

	// test print
	//
	// System.out.println("Print unassigned array: ");
	// for (int[] x : assignArray) {
	// for (int y : x) {
	// System.out.print(y + " ");
	// }
	// System.out.println();
	// }

	private int makeBooleanInt(int i) {
		if (i > 0)
			return 1;
		if (i < 0)
			return -1;
		else
			return 0;
	}

	boolean isClauseSatisfied(int clNum) {
		for (int var : formula[clNum]) {
			if (truthValues[Math.abs(var)] == makeBooleanInt(var))
				return true;
		}
		return false;
	}

	boolean isClauseEmpty(int clNum) {
		for (int clVar : formula[clNum]) {
			if (truthValues[Math.abs(clVar)] == 0) {
				return false;
			}
		}
		return !(isClauseSatisfied(clNum));
	}

	// public boolean isClauseSatisfied(int clNum) {
	// int[] clause = formula[clNum];
	// for (int i : clause) {
	// if (clause[i] == truthValues[clause[i]] && clause[i] != 0) {
	// return true;
	// } else {
	// return false;
	// }
	// }
	// return false;
	// }

	public Boolean isFormulaEmpty() {
		// if first token is -1, its "empty"
		return (clCount.getFirst() == -1);
	}

	public int firstAvailable() {
		for (int i = 0; i < vars; i++) {
			if ((truthValues[i] != 1) || (truthValues[i] != -1))
				return i;
		}
		return -1;
	}

	// public boolean hasEmptyClause() {
	// int i = 0;
	// Iterator<Integer> iterator = clCount.iterator();
	//
	// while (iterator.hasNext()) {
	// if (iterator.nextInt() != -1) {
	// if (isClauseEmpty(i))
	// return true;
	// } else
	// return false;
	// i++;
	// }
	// return false;
	// }

	public boolean hasEmptyClause() {
		for (int i : temp) {
			if (isClauseEmpty(i))
				return true;
		}
		return false;
	}

	public LinkedList<Integer> separateClauses() {
		if (!isFormulaEmpty() == true) {

			int x = 0;
			temp = new LinkedList<>();

			while ((x = clCount.get(0)) != -1) {
				temp.addLast(x);
				clCount.remove(0);
			}
		}
		return temp;
	}

	// public LinkedList<Integer> separateClauses() {
	// int x = 0;
	// int clause;
	//
	// // create temp list
	// temp = new LinkedList<>();
	//
	// while ((clause = clCount.get(x)) != -1) {
	// temp.addLast(clause);
	// x++;
	// }
	// return temp;
	// }

	public void printFormula() {
		System.out.println("Printing formula array: ");
		for (int[] x : formula) {
			for (int y : x) {
				System.out.print(y + " ");
			}
			System.out.println();
		}
	}

	// public void printAssignment(){
	public void printAssignment() {
		System.out.println("Printing truth variable assignment array: ");
		for (int i = 0; i < vars + 1; i++) {
			System.out.print(truthValues[i] + " ");
		}
		System.out.println();
	}

	public void assign(int var, int value) {
		truthValues[var] = value;

		LinkedList<Integer> unsatisfied = separateClauses();
		LinkedList<Integer> satisfied = new LinkedList();

		for (int i = 0; i < unsatisfied.size(); i++) {

			int clNum = unsatisfied.get(i);
			boolean sat = isClauseSatisfied(clNum);

			if (sat == true) {
				satisfied.addLast(clNum);
				unsatisfied.remove(i);
			}
		}
	}

	public void unsetAndReset(int index) {
		truthValues[index] = 0;
		clCount.removeFirstOccurrence(-1);
	}

	public void print() {
		System.out.print("LinkedList Form: ");
		System.out.println(clCount);
	}
}
