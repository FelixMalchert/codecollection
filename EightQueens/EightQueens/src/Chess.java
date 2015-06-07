public class Chess{

	private int[] Permutations ;
	private boolean[] Occupied;
	private int numberOfSolutions;


	// Creates our Chessboard with a size defined via user input 

	public Chess(int n){

		Permutations = new int[n];
		Occupied = new boolean[n]; 
		numberOfSolutions = 0;

		for (int i = 0; i < n; i++) {
			Permutations [i]=- 1;
			Occupied[i]=false; }
	}

	/**Our solving method including our permutations array which allows us to keep track
	 *of all fields on the board and their status e.g. occupied, invalid or free to use 
	 *@param location The column where the next queen is placed */

	public void solver(int location){
		int i;

		// If a viable solution has been found print it to the console
		if (location == Permutations .length){ printSolutions ();
		numberOfSolutions ++;
		}

		// Looking through unoccupied spaces to place the next queen
		for (i = 0; i < Permutations .length; i++)
		{

			// Unless this row has not been occupied don't bother trying 
			if (Occupied[i] ==false){


				// Found a suitable spot for another queen so put one down
				// And mark the spot as inhabitable for future checks 
				if (!isValid(location, i)) {
					Permutations [location]=i;
					Occupied[i]=true;

					// Now use recursion to solve the board
					solver(location+1); 
					Occupied[i]=false;
				}
			}
		}
	}
	// Checking to ensure that the current spot is not in the way of any 
	// other queens should they move (which is diagonally of course)

	private boolean isValid(int location, int row){
		int i;
		for (i = 0; i < location; i++)
			if (Math.abs(location - i) ==Math.abs(Permutations [i]-row)) 
				return true;
		return false;
	}

	// This is for printing out all possible solutions for the requested board size

	private void printSolutions () {
		int i,j;
		System.out.println("\n" + "Here is a solution:\n" );
		for (i = 0; i < Permutations .length; i++) {
			for (j = 0; j < Permutations .length; j++) {
				if (Permutations [j] ==i) System.out.print("Q ");
				else System.out.print("* ");
			}
			System.out.println();
		}
	}

	// This prints out the total number of solutions which are possible 

	public void printNumberOfSolutions () { 
		System.out.println("There were " + numberOfSolutions + " solutions." );
	}
}