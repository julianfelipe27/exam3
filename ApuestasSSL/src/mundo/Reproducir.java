package mundo;

import java.io.File;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public final class Reproducir extends Thread{

	private static Clip clip;
	private static Reproducir instance =null;
	
	private Reproducir() {
		
	}
	
	public static void iniciar() {
		
		
		if(getInstance().isAlive()==false) {
			
			getInstance().start();
		}

	}
	
	public static Reproducir getInstance() {
        if (instance == null) {
            instance = new Reproducir();
        }
 
        return instance;
    }
	public void run() {
		
		
		try {
			
			MulticastSocket mp3= new MulticastSocket(8001);
			InetAddress dir3= InetAddress.getByName("226.2.2.2");
			mp3.joinGroup(dir3);
		while(true) {	
		
		
		byte[] musica= new byte [2048];
		DatagramPacket packet20= new DatagramPacket(musica, musica.length);
		mp3.receive(packet20);
		File soundFile= AudioUtil.getSoundFile(new String(packet20.getData(),0, packet20.getLength()));
		
		
		   AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();

	          clip.open(ais);
	            clip.start();
	    
	            
	            
		}
	        }catch(Exception e) {
	        	
	        	e.printStackTrace();
	        }
		
		
		
	}
	

	public static Clip getClip() {
		return clip;
	}

	public void setClip( Clip clip) {
		Reproducir.clip = clip;
	}
	
	
}
