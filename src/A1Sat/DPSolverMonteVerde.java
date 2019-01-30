package A1Sat;

public class DPSolverMonteVerde {
	
	FormulaMonteVerde formula = new FormulaMonteVerde();
	
	boolean success = false;
	
	public DPSolverMonteVerde (String inputFile){
		read(inputFile);
	}

	

	public void read(String inputFile) {
		formula.read(inputFile);
	}

	

	private boolean dpSolver(FormulaMonteVerde formula) {
		if (formula.isFormulaEmpty()) {
			return true;
			} 
		if (formula.hasEmptyClause()) {
			return false;
			} 
		else {
			int var = formula.firstAvailable();
			formula.assign(var, 1);

			if (dpSolver(formula)) {
				return true;
			}

			else {
				formula.unsetAndReset(var);

				formula.assign(var, -1);

				if (dpSolver(formula)) {
					return true;
				} else {
					formula.unsetAndReset(var);
					return false;
				}
			}
		}
	}
	

	
	public void solve() {

		if (dpSolver(formula) == true) {
			System.out.println("Formula is satisfiable:");
			formula.print();
			success = true;
		} 
		
		else {
			System.out.println("Formula not satisfiable");
			formula.print();
			success = false;
		}
	}
	
	public boolean getSuccess()
	{	
		return success;
	}
	
}