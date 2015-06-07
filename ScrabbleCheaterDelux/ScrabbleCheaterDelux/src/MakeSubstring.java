
import java.util.Arrays;
import java.util.HashSet; 
import java.util.Iterator;

public class MakeSubstring {

	private HashSet<String> allPerms;
	private HashSet<String> allSubstrings ;

	/**Create all unique substrings of a String 
	 * @param the String to generate the substrings from 
	 */ 

	MakeSubstring (String st){
		allPerms = new HashSet<String>();
		allSubstrings = new HashSet<String>();

		//Create all possible permutations 
		createAllPermutations ("", st);

		//Take off the last character from every permutation String one by one 
		//Save all results in a set to avoid duplicates 
		Iterator<String> it = allPerms.iterator();

		while(it.hasNext()) {
			allSubstrings .addAll(ShortenString (it.next(), 2)); 
		}
	}
	/**Return all found substrings as an array 
	 * @return The array containing the found substrings 
	 */ 

	public String[] getSubstrings () { 
		return allSubstrings .toArray(new String[allSubstrings .size()]);
	}

	/**Our helper methods for this class starting with a method to 
	 *Create all possible unique permutations of a string
	 *@param base A base to add the permutations to (normally an empty String) 
	 *@param add The String to get the permutations from 
	 */ 

	private void createAllPermutations (String base, String add){ 

		//base case
		if(add.length() <= 1){
			allPerms.add(base + add); 
		}
		else { 
			String tempAdd,tempBase;
			for (int i = 0; i < add.length();i++)
			{
				//remove character at position i 
				tempAdd = add.substring (0, i)+add.substring (i + 1);

				//add that missing character to the new base 
				tempBase = base + add.charAt(i);

				//keep calling this method to get all permutations 
				createAllPermutations (tempBase, tempAdd);
			}
		}
	}

	/**Reduce the String by one letter cycle and save all cycles 
	 * @param st Our String to get the substrings from 
	 * @param shortestString The length of our shortest substring 
	 * @return a Our String array containing all the substrings 
	 */ 

	private HashSet<String> ShortenString (String st, int shortestString ){

		//Making use of a HashSet to avoid having duplicates
		HashSet<String> out = new HashSet<String>();

		//Setting the length of the String to work with 
		int stLength = st.length();
		char[] temp;

		//Reduce by one character each cycle until the string is as short as shortestString
		for (int i = 0; i <(stLength - shortestString );i++) { 
			temp = st.substring (0, stLength - i).toCharArray ();

			//Sort the result array to avoid duplicates 
			Arrays.sort(temp); 
			out.add(String.valueOf(temp));

		} 
		return out;
	}
}

