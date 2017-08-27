package inheritance;

public class Car extends Vehicle{
	
	private int doors;
	private int wheels;
	private int trunk;
	private String engine;
	private int gears;
	
	public Car(String name, int size, int weight, int doors, int wheels, int trunk, String engine, int gears) {
		super(name, size, weight);
		this.doors = doors;
		this.wheels = wheels;
		this.trunk = trunk;
		this.engine = engine;
		this.gears = gears;
	}
	
	public void move(int speed){
		System.out.println(getName()+" is movig at "+speed);
	}
	
	public void steering(String direction) {
		System.out.println(getName()+" going "+direction);
	}
	
	public void changeGear(int gear) {
		System.out.println("Changing to gear "+gear);
	}
	
	public boolean speed(int speed, String direction, int gear){
		move(speed);
		steering(direction);
		if(gear > this.gears){
			System.out.print(getName()+" engine "+getEngine() + " is not capable of handling "+gear+" gear");
			return false;
		}
		else{
			changeGear(gear);
			return true;
		}
	}

	public int getDoors() {
		return doors;
	}

	public int getWheels() {
		return wheels;
	}

	public int getTrunk() {
		return trunk;
	}

	public String getEngine() {
		return engine;
	}

	public int getGears() {
		return gears;
	}
	
}
