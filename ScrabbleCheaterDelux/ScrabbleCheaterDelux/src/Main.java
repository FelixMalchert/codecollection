
import java.io.BufferedReader ;
import java.io.IOException ;
import java.io.InputStreamReader ;

public class Main { public static void main (String[] args){
	System.out.println("Would you like to use the Random letters (1) or the Input letters (2) version? \n" );

	try{

		BufferedReader bufferRead = new BufferedReader (new InputStreamReader (System.in ));
		String br = bufferRead .readLine();

		if (br.equals("1")) {
			Random.random();
		}

		else if (br.equals("2")) { 
			Input.input(); 
		} 
		else { 
			System.out.println("Not a valid choice, please try again!" );
			Main.main(args); 
		}
	} 
	catch(IOException e) { e.printStackTrace ();
	}
}
}
