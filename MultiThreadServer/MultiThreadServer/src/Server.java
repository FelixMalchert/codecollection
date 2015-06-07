import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLEditorKit;

public class Server {
	ServerSocket server;
	ArrayList<PrintWriter> list_clientWriter;

	final int LEVEL_ERROR = 1; //fields für die unterschiedliche farbgebung auf der console
	final int LEVEL_NORMAL = 0;


	public static void main(String[] args) {// neuer server wird instanziiert , solange der server läuft wird nach clients gelauscht
		Server s = new Server();
		if (s.runServer()) {
			s.listenToClients();
		} else {
			// Do nothing
		}
	}

	public class ClientHandler implements Runnable {
		Socket client;
		BufferedReader reader;

		public ClientHandler(Socket client) {
			try {
				this.client = client;// erstellter client wird hier zugewiesen und erhält reader zum lesen der nachricht
				reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			String nachricht;

			try {
				while((nachricht = reader.readLine()) != null) {
					appendTextToConsole("Vom Client: \n" + nachricht, LEVEL_NORMAL);
					sendToAllClients(nachricht);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void listenToClients() {
		while(true) { // da der server läuft und true zurück gibt , hier endlosschleife d.f. ständiges lauschen nach clients
			try {
				Socket client = server.accept();

				PrintWriter writer = new PrintWriter(client.getOutputStream()); // neuer client mit writer wird erstellt und im array gespeichert
				list_clientWriter.add(writer);

				Thread clientThread = new Thread(new ClientHandler(client));
				clientThread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}              
		}
	}
	public boolean runServer() {
		try {
			server = new ServerSocket(8888);
			appendTextToConsole("Server wurde gestartet!", LEVEL_ERROR); 

			list_clientWriter = new ArrayList<PrintWriter>(); // arraylist von Printwritern wird implementiert
			return true; // wenn server gestarted auf true gesetzt
		} catch (IOException e) {
			appendTextToConsole("Server konnte nicht gestartet werden!", LEVEL_ERROR);
			e.printStackTrace();
			return false;
		}
	}

	public void appendTextToConsole(String message, int level) {
		if(level == LEVEL_ERROR) {
			System.err.println(message + "\n"); // err.print für rot geprinteten Text auf der console
		} else {
			System.out.println(message + "\n"); 
		}
	}

	public void sendToAllClients(String message) {
		Iterator it = list_clientWriter.iterator(); // Iterator zeigt auf die writer und checked ob sie text haben 

		while(it.hasNext()) {
			PrintWriter writer = (PrintWriter) it.next();
			writer.println(message);
			writer.flush();
		}
	}
}