import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	public static void main (String[] args){

		ExecutorService executor = Executors.newFixedThreadPool(30); //  Maximal 30  Leute im Chat

		ServerSocket server;
		try{
			server = new ServerSocket (8888);  				 // Zum erstellen des Servers
			System.out.println("Server gestartet!");		 // Ausgabe die uns sagt wenn der Server gestartet wurde

			while(true){
				try{
					Socket client = server.accept();				 // Erstellen des Clients

					//Thread t = new Thread(new Handler(client));
					//t.start();
					executor.execute(new Handler(client));
				} 
				catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		catch (IOException e1){
			e1.printStackTrace();
		}
	}
}
