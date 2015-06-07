
public class Random {

	public static void random() {

		HashDict hashDict = new HashDict(); 
		hashDict.hashWords ();

		String testword = HashDict.random7(); 
		MakeSubstring substrings = new MakeSubstring (testword);
		String[] allTestStrings = substrings .getSubstrings ();
		System.out.println("The following words can be build from these letters: " + testword + "\n\n"); 

		for (int i = 0; i < allTestStrings .length; i++) {
			hashDict.scrabbleCheater (allTestStrings [i]);
		}
		hashDict.print();
	}
}
