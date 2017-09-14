package firstNotRepeatingCharacter;

import java.util.HashSet;

public class Main {
	
	public static char firstNotRepeatingCharacter(String s){
		
		HashSet<Character> normalSet = new HashSet<Character>();
		HashSet<Character> repeatedSet = new HashSet<Character>();
		
		for(int i = 0; i < s.length(); i++){
			if(normalSet.add( s.charAt(i) ) == false){
				repeatedSet.add( s.charAt(i) );
			}
		}
		for(int i = 0; i < s.length(); i++){
			if(!repeatedSet.contains( s.charAt(i) )){
				return s.charAt(i);
			}
		}
		
		
		return '_';
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String s = "abacabad";
		
		System.out.println(firstNotRepeatingCharacter(s));
	}

}
