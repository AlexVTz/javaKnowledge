package pooChallenge;

public class DeluxeBurger extends Hamburger{
	
	private Soda soda;
	private FrenchFries fries;

	public DeluxeBurger(String breadRollType, String meat, float price) {
		super(breadRollType, meat, price);
	}
	
	public void selectExtras(){
		selectItems();
		soda = new Soda("Grande", "Coca Cola", 2.5);
		fries = new FrenchFries("Medianas",2);
		
	}
	
	public void listDeluxeBurger(){
		listHamburger();
		if(soda != null && fries != null){
			System.out.println("Soda "+soda.getName()+" size "+soda.getSize()+" $"+soda.getPrice());
			System.out.println("Fries: "+fries.getSize()+" $"+fries.getPrice());
			System.out.println("Total Price: "+ (getPrice()+soda.getPrice()+fries.getPrice()));
		}
		
	}

}

class Soda {
	String size;
	String name;
	double price;
	
	public Soda(String size, String name, double price) {
		this.size = size;
		this.name = name;
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
		
}

class FrenchFries {
	String size;
	float price;
	
	public FrenchFries(String size, float price) {
		this.size = size;
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public float getPrice() {
		return price;
	}
	
}
