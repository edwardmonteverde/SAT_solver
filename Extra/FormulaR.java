/*Jonathan Rissew
 * CSC406
 * Assignment Part 4 Soft Copy, not yet working*/
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;


*** LinkedList clauses = clCount
*** assignment[] = truthValues[]


public class FormulaRissew {
	//Array to track truth values of variables
	private int assignment[]; //numVars + 1 length
	//Linked list to keep track of all sublists of clauses that are satisfied, and also not satisfied
	LinkedList<Integer> clauses = new LinkedList<Integer>();
	
	//These are set in the read method
	private int numVars;
	private int numClauses;
	private int[][] formula;

	public FormulaRissew(String fileName) {
		read(fileName);
	}
	
	//fileName is arg[0] from main
	//Assigns values to numVars,numClauses,and formula
	void read (String fileName) {
		
		FormulaReaderRissew reader = new FormulaReaderRissew();
		try {
			reader.read(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		numVars = reader.getVariableCount();
		numClauses = reader.getClauseCount();
		formula = reader.getFormula();
		
		//Create truth table 1 larger than needed because there is no 0th variable
		assignment = new int[numVars + 1];
		//Refer to each clause in formula by their index. -1 is the marker used to 
		//distinguish between satisfied/unsatisfied clauses, unsatisfied will be BEFORE.
		for (int i = 0; i < numClauses; i++ ) {
			clauses.add(i);
		}
		clauses.addLast(-1);				
	}
	
	boolean isFormulaEmpty() {
		if (clauses.getFirst() == -1 ) {
			return true;
		}
		return false; // else false
	}
	
	
	boolean isClauseEmpty(int clauseNum) {
		if (isClauseSatisfied(clauseNum)) {
			return false;
		}
		int[] clause = formula[clauses.get(clauseNum)];
		for (int i : clause) {
			//True if the variable in the clause equals the variable in the assignment
			//ex: 1 in clause ==  1, -1 == -1 which comes to 1, and -1 != 1.
			if (assignment[clause[i]] == 0) {
				return false;
			}
			else {
				return false;
			}
		}
		//All clauses are assigned, thus clause is empty
		return true;
	}
	
	boolean hasEmptyClause() {
		
		Iterator<Integer> iter = clauses.iterator();
		int i = 0; //index passed to isClauseEmpty()
		
		while (iter.hasNext()) {
			//Loop through each item until you hit the marker
			if ( iter.next() != -1) {
				//Check if clause is empty
				if (isClauseEmpty(i)) {
					//If the clause is empty return true
					return true;
				}
			}
			//No empty clauses, thus return false
			else {
				return false;
			}
			//Iterate index
			i++;
		}
		return false;
	}
	
	
	int firstAvailable() {
		for (int i = 1; i < assignment.length; i++) {
			if (assignment[i] == 0) {
				return i;
			}
		}
		return -1; //no available
	}

	LinkedList<Integer> seperateClauses() {
		LinkedList<Integer> tempList = new LinkedList<Integer>();
		Iterator<Integer> iter = clauses.iterator();
		while (iter.hasNext()) {
			int var = iter.next();
			if (var != -1) {
				tempList.addLast(var);
			}
			else {
				break;
			}
		}
		return tempList;
	}

	void assign(int index, int value) {
		assignment[index] = value;
		
		for (int i : clauses) {
			if (i == -1) {
				return;
			}
			else if (isClauseSatisfied(i)) {
				clauses.remove(i);
				clauses.addLast(i);
			}
		}	
	}
	
	boolean isClauseSatisfied(int clauseNum) {
		int[] clause = formula[clauses.get(clauseNum)];
		for (int i : clause) {
			//True if the variable in the clause equals the variable in the assignment
			//ex: 1 in clause ==  1, -1 == -1 which comes to 1, and -1 != 1.
			if (clause[i] == assignment[clause[i]] && clause[i] != 0) {
				//System.out.println(clause[i] + " " + assignment[clause[i]]);
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}

	void printAssignment() {
		System.out.println("Assignment array: ");
		for (int i = 0; i < assignment.length; i++) {
			System.out.println("Var" + i + ": " + assignment[i]);
		}
	}
	
	void printFormula() {
		for (int[] i : formula) {
			for (int c : i) {
				System.out.print(c + " ");
			}
			System.out.println();
		}
	}
	
	void print() {
		
		System.out.println("Linkedlist representation: ");
		Iterator<Integer> iter = clauses.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		printAssignment();
	}
}
