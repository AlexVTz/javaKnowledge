
public class Main {

	public static void main(String[] args) {
		
		Dimension dimensions = new Dimension(20,20,5);
		Case theCase = new Case("220B", "Dell", "240", dimensions);
		
		Monitor theMonitor = new Monitor("27 inch", "Acer", 27, new Resolution(2540, 1440));
		
		Motherboard theMotherboard = new Motherboard("BJ-200", "Asus", 4, 6, "v2.44");
		
		Pc thePc = new Pc(theCase, theMonitor, theMotherboard);
		
		thePc.getMonitor().drawPixelAt(2, 3, "black");
		thePc.getMotherboard().loadProgram("Google Chrome");
		thePc.getTheCase().pressPowerButton();

	}

}
