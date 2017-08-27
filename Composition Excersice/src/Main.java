
public class Main {

	public static void main(String[] args) {
		Dimension windowDimension = new Dimension(100,80,5);
		Window window = new Window(windowDimension, "Template Glass");
		
		Dimension doorDimension = new Dimension(220,100,10);
		Latch latch = new Latch(1,1,1);
		Door door = new Door(doorDimension,latch, "Wood");
		
		Room room = new Room(window, door);
		
		room.useDoor(1);
		room.useWindow(0);

	}

}
