
public class Messi {
	
	private IEquipo equipo;
	
	public Messi(){};
	
	public Messi(IEquipo equipo){
		this.equipo = equipo;
	}
	
	public void setEquipo(IEquipo equipo){
		this.equipo = equipo;
	}
	
	public void mostrarEquipo(){
		equipo.mostrar();
	}

}
