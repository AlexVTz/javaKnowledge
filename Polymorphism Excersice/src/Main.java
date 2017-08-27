class Car{
	
	private boolean engine;
	private int numberOfCylinders;
	private String name;
	private int wheels;
	/**
	 * @param engine
	 * @param numberOfCylinders
	 * @param name
	 * @param wheels
	 */
	public Car(int numberOfCylinders, String name) {
		this.engine = true;
		this.numberOfCylinders = numberOfCylinders;
		this.name = name;
		this.wheels = 4;
	}
	public boolean isEngine() {
		return engine;
	}
	public int getNumberOfCylinders() {
		return numberOfCylinders;
	}
	public String getName() {
		return name;
	}
	public int getWheels() {
		return wheels;
	}
	
	public void startEngine(){
		System.out.println("Engine has been started");
	}
	
	public void accelerate() {
		System.out.println("Car accelerating");
	}
	
	public void brake() {
		System.out.println("braking");
	}
	
}

class Vento extends Car {

	private String company;
	
	public Vento(int numberOfCylinders, String name) {
		super(numberOfCylinders, name);
		this.company = "Volkswagen";
	}

	public String getCompany() {
		return company;
	}

	@Override
	public void startEngine() {
		super.startEngine();
	}

	@Override
	public void accelerate() {
		super.accelerate();
	}

	@Override
	public void brake() {
		System.out.println(getCompany()+" "+getName()+" is braking");
	}
		
}

class Ford extends Car {

	public Ford(int numberOfCylinders, String name) {
		super(numberOfCylinders, name);
	}

	@Override
	public void accelerate() {
		System.out.println(getClass().getSimpleName() + " " +getName() + " is accelerating");
	}
	
	
	
}


public class Main {
	
	static class Mitsubishi extends Car {

		public Mitsubishi(int numberOfCylinders, String name) {
			super(numberOfCylinders, name);
		}
		
		public void brake(){
			System.out.println(getClass().getSimpleName()+" "+getName()+" has braked");
		}
		
	}

	public static void main(String[] args) {
		Car c, c2, c3;
		
		c= new Vento(6,"Vento");
		
		c.accelerate();
		c.brake();
		
		c2 = new Ford(8,"Lobo");
		c2.startEngine();
		c2.accelerate();
		
		c3 = new Mitsubishi(6,"x320");
		c3.startEngine();
		c3.accelerate();
		c3.brake();
	}

}
