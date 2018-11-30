package mundo;

public class Caballo {

	private String idCaballo;
	private int velocidad;
	
	public Caballo(String id) {
		
		this.idCaballo=id;
	}
	
	

	

	public String getIdCaballo() {
		return idCaballo;
	}

	public void setIdCaballo(String idCaballo) {
		this.idCaballo = idCaballo;
	}

	public void setVelocidad(int velocidad) {
		
		this.velocidad=velocidad;
	}

	public int getVelocidad() {
		
		return velocidad;
	}

	public String imprimir() {
		
		
		String camino="Caballo "+idCaballo+":"+"";
		
		for(int i=0;i<velocidad;i++) {
			
			camino+="-";
		}
		
		return camino;
	}
	
}
