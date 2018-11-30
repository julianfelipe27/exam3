package mundo;


public class Apuesta {

	private Cliente cliente;
	private Caballo caballo;
	private int monto;
	
	

	public Apuesta(Cliente cliente, Caballo caballo,int m) {
		super();
		this.cliente = cliente;
		this.caballo = caballo;
		this.monto=m;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Caballo getCaballo() {
		return caballo;
	}

	public void setCaballo(Caballo caballo) {
		this.caballo = caballo;
	}
	
	
	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}
}
