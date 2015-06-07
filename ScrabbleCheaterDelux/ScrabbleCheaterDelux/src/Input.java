import java.io.BufferedReader ;
import java.io.IOException ;
import java.io.InputStreamReader ;

public class Input {
	public static void input() {
		System.out.println("Enter your seven Scrabble letters here: \n" );


		try{

			BufferedReader bufferRead = new BufferedReader (new InputStreamReader (System.in ));
			String testword = bufferRead .readLine();

			HashDict hashDict = new HashDict();
			hashDict.hashWords ();

			MakeSubstring substrings = new MakeSubstring (testword); 
			String[] allTestStrings = substrings .getSubstrings (); 
			System.out.println("The following words can be build from these letters: " + testword + "\n\n"); 

			for (int i = 0; i < allTestStrings .length; i++) { 
				hashDict.scrabbleCheater (allTestStrings [i]);
			}

			hashDict.print();

		}
		catch(IOException e)
		{ 
			e.printStackTrace (); 
		}
	}
}

