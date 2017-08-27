
public class Window {
	
	private Dimension dimensions;
	private String glassType;

	
	public Window(Dimension dimensions, String glassType) {

		this.dimensions = dimensions;
		this.glassType = glassType;
	}


	public Dimension getDimensions() {
		return dimensions;
	}


	public void setDimensions(Dimension dimensions) {
		this.dimensions = dimensions;
	}


	public String getGlassType() {
		return glassType;
	}


	public void setGlassType(String glassType) {
		this.glassType = glassType;
	}
	
	public void openWindow(){
		System.out.println("Window opened");
	}
	
	public void closeWindow(){
		System.out.println("Window closed");
	}
	
	
}
