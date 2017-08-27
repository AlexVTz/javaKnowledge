
public class Door {
	
	private Dimension dimensions;
	private Latch latch;
	private String material;
	
	
	public Door(Dimension dimensions, Latch latch, String material) {
		this.dimensions = dimensions;
		this.latch = latch;
		this.material = material;
	}

	public Dimension getDimensions() {
		return dimensions;
	}


	public void setDimensions(Dimension dimensions) {
		this.dimensions = dimensions;
	}


	public Latch getLatch() {
		return latch;
	}


	public void setLatch(Latch latch) {
		this.latch = latch;
	}


	public String getMaterial() {
		return material;
	}


	public void setMaterial(String material) {
		this.material = material;
	}
	
	public void openDoor(){
		latch.unlock();
		System.out.println("Door Opened");
	}
	
	public void closeDoor(){
		System.out.println("Door Closed");
		latch.lock();
	}
	
}
