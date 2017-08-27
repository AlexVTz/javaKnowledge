package inheritance;

public class Dog extends Animal{
	
	private int eyes;
	private int legs;
	private int tail;
	private int teeth;
	private String coat;
	
	public Dog(String name, int size, int weight, int eyes, int legs, int tail, int teeth, String coat) {
		super(name, 1, 1, size, weight);
		this.eyes = eyes;
		this.legs = legs;
		this.tail = tail;
		this.teeth = teeth;
		this.coat = coat;
	}
	
	public void chew(){
		System.out.println(getName()+" is Chewing");
	}

	@Override
	public void eat() {
		System.out.println(getName()+" is eating");
		chew();
		super.eat();
	}

	public int getEyes() {
		return eyes;
	}

	public void setEyes(int eyes) {
		this.eyes = eyes;
	}

	public int getLegs() {
		return legs;
	}

	public void setLegs(int legs) {
		this.legs = legs;
	}

	public int getTail() {
		return tail;
	}

	public void setTail(int tail) {
		this.tail = tail;
	}

	public int getTeeth() {
		return teeth;
	}

	public void setTeeth(int teeth) {
		this.teeth = teeth;
	}

	public String getCoat() {
		return coat;
	}

	public void setCoat(String coat) {
		this.coat = coat;
	}
	
	

}
