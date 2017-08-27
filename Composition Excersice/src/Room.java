
public class Room {
	
	private Window window;
	private Door door;

	
	public Room(Window window, Door door) {

		this.window = window;
		this.door = door;
	}
	
	//1 open, 0 close
	public void useDoor(int instruction){
		switch(instruction){
			case 0:
				door.closeDoor();
				break;
			case 1: 
				door.openDoor();
				break;
			default:
				System.out.println("What you doing dude?");
				break;
		}
	}
	
	public void useWindow(int instruction){
		switch(instruction){
			case 0:
				window.closeWindow();
				break;
			case 1: 
				window.openWindow();
				break;
			default:
				System.out.println("What you doing dude?");
				break;
		}
	}
	
}
