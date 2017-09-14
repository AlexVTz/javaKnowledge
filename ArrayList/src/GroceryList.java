import java.util.ArrayList;

public class GroceryList {
	private ArrayList<String> groceryList = new ArrayList<>();
	
	public void addGrocery(String item){
		groceryList.add(item);
	}
	
	public void printGroceryList(){
		
		System.out.println("You have a list of size: " + groceryList.size());
		for(String grocery : groceryList){
			System.out.println(grocery);
		}
	}
	
	public void modifyGroceryItem(String groceryItem, String newItem){
		int found = find(groceryItem);
		if(found >= 0){
			modifyGroceryItem(found, newItem);
		}
	}
	
	private void modifyGroceryItem(int item, String groceryItem){
		System.out.print("Grocery Item " + groceryList.get(item) + " is changing to");
		groceryList.set(item, groceryItem);
		System.out.println(groceryList.get(item));
		
	}
	
	private int find(String groceryItem){
		return groceryList.indexOf(groceryItem);
	}
	
	public void removeGroceryItem(String itemToRemove){
		System.out.print(itemToRemove);
		if(groceryList.remove(itemToRemove)){
			System.out.println(" has been removed");
		}
		else{
			System.out.println(" does not exist");
		}
	}
	
	public void findItem(String groceryItem){
		if(groceryList.contains(groceryItem)){
			System.out.println("Se encontró");
		}
		else{
			System.out.println("No se encuentra");
		}
	}
	
	public void processGroceries(ArrayList<String> newArray){
		
	}
	
}
