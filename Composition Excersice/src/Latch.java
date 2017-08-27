
public class Latch {
	
	private int lock;
	private int internKnob;
	private int exteriorKnob;
	
	public Latch(int lock, int internKnob, int exteriorKnob) {

		this.lock = lock;
		this.internKnob = internKnob;
		this.exteriorKnob = exteriorKnob;
	}
	
	

	public int getLock() {
		return lock;
	}



	public void setLock(int lock) {
		this.lock = lock;
	}



	public int getInternKnob() {
		return internKnob;
	}



	public void setInternKnob(int internKnob) {
		this.internKnob = internKnob;
	}



	public int getExteriorKnob() {
		return exteriorKnob;
	}



	public void setExteriorKnob(int exteriorKnob) {
		this.exteriorKnob = exteriorKnob;
	}



	public void lock(){
		if(getLock() != 0){
			System.out.println("Lock is now on");
		}
	}
	
	public void unlock(){
		if(getLock() != 0){
			System.out.println("Unlocked's been aplied");
		}
	}

}
