package mundo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.SSLServerSocketFactory;
import javax.sound.sampled.LineUnavailableException;

public class Servidor  extends Thread{

	public static final String KEYSTORE_LOCATION = "C:/Program Files/Java/jre1.8.0_144/bin/clientkeystore";
	public static final String KEYSTORE_PASSWORD = "123456";

	
	private  ArrayList<Apuesta>ap= new ArrayList<Apuesta>() ;
	private  List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<ClientHandler>());;
	private  Carrera carrera;
	public static void main(String[] args) throws SocketException, LineUnavailableException  {
	
		new Servidor();
	}
	
	public Servidor() throws LineUnavailableException {
		


		// TODO Auto-generated method stub
		System.setProperty("javax.net.ssl.keyStore", KEYSTORE_LOCATION);
		System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASSWORD);
		SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		
		
			LocalTime n = LocalTime.now();
			System.out.println(n);
		
			
		try {
			ServerSocket servidor = ssf.createServerSocket(2000);
			System.out.println("SSLServerSocket Started in "+ servidor.getLocalPort());
			Socket conexionServidor=null;
			ServidorThread hiloServer=null;
				
				
				
				while(LocalTime.now().isBefore(n.plusSeconds(15)))
				{
				
					conexionServidor= servidor.accept();
					 hiloServer= new ServidorThread(conexionServidor,ap);
				
				hiloServer.start();
				
				ClientHandler ch= new ClientHandler(conexionServidor);
				clients.add(ch); 
				
				
				
			}
				conexionServidor.close();
			
				carrera=new Carrera(30);
				carrera.getCaballosEnCarrera().add(new Caballo("1"));
				carrera.getCaballosEnCarrera().add(new Caballo("2"));
				carrera.getCaballosEnCarrera().add(new Caballo("3"));
				carrera.getCaballosEnCarrera().add(new Caballo("4"));
				carrera.getCaballosEnCarrera().add(new Caballo("5"));
				carrera.getCaballosEnCarrera().add(new Caballo("6"));
				
				carrera.setAp(ap);
				System.out.println("Carrera inicia en 5 segundos");
				sleep(1000);
				System.out.println("Carrera inicia en 4 segundos");
				sleep(1000);
				System.out.println("Carrera inicia en 3 segundos");
				sleep(1000);
				System.out.println("Carrera inicia en 2 segundos");
				sleep(1000);
				System.out.println("Carrera inicia en 1 segundos");
				sleep(1000);
				
				
				
				System.out.println("Inició la carrera");
				
				servidor.close();
				
				carrera.start();
				
				while(carrera.isAlive()) {
					

				}
				
				
				System.out.println("Terminó la carrera");
				System.out.println(generarReporteCaballos(ap));
				
				hiloServer.getCliente().close();
				
						
			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(InterruptedException e2) {
			
			
			e2.printStackTrace();
		}
	
		
	}
				
	




			
	
	
		
	
		public static  String generarReporteCaballos(ArrayList<Apuesta> ap) {
		
		int monto1=0;
		int monto2=0;
		int monto3=0;
		int monto4=0;
		int monto5=0;
		int monto6=0;

		for (int i = 0; i < ap.size(); i++) {
			
			if(ap.get(i).getCaballo().getIdCaballo().equals("1")) {
				
				monto1+= ap.get(i).getMonto();
			}
			
			else if(ap.get(i).getCaballo().getIdCaballo().equals("2")) {
				
				monto2+= ap.get(i).getMonto();
			}
			
			else if(ap.get(i).getCaballo().getIdCaballo().equals("3")) {
				
				monto3+= ap.get(i).getMonto();
			}
			
			else if(ap.get(i).getCaballo().getIdCaballo().equals("4")) {
				
				monto4+= ap.get(i).getMonto();
			}
			
			else if(ap.get(i).getCaballo().getIdCaballo().equals("5")) {
				
				monto5+= ap.get(i).getMonto();
			}
			
			else if(ap.get(i).getCaballo().getIdCaballo().equals("6")) {
				
				monto6+= ap.get(i).getMonto();
			}
			
		}
		 String cadena= "Caballo 1: " + monto1 + "-" +"Caballo 2: " + monto2 +"-"+"Caballo 3: " + monto3 +"-"+
				"Caballo 4: " + monto4 +"-"+
				"Caballo 5: " + monto5 +"-"+
				"Caballo 6: " + monto6 ;
				
		 return cadena;
	
	}
		
		
		
	

}
