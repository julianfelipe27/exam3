package mundo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClienteThread extends Thread {


	Socket cliente;
	
	BufferedReader lectorCliente;
	PrintWriter escritorCliente;
	
	public ClienteThread(Socket cliente) {
		
		super();
		this.cliente=cliente;
		
	}
	
	
	public void run() {
		

				String usuario= Lecturas.leeCadena("Por favor digite su nombre:");
				String caballo= Lecturas.leeCadena("Caballos disponibles [1, 2, 3, 4, 5, 6]" +"\n"+ "Ingrese el id del caballo: ");
				String monto= Lecturas.leeCadena("Cuanto va a apostar: ");
				
				try {
						
					
					
					 lectorCliente = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
					 escritorCliente = new PrintWriter(cliente.getOutputStream(), true);
					
					escritorCliente.println(usuario);
					escritorCliente.println(caballo);
					escritorCliente.println(monto);
					
					System.out.println(lectorCliente.readLine());
					
					lectorCliente.close();
					escritorCliente.close();
					
					
					
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
	}
}
