package mundo;

import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

import javax.net.ssl.SSLSocketFactory;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
public class Cliente{
	
		
	public static final String TRUSTTORE_LOCATION = "C:/Program Files/Java/jre1.8.0_144/bin/clientkeystore";
	private String id;
	AudioInputStream audioInputStream;
	SourceDataLine sourceDataLine;
	private String usuario;
	private String cedula;
	private String caballo;
	private String fecha;
	private String resultado;
	private String monto;
	private boolean gano;
	
	public String primero;
	
	private Coneccion con;

	
	private Caballo cb;

	public Cliente(String id) {
		
		this.id=id;
		
	}

	public Cliente() {

		// TODO Auto-generated method stub
				
				con= new Coneccion();
				System.out.println("SSLClientSocket Started");
				// this an argument like -Djavax.net.ssl.trustStore="keystore"....
				System.setProperty("javax.net.ssl.trustStore", TRUSTTORE_LOCATION);
				SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();
				usuario= Lecturas.leeCadena("Por favor digite su nombre:");
				cedula= Lecturas.leeCadena("Por favor digite su cédula:");
				caballo= Lecturas.leeCadena("Caballos disponibles [1, 2, 3, 4, 5, 6]" +"\n"+ "Ingrese el id del caballo: ");
				cb= new Caballo(caballo);
				monto= Lecturas.leeCadena("Cuanto va a apostar: ");
				Date date= new Date();
				fecha=date.toString();
				gano= false;
				
				try {
						
					
					Socket conexionCliente = sf.createSocket("localhost", 2000);
					Scanner lectorCliente = new Scanner(new InputStreamReader(conexionCliente.getInputStream()));
					PrintWriter escritorCliente = new PrintWriter(conexionCliente.getOutputStream(), true);
					
					
					escritorCliente.println(usuario);
					escritorCliente.println(cedula);
					escritorCliente.println(caballo);
					escritorCliente.println(monto);
					
					Reproducir.iniciar();
					MulticastSocket socket2 = new MulticastSocket(9000);
					InetAddress dir= InetAddress.getByName("228.5.6.7");
					socket2.joinGroup(dir);
					
				
					MulticastSocket socket = new MulticastSocket(8000);
					InetAddress dir2= InetAddress.getByName("225.2.2.2");
					socket.joinGroup(dir2);
					
									

						
						
					
					
					primero=" ";
					
				while(primero!="") {
					
					
					byte[] audioBuffer = new byte[10000];
					DatagramPacket packet
					= new DatagramPacket(audioBuffer, audioBuffer.length);
					socket2.receive(packet);
					
						byte audioData[] = packet.getData();
						InputStream byteInputStream =
						new ByteArrayInputStream(audioData);
						AudioFormat audioFormat = getAudioFormat();
						audioInputStream = new AudioInputStream(
						byteInputStream,
						audioFormat, audioData.length /
						audioFormat.getFrameSize());
						DataLine.Info dataLineInfo = new DataLine.Info(
						SourceDataLine.class, audioFormat);
						sourceDataLine = (SourceDataLine)
						AudioSystem.getLine(dataLineInfo);
						sourceDataLine.open(audioFormat);
						sourceDataLine.start();
						playAudio();
						
					byte  [] msj= new byte[2000];
					DatagramPacket packet2= new DatagramPacket(msj, msj.length, dir2, 8000); 
					socket.receive(packet2);
					
					primero= new String(packet2.getData(), 0, packet2.getLength());
					
					
					byte  [] msj2= new byte[2000];
					DatagramPacket packet3= new DatagramPacket(msj2, msj2.length, dir2, 8000); 
					socket.receive(packet3);
					
					String segundo= new String(packet3.getData(), 0, packet3.getLength());

					
					byte  [] msj3= new byte[2000];
					DatagramPacket packet4= new DatagramPacket(msj3, msj3.length, dir2, 8000); 
					socket.receive(packet4);
					
					String tercero= new String(packet4.getData(), 0, packet4.getLength());

					byte  [] msj4= new byte[2000];
					DatagramPacket packet5= new DatagramPacket(msj4, msj4.length, dir2, 8000); 
					socket.receive(packet5);
					
					String cuarto= new String(packet5.getData(), 0, packet5.getLength());

					byte  [] msj5= new byte[2000];
					DatagramPacket packet6= new DatagramPacket(msj5, msj5.length, dir2, 8000); 
					socket.receive(packet6);

					String quinto= new String(packet6.getData(), 0, packet6.getLength());

					byte  [] msj6= new byte[2000];
					DatagramPacket packet7= new DatagramPacket(msj6, msj6.length, dir2, 8000); 
					socket.receive(packet7);
					
					String sexto= new String(packet7.getData(), 0, packet7.getLength());

					byte  [] msj7= new byte[2000];
					DatagramPacket packet8= new DatagramPacket(msj7, msj7.length, dir2, 8000); 
					socket.receive(packet8);
					
					if(cb.getIdCaballo().equals(primero)) {
						
						
						primero+=" <<";
						gano=true;
					}
					
					
					else if(cb.getIdCaballo().equals(segundo)) {
						
						segundo+=" <<";
						gano=false;
						
					}
					
					else if(cb.getIdCaballo().equals(tercero)) {
						
						
						tercero+=" <<";
						gano=false;

					}
					
					else if(cb.getIdCaballo().equals(cuarto)) {
						
						cuarto+=" <<";
						gano=false;

						
					}
					
					else if(cb.getIdCaballo().equals(quinto)) {
						
						quinto+=" <<";
						gano=false;

					}
					
					else if(cb.getIdCaballo().equals(sexto)) {
						
						sexto+=" <<";
						gano=false;

						}
						

					
					String mensaje="1ro: "+ primero +"\n"+
							"2do: "+ segundo +"\n"+
							"3ro: "+ tercero +"\n"+
							"4to: "+ cuarto + "\n"+
							"5to: "+ quinto +"\n"+
							"6to: "+ sexto+"\n";
							
							
					 

					System.out.println(mensaje);
					
					if(gano) {
						
						resultado="Si";
						
					}
					else
						resultado="No";
					
					
					}
					
					
					
				
					lectorCliente.close();
					escritorCliente.close();
					conexionCliente.close();
					
					socket.close();
					socket2.close();
					
					
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					
				} 
				catch (LineUnavailableException e3) {
					
					e3.printStackTrace();
				}
				
	}
	
	

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		
		return id;
	}

	private AudioFormat getAudioFormat() {
		float sampleRate = 16000F;
		int sampleSizeInBits = 16;
		int channels = 1;
		boolean signed = true;
		boolean bigEndian = false;
		return new AudioFormat(sampleRate, sampleSizeInBits,
		channels, signed, bigEndian);
		}
	
	private void playAudio() {
		byte[] buffer = new byte[10000];
		try {
		int count;
		while ((count = audioInputStream.read(
		buffer, 0, buffer.length)) != -1) {
		if (count > 0) {
		sourceDataLine.write(buffer, 0, count);
		}
		}
		} catch (Exception e) {
		// Handle exceptions
		}
		}

	
	
	public static void main(String []args) {
		
		Cliente c=new Cliente();
	}
}
