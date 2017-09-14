import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		GroceryList gL = new GroceryList();
		
		gL.addGrocery("Pelon Pelorico");
		gL.addGrocery("Duvalin");
		gL.addGrocery("Rocaleta");
		
		gL.printGroceryList();
		
		gL.modifyGroceryItem("Duvalin","Mango Enchilado");
		
		gL.printGroceryList();
		
		gL.removeGroceryItem("Pelon Pelorico");
		
		gL.printGroceryList();

	}
	
	public static void processArray(){
		ArrayList<String> newArray = new ArrayList<String>();
		
		
	}

}
