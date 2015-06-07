import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class Handler implements Runnable{


	private Socket client;

	public Handler (Socket client){
		this.client = client;		
	}

	public void run(){
		try{
			//Streams

			OutputStream out = client.getOutputStream(); 	 
			PrintWriter  writer = new PrintWriter(out);	  	 // Zum Abfangen von Zeichen.

			InputStream in =  client.getInputStream();		 
			BufferedReader reader = new BufferedReader ( new InputStreamReader (in));

			String s  =  null;
			while((s=reader.readLine())!=null){				
				writer.write(s+ "\n");						//  Das String wird an den Writer �bergeben. \n benutzen damit readLine funktioniert
				writer.flush();								// Aktualisierung der Daten.
				System.out.println("Empfangen vom Client: " +s);

			}

			writer.close();					// Um die Streams wieder zu schlie�en.
			reader.close();

			client.close();
		}
		catch (Exception  e){

		}
	}
}
