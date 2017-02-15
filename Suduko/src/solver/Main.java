package solver;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sudoku sud = new Sudoku();
		
		sud.setNumber(5, 5, 9);
		sud.setNumber(0, 0, 5);
		sud.setNumber(6, 4, 1);
		
		if(sud.solve())
			sud.printSudoku();

	}

}
