package mundo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorThread extends Thread{

	private Socket cliente;
	private BufferedReader lectorServidor;
	private PrintWriter escritorServidor;
	private ArrayList<Apuesta>ap;
	
	
	public ServidorThread(Socket solicitud, ArrayList<Apuesta >ap) {
	super();
	cliente=solicitud;
	this.ap=ap;	
	}
	
		
	public void run() {
		
		
		try {
		
		
		lectorServidor =new BufferedReader(new InputStreamReader(cliente.getInputStream()));
		escritorServidor= new PrintWriter(cliente.getOutputStream(),true);
		String nombre= lectorServidor.readLine();
		String caballo= lectorServidor.readLine();
		int monto= Integer.parseInt(lectorServidor.readLine());
		Apuesta a= new Apuesta(new Cliente(nombre), new Caballo(caballo), monto);
		
		ap.add(a);
		
		lectorServidor.close();
		escritorServidor.close();
	
		
		}
		catch(IOException e) {
			
			System.err.println("Imposible procesar la apuesta, tiempo excedido "+ "\n");
		} 
		
		
	}

	public Socket getCliente() {
		return cliente;
	}

	public void setCliente(Socket cliente) {
		this.cliente = cliente;
	}

	public BufferedReader getLectorServidor() {
		return lectorServidor;
	}

	public void setLectorServidor(BufferedReader lectorServidor) {
		this.lectorServidor = lectorServidor;
	}

	public PrintWriter getEscritorServidor() {
		return escritorServidor;
	}

	public void setEscritorServidor(PrintWriter escritorServidor) {
		this.escritorServidor = escritorServidor;
	}

	public ArrayList<Apuesta> getAp() {
		return ap;
	}

	public void setAp(ArrayList<Apuesta> ap) {
		this.ap = ap;
	}
	
	
	
	
	
}
