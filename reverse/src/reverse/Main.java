package reverse;



public class Main {
	
//	public static String reverseSentence(String sentence){
//		
//		String reverse = "";
//		boolean first = false;
//		int end;
//		
//		
//		for(int i = sentence.length() - 1; i >= 0; i--){
//			end = i;
//			if(sentence.charAt(i) == ' ' && first == false){
//				reverse = sentence.substring(i+1, sentence.length());
//				end = i-1;
//				first = true;
//				return reverse;
//			}
//			else if(sentence.charAt(i) == ' ' && first == true)
//			{
//				reverse += sentence.substring(i+1, end);
//				return reverse;
//			}
//			
//		}
//		
//		return reverse;
//	}
	
public static String reverseSentence(String sentence){
		
		String reverse = "";
		boolean espacio = false;
		int fin = sentence.length()-1;
		int index = -1;
		int i;
		
		for(i = 0; i <= fin; i++){
			if(sentence.charAt(i) == ' '){
				index = i;
				espacio = true;
			}
			
			if(i == fin-1 && espacio){
				reverse += sentence.substring(index+1, fin+1) + " ";
				fin =  index-1;
				i = 0;
				index = -1;
				espacio = false;
			}
			else if(i == fin-1 && !espacio){
				reverse += sentence.substring(index+1, fin+1);
				fin =  0;
				i = 0;
				index = -1;
				espacio = false;
			}
			else if(i == fin && !espacio){
				reverse += sentence.substring(i, fin+1);
			}
		}
		
		if(fin < i){
			reverse += sentence.substring(fin, i);
		}
		
		return reverse;
	}

	static int bbqSticks(int k, int[] ingredients) {
		int max = 0;
		if(k == ingredients.length){
			for(int i = 0; i < ingredients.length; i++){
				if(max < ingredients[i]){
					max = ingredients[i];
				}
			}
		}
		
		return max;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] sticks = {10, 5, 12};
		
		System.out.println(bbqSticks(3 , sticks) );

	}

}
