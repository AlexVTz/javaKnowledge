class Movie {
	private String name;

	public Movie(String name) {
		super();
		this.name = name;
	}
	
	public String plot(){
		return "No plot here";
	}

	public String getName() {
		return name;
	}
	
}

class StarWars extends Movie{
	public StarWars() {
		super("Star Wars");
	}
	
	public String plot(){
		return "A galaxy and jedis";
	}
}

class Jaws extends Movie {

	public Jaws() {
		super("Jaws");
	}
	
	public String plot(){
		return "A shark that kills people";
	}
	
}

class HarryPotter extends Movie {
	
	public HarryPotter() {
		super("Harry Potter");
	}
	
	public String plot(){
		return "A school of mages and the chosen boy";
	}
	
}

class Mib extends Movie {
	public Mib() {
		super("Men In Black");
	}
	
	public String plot(){
		return "An agency that hunts aliens";
	}
}

class Forgetable extends Movie {
	public Forgetable() {
		super("Forgetable");
	}
	
}


public class Main {
	
	public static void main(String args[]) {
		
		for(int i = 1; i <= 10; i++){
			Movie movie = generateRandom();
			System.out.println("The Movie #" + i + " - " + movie.getName() 
								+ "\n Plot: " + movie.plot());
		}
		
	}
	
	public static Movie generateRandom(){
		int randomNumber = (int) (Math.random() * 5) + 1;
		switch(randomNumber){
			case 1:
				return new StarWars();
			case 2:
				return new Jaws();
			case 3:
				return new HarryPotter();
			case 4:
				return new Mib();
			case 5:
				return new Forgetable();
		}
		return null;
	}
	
}
