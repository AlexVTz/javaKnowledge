package firstDuplicate;

import java.util.HashSet;

public class Main {
	
	public static int firstDuplicate(int[] a) {
		
		int finalIndex = a.length;
		int numero = 0;
		boolean duplicate = false;
		
		for(int i = 0; i < a.length; i++){
			for(int j = i; j < a.length - 1; j++){
				if(a[i] == a[j+1]){
					duplicate = true;
					if(finalIndex > j+1){
						finalIndex = j+1;
						numero = a[i];
					}
				}
			}
		}
		
		if(!duplicate)
			return -1;
		else
			return numero;
	}

	public static int firstDuplicateV2(int[] a){
		HashSet<Integer> hashSet = new HashSet<Integer>();
		
		for(int i = 0; i < a.length; i++){
			if(!hashSet.add(a[i])){
				return a[i];
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] a = {2, 3, 3, 1, 5, 2};
		
		System.out.println(firstDuplicateV2(a));
	}

}
