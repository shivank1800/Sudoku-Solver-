package suduko;

public class SudukoSolver {
	
	private static int  grid = 9;

	public static void main(String[] args) {
		int[][] board = { {3, 0, 6, 5, 0, 8, 4, 0, 0}, 
		         {5, 2, 0, 0, 0, 0, 0, 0, 0}, 
		         {0, 8, 7, 0, 0, 0, 0, 3, 1}, 
		         {0, 0, 3, 0, 1, 0, 0, 8, 0}, 
		         {9, 0, 0, 8, 6, 3, 0, 0, 5}, 
		         {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
		         {1, 3, 0, 0, 0, 0, 2, 5, 0}, 
		         {0, 0, 0, 0, 0, 0, 0, 7, 4}, 
		         {0, 0, 5, 2, 0, 6, 3, 0, 0} };
		
		printBoard(board);
		System.out.println();
		
		if(solveBoard(board)) {
			System.out.println("solved succesfully");
			System.out.println();
		}else {
			System.out.println("unsolved");
			System.out.println();
		}
		
		printBoard(board);

	}

private static void printBoard(int[][] board) {
	for(int row = 0; row < grid ;row++) {
		if(row%3 == 0 && row !=0) {
			System.out.println("------------");
		}
		for(int column = 0; column <grid ; column++) {
			if(column % 3 == 0 && column != 0) {
				System.out.print("|");
			}
			System.out.print(board[row][column]);
		}
		System.out.println();
	}
		
		
	}

//to check no. in row
private static boolean isNumberInRow(int[][] board,int number, int row) {
	for(int i=0;i<grid;i++) {
		if(board[row][i] == number) {
			return true;
		}
	}
	return false;
}

//to check no. in column
private static boolean isNumberInColumn(int[][] board,int number,int column) {
	for(int i=0;i<grid;i++) {
		if(board[i][column] == number) {
			return true;
		}
	}
	return false;
}

//to check the no. 3*3 grid
private static boolean isNumberInBox(int[][] board,int number, int row,int column) {
	int localBoxRow = row - row%3;
	int localBoxColumn = column - column*3;
	for(int i = localBoxRow ;i< localBoxRow+3; i++) {
		for( int j =localBoxColumn; j<localBoxColumn ; j++) {
			if(board[i][j] == number) {
				return true;
			}
		}
	}
	return false;
}

private static boolean isValid(int[][] board,int number, int row,int column) {
	return !isNumberInRow(board,number,row) &&
			!isNumberInColumn(board,number,column) &&
			!isNumberInBox(board,number,row,column);
}


private static boolean solveBoard(int[][] board) {
	for(int row = 0; row < grid; row++) {
		for(int column =0 ; column < grid; column++) {
			if(board[row][column] == 0) {
				for(int totry = 1; totry <=9 ; totry++) {
					if(isValid(board,totry,row,column)) {
						board[row][column] = totry;
						
						if(solveBoard(board)) {
							return true;
						}else {
							board[row][column] = 0;
						}
					}
					
				}
			}
		}
	}
	return true;
}



}