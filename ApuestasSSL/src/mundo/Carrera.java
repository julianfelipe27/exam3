package mundo;


import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

public class Carrera extends Thread{


	private ArrayList<Caballo> caballosEnCarrera;
	private ArrayList<Apuesta> ap;


	private  final byte audioBuffer[] = new byte[10000];
	private  TargetDataLine targetDataLine;


	private int segundos; 



	public Carrera(int segundos) {

		setupAudio();

		this.segundos=segundos;

		caballosEnCarrera= new ArrayList<Caballo>();	
		ap= new ArrayList<Apuesta>();


	}


	public void agregarApuesta(Apuesta apuesta) {


		ap.add(apuesta);	

	}

	public void agregarCaballo(Caballo c) {


		caballosEnCarrera.add(c);


	}



	//	public boolean isTerminada() {
	//		
	//		ordenarPosiciones();
	//	
	//		if(caballosEnCarrera.get(caballosEnCarrera.size()-1).getVelocidad()>=kilometros) {
	//			
	//			return true;
	//			
	//		}
	//		
	//		
	//		return false;
	//	}
	//	
	public void ordenarPosiciones() {



		Caballo temporal = null;

		for (int i = 0; i < caballosEnCarrera.size(); i++) {
			for (int j = 1; j < (caballosEnCarrera.size() - i); j++) {
				if (caballosEnCarrera.get(j-1).getVelocidad() > caballosEnCarrera.get(j).getVelocidad()) {
					temporal = caballosEnCarrera.get(j-1);
					caballosEnCarrera.set(j-1, caballosEnCarrera.get(j));
					caballosEnCarrera.set(j, temporal) ;
				}
			}

		}


	}
	//	public void setGanador() {
	//		
	//		caballoGanador= caballosEnCarrera.get(0);
	//		
	//		for (int i = 0; i < caballosEnCarrera.size()-1; i++) {
	//			
	//			if(caballoGanador.getVelocidad()<caballosEnCarrera.get(i+1).getVelocidad()) {
	//				
	//				
	//				caballoGanador=caballosEnCarrera.get(i);
	//			}
	//			
	//		}
	//		
	//	}
	//	
	//	public Caballo getGanador() {
	//		
	//		
	//		return caballoGanador;
	//	}
	//	
	//	


	public ArrayList<Caballo> getCaballosEnCarrera() {
		return caballosEnCarrera;
	}


	public void setCaballosEnCarrera(ArrayList<Caballo> caballosEnCarrera) {
		this.caballosEnCarrera = caballosEnCarrera;
	}




	public ArrayList<Apuesta> getAp() {
		return ap;
	}


	public void setAp(ArrayList<Apuesta> ap) {
		this.ap = ap;
	}



	//	public boolean parar(int i) {
	//		
	//		
	//			
	//			if(caballosEnCarrera.get(i).getVelocidad()>=kilometros) {
	//				
	//				return true;
	//			}
	//		
	//		
	//		return false;
	//	}

	public String imprimirResultado() {

		String posiciones="";
		ordenarPosiciones();
		posiciones="Posicion 1: " + caballosEnCarrera.get(5).getIdCaballo()+" en "+ caballosEnCarrera.get(5).getVelocidad()+" seg "+"\n"
				+"Posicion 2: " + caballosEnCarrera.get(4).getIdCaballo()+" en "+caballosEnCarrera.get(4).getVelocidad()+" seg"+"\n"
				+"Posicion 3: " + caballosEnCarrera.get(3).getIdCaballo()+" en "+caballosEnCarrera.get(3).getVelocidad()+" seg"+"\n"
				+"Posicion 4: " + caballosEnCarrera.get(2).getIdCaballo()+" en "+caballosEnCarrera.get(2).getVelocidad()+" seg"+"\n"
				+"Posicion 5: " + caballosEnCarrera.get(1).getIdCaballo()+" en "+caballosEnCarrera.get(1).getVelocidad()+" seg"+"\n"
				+"Posicion 6: " + caballosEnCarrera.get(0).getIdCaballo()+" en "+caballosEnCarrera.get(0).getVelocidad()+" seg";

		return posiciones;
	}

	public void imprimirParcialmente() {

		//	String []c=new String [6];


		ordenarPosiciones();



		//		c[0]= "1ro: "+ caballosEnCarrera.get(0).getIdCaballo();
		//		c[1]="2do: "+ caballosEnCarrera.get(1).getIdCaballo();
		//		c[2]="3ro: "+ caballosEnCarrera.get(2).getIdCaballo();
		//		c[3]="4to: "+ caballosEnCarrera.get(3).getIdCaballo();
		//		c[4]="5to: "+ caballosEnCarrera.get(4).getIdCaballo();
		//		c[5]="6to: "+ caballosEnCarrera.get(5).getIdCaballo();
		//		
		//	
		//		
		//		String imp= c[0]+"\n"+
		//		c[1]+"\n"+
		//		c[2]+"\n"+
		//		c[3]+"\n"+
		//		c[4]+"\n"+
		//		c[5]+"\n";
		//		

		//return imp;
	}
	public void run() {


		int velocidad=0;

		LocalTime tiempo= LocalTime.now();

		try {
			MulticastSocket audio = new MulticastSocket(9000);
			InetAddress dir= InetAddress.getByName("228.5.6.7");
			audio.joinGroup(dir);

			MulticastSocket msj= new MulticastSocket(8000);
			InetAddress dir2= InetAddress.getByName("225.2.2.2");
			msj.joinGroup(dir2);

			
			MulticastSocket mp3= new MulticastSocket(8001);
			InetAddress dir3= InetAddress.getByName("226.2.2.2");
			mp3.joinGroup(dir3);
		
			String ruta= "C:/Users/Camilo Gutiérrez/Downloads/William Tell OvertureFinale (Lone Ranger Future Cut Mix) - Official Video  HD.wav";
			byte [] m= ruta.getBytes();
			DatagramPacket packetm= new DatagramPacket(m,m.length,dir3,8001);
			mp3.send(packetm);



			String primero=null;

			while(LocalTime.now().isBefore(tiempo.plusSeconds(segundos))) {


				int count = targetDataLine.read(
						audioBuffer, 0, audioBuffer.length);
				if (count > 0) {
					DatagramPacket packetau = new DatagramPacket(
							audioBuffer, audioBuffer.length, dir, 9000);
					audio.send(packetau);
				}

				primero= caballosEnCarrera.get(0).getIdCaballo();
				String segundo= caballosEnCarrera.get(1).getIdCaballo();
				String tercero= caballosEnCarrera.get(2).getIdCaballo();
				String cuarto= caballosEnCarrera.get(3).getIdCaballo();
				String quinto= caballosEnCarrera.get(4).getIdCaballo();
				String sexto= caballosEnCarrera.get(5).getIdCaballo();




				for(int i=0;i<caballosEnCarrera.size();i++) {

					int n= (int)(Math.random()*7+1);

					velocidad= caballosEnCarrera.get(i).getVelocidad();

					caballosEnCarrera.get(i).setVelocidad(n+velocidad);




				}
				ordenarPosiciones();

				byte[] pos1= primero.getBytes();
				DatagramPacket packet= new DatagramPacket(pos1, pos1.length, dir2,8000);
				msj.send(packet);

				byte[] pos2= segundo.getBytes();
				DatagramPacket packet2= new DatagramPacket(pos2, pos2.length, dir2,8000);
				msj.send(packet2);

				byte[] pos3= tercero.getBytes();
				DatagramPacket packet3= new DatagramPacket(pos3, pos3.length, dir2,8000);
				msj.send(packet3);

				byte[] pos4= cuarto.getBytes();
				DatagramPacket packet4= new DatagramPacket(pos4, pos4.length, dir2,8000);
				msj.send(packet4);

				byte[] pos5= quinto.getBytes();
				DatagramPacket packet5= new DatagramPacket(pos5, pos5.length, dir2,8000);
				msj.send(packet5);

				byte[] pos6= sexto.getBytes();
				DatagramPacket packet6= new DatagramPacket(pos6, pos6.length, dir2,8000);
				msj.send(packet6);

				//		new SendMessage(ch, imprimirParcialmente());






			}


			primero="";
			byte[] imprimir= primero.getBytes();
			DatagramPacket packet3= new DatagramPacket(imprimir, imprimir.length, dir2, 8000);
			msj.send(packet3);
			
			msj.close();
			audio.close();
			mp3.close();
		}catch(Exception e) {

			e.printStackTrace();

		}





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

	private void setupAudio() {
		try {
			AudioFormat audioFormat = getAudioFormat();
			DataLine.Info dataLineInfo =
					new DataLine.Info(TargetDataLine.class,
							audioFormat);
			targetDataLine = (TargetDataLine)
					AudioSystem.getLine(dataLineInfo);
			targetDataLine.open(audioFormat);
			targetDataLine.start();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(0);
		}
	}	
}
