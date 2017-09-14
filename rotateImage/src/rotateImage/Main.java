package rotateImage;

public class Main {
	
	public static int[][] rotateImage(int[][] a){
		
		int rows = a.length;
		int columns = a[0].length;
		int[][] newImage = new int[rows][columns]; 
		int columna = columns -1;
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				newImage[j][columna] = a[i][j];
			}
			columna--;
		}
		
		
		return newImage;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][] a = {{1, 2, 3},
		             {4, 5, 6},
		             {7, 8, 9}};
		
		System.out.println(rotateImage(a)); 

	}

}
