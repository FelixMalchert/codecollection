import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client implements Runnable {

	private static int a;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		a=  1;
		new Thread (new Client()).start();
		new Thread (new Client()).start();
		new Thread (new Client()).start();
		new Thread (new Client()).start();
	}



	public void run(){

		try {
			// Scanner eingabe = new Scanner(System.in);	// (*) Wird  benöötigt für die Individuelle  eingabe

			Socket client  = new Socket ("localhost", 8888);	// Client  erstellen+ Angabe wo der Client liegt.
			System.out.println("client  gestartet!");			//Ausgabe die uns sagt wenn der Client gestartet wurde

			//Streams

			OutputStream out = client.getOutputStream(); 	 //Daten die an den Client geschickt werden. Outputstream zum sichern der Eingabe.
			PrintWriter  writer = new PrintWriter(out);	  	 // Zum Abfangen von Zeichen.

			InputStream in =  client.getInputStream();		 //  Daten die vom Client gesendet werden Abfangen.
			BufferedReader reader = new BufferedReader ( new InputStreamReader (in));


			//System.out.print("Eingabe: ");   				// (*)Alles was hier eingegeben wird an Server gesendet
			///String anServer = eingabe.nextLine();		// (*)Er übernimmt die komplette nächste Reihe der Eingabe

			writer.write("Hallo vom "+ a + ". Client \n");	// Daten werden an Server gesendet. \n benutzen damit readLine funktioniert
			writer.flush();									// Aktualisierung um Daten zum Server zu senden.
			a++;

			String s = null;

			while((s=reader.readLine())!=null){				// Ausgabe des buffered reader.

				System.out.println("Empfangen vom Server: " +s);

			}
			reader.close();
			writer.close();
		} catch (IOException e) {

		}   
	}

}
