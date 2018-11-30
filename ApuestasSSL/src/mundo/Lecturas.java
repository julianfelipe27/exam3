package mundo;
import java.io.IOException;

public class Lecturas {

	
	public static void printPrompt(String prompt) {
		
		System.out.println(prompt+ " ");
		System.out.flush();
		
	}
	
	
	public static String leeCadena() {
		
		
		int ch;
		String r="";
		boolean done=false;
		
		while(!done) {
			
			
			try {
				ch= System.in.read();
				
				if(ch< 0 || (char) ch == '\n')
					done=true;
				else if ((char) ch!= '\r')
					r=r+ (char) ch;
			}
			 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		}
		return r;
	}
	
	public static String leeCadena(String prompt) {
		
		printPrompt(prompt);
		return leeCadena();
	}
}
