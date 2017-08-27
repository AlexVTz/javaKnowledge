package pooChallenge;

import java.util.Scanner;

public class Hamburger {
	private String breadRollType;
	private String meat;
	private AdditionalItem[] items;
	private int ingredientsNumber;
	private float price;
	public Scanner sc;
	
	public Hamburger(String breadRollType, String meat, float price) {
		this.breadRollType = breadRollType;
		this.meat = meat;
		this.ingredientsNumber = 0;
		this.price = price;
		this.sc = new Scanner(System.in);
		this.items = new AdditionalItem[4];
	}
	
	public AdditionalItem[] getItems() {
		return items;
	}
	public void setItems(AdditionalItem items, int itemNumber) {
		this.items[itemNumber] = items;
	}
	
	public void listHamburger(){
		System.out.println("Bread Roll: "+getBreadRollType());
		System.out.println("Meat: "+getMeat());
		AdditionalItem items[] = getItems();
		for(int i = 0; i < ingredientsNumber; i++){
			System.out.println(items[i].getItem()+" - $"+items[i].getPrice());
		}
		System.out.println("Price: "+getPrice());
	}
	
	public void selectItems(){
		float itemPrice;
		int selectNewItem;
		String itemName;
		
		for(int i = 0; i < 4; i++){
			System.out.println("Do you want more items? (1/0)");
			selectNewItem = sc.nextInt();
			if(selectNewItem == 0 || ingredientsNumber >= 4){
				break;
			}
			System.out.println("Item Name: ");
			sc.nextLine();
			itemName = sc.nextLine();
			System.out.println("Item Price");
			itemPrice = sc.nextFloat();
			
			setItems( new AdditionalItem(itemName, itemPrice), ingredientsNumber);
			setPrice(getPrice() + itemPrice);
			ingredientsNumber++;
		}
		
	}
	
	public String getBreadRollType() {
		return breadRollType;
	}
	public String getMeat() {
		return meat;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}

class AdditionalItem{
	private String item;
	private float price;
	
	public AdditionalItem(String item, float price) {
		this.item = item;
		this.price = price;
	}

	public String getItem() {
		return item;
	}

	public float getPrice() {
		return price;
	}
	
	
}