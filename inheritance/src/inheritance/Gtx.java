package inheritance;

public class Gtx extends Car{
	private int turbo;
	private String model;

	public Gtx(String name, int size, int weight, int doors, int wheels, int trunk, String engine, int gears, 
			int turbo, String model) {
		super(name, size, weight, doors, wheels, trunk, engine, gears);
		this.turbo = turbo;
		this.model = model;
	}

	public int getTurbo() {
		return turbo;
	}

	public void setTurbo(int turbo) {
		this.turbo = turbo;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public void useTurbo(int speed, String direction, int gear){
		super.speed(speed, direction, gear);
		if(this.turbo == 1){
			System.out.println(getName()+" "+getModel()+" used turbo");
			System.out.println("Speed increased to "+(speed+40));
			this.turbo = 0;
		}
		else{
			System.out.println(getName()+" "+getModel()+" is out of turbo");
		}
	}
	
}
