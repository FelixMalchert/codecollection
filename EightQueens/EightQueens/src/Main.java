import java.util.Scanner;

public class Main {

	public static void main(String[] args){
		Scanner console = new Scanner(System.in);
		System.out.print("What size board do you want to use? " );
		int size = console.nextInt(); 
		Chess obj = new Chess(size);
		obj.solver(0);
		obj.printNumberOfSolutions (); 
		console.close();
	}
}
