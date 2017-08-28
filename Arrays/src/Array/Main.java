package Array;

import java.util.*;

public class Main {
	
	
	public static void main(String[] args) {
		int[] valores = getValores(5);
		for(int i = 0; i < 5; i++){
			System.out.println("Valor "+valores[i]);
		}

	}
	
	public static int[] getValores(int valor){
		
		int[] valores = new int[valor];
		Random rand = new Random();
		
		for(int i = 0; i < valor; i++){
			valores[i] = rand.nextInt();
		}
		
		return valores;
		
	}

}
