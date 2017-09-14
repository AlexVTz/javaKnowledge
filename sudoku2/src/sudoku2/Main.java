package sudoku2;

import java.util.*;

public class Main {
	
	
	public static boolean square(char[][] grid){
		HashSet<Character> nuevo = new HashSet<Character>();
		int aumentoColumna = 0;
		int aumentoFila = 0;
		
		for(int column = aumentoFila; column <= 6; column+=3){
			for(int row = 0; row < 3; row++){
				for(int i = aumentoFila; i < (aumentoFila+3); i++){
					for(int j = aumentoColumna; j < (aumentoColumna+3); j++){
						if(grid[i][j] != '.'){
							if(!nuevo.add(grid[i][j])){
								return false;
							}
						}
					}
				}
				nuevo.clear();
				aumentoColumna +=3;
			}
			aumentoFila +=3;
			aumentoColumna = 0;
		}
		
		return true;
		
	}
	
	
	public static boolean sudoku2(char[][] grid){
		HashSet<Character> nuevo = new HashSet<Character>();
		
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(grid[i][j] != '.'){
					if(!nuevo.add(grid[i][j])){
						return false;
					}
				}
			}
			nuevo.clear();
			for(int j = 0; j < 9; j++){
				if(grid[j][i] != '.'){
					if(!nuevo.add(grid[j][i])){
						return false;
					}
				}
			}
			nuevo.clear();
		}
		return square(grid);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		char[][] grid = {{'.', '.', '.', '1', '4', '.', '.', '2', '.'},
						{'.', '.', '6', '.', '.', '.', '.', '.', '.'},
						{'.', '.', '.', '.', '.', '.', '.', '.', '.'},
						{'.', '.', '1', '.', '.', '.', '.', '.', '.'},
						{'.', '6', '7', '.', '.', '.', '.', '.', '9'},
						{'.', '.', '.', '.', '.', '.', '8', '1', '.'},
						{'.', '3', '.', '.', '.', '.', '.', '.', '6'},
						{'.', '.', '.', '.', '.', '7', '.', '.', '.'},
						{'.', '.', '.', '5', '.', '.', '.', '7', '.'}};
		
		char[][] grid2 = {{'.', '.', '.', '.', '2', '.', '.', '9', '.'},
						  {'.', '.', '.', '.', '6', '.', '.', '.', '.'},
						  {'7', '1', '.', '.', '7', '5', '.', '.', '.'},
						  {'.', '7', '.', '.', '.', '.', '.', '.', '.'},
						  {'.', '.', '.', '.', '8', '3', '.', '.', '.'},
						  {'.', '.', '8', '.', '.', '7', '.', '6', '.'},
						  {'.', '.', '.', '.', '.', '2', '.', '.', '.'},
						  {'.', '1', '.', '2', '.', '.', '.', '.', '.'},
						  {'.', '2', '.', '.', '3', '.', '.', '.', '.'}};
		
		//System.out.println(grid[0][4]);
		
		//System.out.println(square(grid2));
		
		System.out.println(sudoku2(grid));
				
		
		
	}

}
