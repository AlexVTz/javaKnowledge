package pooChallenge;

import java.util.Scanner;

public class HealthyBurger extends Hamburger{
	private AdditionalItem[] healthyItems;
	private int healthyIngredients;

	public HealthyBurger(String breadRollType, String meat, float price) {
		super(breadRollType, meat, price);
		this.healthyItems = new AdditionalItem[2];
		this.healthyIngredients = 0;
		this.sc = new Scanner(System.in);
	}
	
	public void listHealthyHamburger(){
		listHamburger();
		AdditionalItem[] healthyItem = getHealthyItems();
		for(int i = 0; i < healthyIngredients; i++){
			System.out.println(healthyItem[i].getItem()+" - $"+ healthyItem[i].getPrice());
		}
		System.out.println("Plus extra ingredients: "+ getPrice());
	}
	
	public void selectHealthyItems(){
		selectItems();
		float itemPrice;
		int selectNewItem;
		String itemName;
		
		for(int i = 0; i < 2; i++){
			System.out.println("Do you want more items? (1/0)");
			selectNewItem = sc.nextInt();
			if(selectNewItem == 0 || healthyIngredients >= 2){
				break;
			}
			System.out.println("Item Name: ");
			sc.nextLine();
			itemName = sc.nextLine();
			System.out.println("Item Price");
			itemPrice = sc.nextFloat();
			
			setHealthyItems( new AdditionalItem(itemName, itemPrice), healthyIngredients);
			setPrice(getPrice() + itemPrice);
			healthyIngredients++;
		}
	}

	public AdditionalItem[] getHealthyItems() {
		return healthyItems;
	}

	public void setHealthyItems(AdditionalItem healthyItems, int healthyIngredient) {
		this.healthyItems[healthyIngredient] = healthyItems;
	}
	
	
	

}
