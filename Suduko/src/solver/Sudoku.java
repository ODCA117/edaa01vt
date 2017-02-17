package solver;

public class Sudoku {
	int[][] matris;
	
	/**
	 * Skapar en 9x9 matris full av nollor.
	 */
	public Sudoku(){
		this.matris = new int[9][9];
	}
	
	/** 
	 * H�mtar v�rdet p� en viss plats i sudokut (index b�rjar p� 0).
	 * @param 	row raden i sudokut
	 * @param 	col kolumnen i sudokut
	 * @return v�rdet p� raden row, kolumnen col
	 */
	public int getNumber(int row, int col){
		return matris[row][col];
	}
	
	/** 
	 * �ndrar v�rdet p� en viss plats i sudokut (index b�rjar p� 0).
	 * @param 	row raden i sudokut
	 * @param 	col kolumnen i sudokut
	 * @param 	number siffran som skall s�ttas in
	 */
	public void setNumber(int row, int col, int number){
		matris[row][col] = number;
	}
	
	public void setTable(int[][] table){
		for(int i = 0; i < table.length; i++){
			for(int j = 0; j < table[i].length; j++){
				matris[i][j] = table[i][j];
			}
		}
	}
	
	
	/** 
	 * Ritar sudokut s� som det ser ut just nu.
	 */
	public void printSudoku(){
		for(int i = 0; i <= 8; i++){
			for(int j = 0; j <= 8; j++){
				System.out.print(matris[i][j] + "  ");
			}
			System.out.println();
		}
	}
	
	/** 
	 * L�ser sudokut om det finns en l�sning.
	 * @return true om l�sning existerar
	 */
	public boolean solve(){
		return solve(0, 0);
	}
	
	private boolean solve(int i, int j){
		//Om man g�tt igenom alla rader
		if(i == 9)
			return true;
		
		//Om det finns rader kvar
		else{
			
			boolean solved = false;
			
			//Om positionen �r tom
			if(matris[i][j] == 0){
				//Loopar igenom alla tal som kan s�ttas in
				for(int num = 1; num <= 9; num++){
					matris[i][j] = num;
					//kontrollerar om talet �r godk�nt
					if(isAllowed(num,i,j)){
						
						//om sista kolumnen i raden
						if(j == 8){
							solved = solve(i + 1, 0);
						}else
							solved = solve(i, j + 1);
						
						//om talen efter inte gick att s�tta in
						//S�tt denna plats som tom
						if(solved == false){
							matris[i][j] = 0;
						}
						//Om det gicka att skicka in talen
						else
							return solved;
					}
					else{//om inte s�tt talet tomt
						solved = false;
						matris[i][j] = 0;
					}
				}
			}
			
			//om det finns ett tal p�
			else{
				//Om talet �r godk�nt
				if(isAllowed(matris[i][j], i, j)){
					
					//sista kolumnen p� raden
					if(j == 8){
						solved = solve(i + 1, 0);
					}
					else 
						solved = solve(i, j + 1);
				}
				else
					solved = false;				
			}
			
			return solved;
		}
		
		
	}
	
	// tre privata booleska metoder som kontrollerar reglerna (rad, kolumn, region), anv�nds i solve
	public boolean isAllowed(int number, int row, int col){
		
		
		if(radOk(number, row, col) && kolumnOk(number, row, col) && regionOk(number, row, col)){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean radOk(int number, int row, int col){
		for (int i = 0; i <= 8; i++) {
			if (i != col && number == matris[row][i]) {
				return false;
			}
		}
		return true;
	}
	
	private boolean kolumnOk(int number, int row, int col){
		for (int i = 0; i <= 8; i++) {
			if (i != row && number == matris[i][col]) {
				return false;
			}
		}
		return true;
	}
	
	private boolean regionOk(int number, int row, int col){
		int rowReg;
		int colReg;
		
		if(row < 3){
			rowReg = 0;
		}
		else if(row > 2 && row < 6){
			rowReg = 3;
		}
		else{
			rowReg = 6;
		}
		
		if(col < 3){
			colReg = 0;
		}
		else if(col > 2 && col < 6){
			colReg = 3;
		}
		else{
			colReg = 6;
		}
		
		for(int x = 0; x < 3; x++){
			for(int y = 0; y < 3; y++){
				if(number == matris[x + rowReg][y + colReg] && rowReg + x != row && colReg + y != col){
					return false;
				}
			}
		}
		return true;
	}
	
}