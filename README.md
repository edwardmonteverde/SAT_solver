# bport_assignment1

Contains JAVA code for developing a SAT solver, using the Davis-Putnam algorithm to solve boolean satisfiability problems

At any given point, a DP solver has:
- A partial assignment (an assignment of values to some of the variables in the input formula); and
- An unsatisfied formula (a set of clauses, that may be different from the input formula). Two things to note about this formula:
    - it contains only clauses that are unsatisfied by the current partial assignment, and
    - each of these clauses only contains unassigned literals (assigned literals that do not satisfy a clause are "removed" from the           clause, either literally removed, or marked and ignored). For example, suppose that the algorithm starts out with a formula with a         single clause (-1 2 3), and the completely unasssigned partial assignment of 3 variables (-, -, -). Now suppose that the algorithm         assigns the value true to x1. The clause cannot be satisfied on account of its first term, which is assigned but to a value that is       not helpful to the clause, so this term will be (temporarily) removed from the clause. 

The DP algorithm proceeds as follows (this is a recursive process):
- Any variable that is currently unassigned is chosen to be the next "branch variable," var.
- The branch variable var is assigned an initial (arbitrary) value of true. This assignment of a value to var has two consequences, that     preserve the properties of the formula that we discussed above:
    - any clauses in the formula that contain var are now satisfied, thus they are "removed" from the formula; and
    - any occurrences of the literal -var in other clauses are "removed."
- The algorithm has extended the partial assignment by assigning a value to the branch variable and it calls recursively to attempt to       extend the current partial assignment even more. It may encounter the following scenarios:
    - If the formula is empty (because all its clauses have been satisfied and removed) then the current assignment is a satisfying           assignment (the first base case in the DPSolver method).
    - If one of the clauses in the formula is empty (because its last literal was removed and thus no refinement of the current partial       assignment could possibly satisfy this clause or the formula), the algorithm backtracks: The algorithm will change the value of var       from true to false and call recursively to try to extend this new partial assignment; if this also results in an empty clause, var         will be unassigned (backtracking to an earlier partial assignment) and some earlier branch variable assignment will be changed.           Notice that unassigning or changing the value assigned to a variable may cause changes in the formula; this preserves the properties       of the formula that we discussed above.
