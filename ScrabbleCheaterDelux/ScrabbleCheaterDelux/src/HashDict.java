
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList ; 
import java.util.Iterator;
import java.util.Random;


public class HashDict {

	public int tableSize = 0;//starting size of our hash table 
	private ArrayList <String> []hashDict;//setting up the hash table 
	private static Random random = new Random();


	//For the statistics
	public int mostCollisions = 0;

	/**Read in a file containing words & create a hashtable for storing them 
	 *@return Our hashtable as an array 
	 */

	@SuppressWarnings ("unchecked" ) 

	public  ArrayList <String>[] hashWords() {

		String [] dict = readInFile ("Wordlist.txt" ); 
		int numberOfWords = dict.length;


		//Calling up a prime number to generate the hashtable 
		tableSize = getNextPrime (numberOfWords );
		hashDict = ((ArrayList <String>[]) new ArrayList [tableSize ]);

		for (int i = 0; i < numberOfWords ; i++) {
			double hashCode = getHashCode (dict[i]);



			//Getting the word's position within the hashtable 
			int position =(int)(hashCode % tableSize ); 

			if(hashDict[position] ==null){
				hashDict[position]=new ArrayList <String>(); 
			}
			hashDict[position].add(dict[i]);

			//For our statistics
			//collisions += hashDict[position].size() - 1;

			if(hashDict[position].size() > mostCollisions ){ 
				mostCollisions = hashDict[position].size();
			}
		}

		return hashDict;
	}


	/**Our lookup method to show all permutations and collisions of a word 
	 * @param word Our word or array of characters we want to check 
	 * @return an ArrayList of all permutations and collisions
	 */

	public ArrayList <String> lookup(String word){
		double hashCode = getHashCode (word);

		//Get position within our hashtable 
		int position =(int)(hashCode % tableSize );
		return hashDict[position];
	}


	/** Our Scrabble cheater method 
	 */ 

	public void scrabbleCheater (String letters){
		ArrayList <String> permutations = lookup(letters); 
		if (permutations != null){ 
			Iterator<String> it = permutations .iterator(); 

			while (it.hasNext()) { 
				String out = it.next(); 
				if (isPermutation (out, letters)) {
					System.out.println(out); 
				} 
			}
		} 
	}

	/**Below are all our helper methods starting with this one 
	 * Just a quick print method for our number of collisions
	 */ 

	public void print() {

		System.out.println("\n Size of Hash Table: " + tableSize ); 
		System.out.println("\n Number of Max Collisions: " + mostCollisions );
	}

	/**Our file reader needed read in the word list (separated by blanks or a new line) 
	 * to fill the dictionary hashtable 
	 * @param Name of the word list file 
	 */ 

	private String[] readInFile (String file_name ){
		ArrayList <String> output = new ArrayList <String>(); 

		try {
			//Open our word list file 
			FileInputStream fstream = new FileInputStream (file_name );


			//Set up the actual data input to the reader
			DataInputStream in = new DataInputStream (fstream);
			BufferedReader br = new BufferedReader (new InputStreamReader (in)); 
			String strLine;

			//Now read the file line by line
			while ((strLine = br.readLine()) != null){ 
				String[]words = strLine.split (" "); 
				for (int i = 0; i < words.length; i++) { 
					output.add(words[i]); 
				} 
			} 
			//Make sure the input stream is closed when done 
			in.close();
		}


		catch (Exception e){
			//Catch exception should we get one and print it to the console
			System.err.println ("Error: " + e.getMessage ());
		}
		return output.toArray(new String[output.size()]);
	}

	/**Our getPrime method to generate the hashtable for the dictionary 
	 * @param number is our starting number so to speak 
	 */ 

	private int getNextPrime (int i){

		//keep going until a prime number is found 
		while (true){ 
			if (i == 2) return i;
			int n = 2;
			while (n < i){ if (i%n == 0){ return i; 
			} 
			n++; 
			} 
			i++;
		} 
	}

	/**Calculate a unique number to use as a String representation 
	 * @param st is the string presented as a single letter string array
	 * @return Our new hashNumber
	 */

	private double getHashCode (String word){

		//Normalize our word & save it as an array for sorting
		word = word.toLowerCase ();
		char [] st = word.toCharArray ();

		//The sorting also maps any anagrams of our word to the same hash number
		Arrays.sort(st);

		int length = st.length; double hashCode = 0; 

		for (int i = 0; i < length; i++) { 
			char ch = st[i]; hashCode += Math.pow(ch*31, length - i + 1);
		}
		return hashCode;
	}

	/**Our permutation helper for the Scrabble cheater method
	 * @param word1 First String for our comparison 
	 * @param word2 Second String for our comparison 
	 * @return Whether we have a permutation 
	 */

	private boolean isPermutation (String word1, String word2){ 
		double code1 = getHashCode (word1);
		double code2 = getHashCode (word2);
		return (code1 == code2); 
	}

	/**Generate a random seven letters String 
	 *@return said String 
	 */

	public static String random7() {

		String out= " ";
		for (int i = 0; i < 7; i++) { 
			int number = random.nextInt(26);

			//Alphabet has 26 possible letters 
			char letter =(char)(number + 65);

			//65 is the ASCII equivalent of the letter A our starting point 
			out += String.valueOf(letter);
		} 
		return out;
	}
}
