import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class ReadWrite{

	public static void main(String[] args) throws IOException{
		//file to be read of
		String file_name = "D:/test.txt";  
		//reads from a file  
		FileReader reader = new FileReader(file_name);   
		int cha = 0; //stores the character
		// read characters from the file till there are no more

		while ((cha = reader.read()) != -1) {  
			System.out.println((char) cha);  
		}  
		if ((cha = reader.read()) == -1) {
			System.out.println("There are no characters to be read!");  
		} 
		reader.close(); //closes the file   

		String text1 = "String and int to a file"; 

		try {  
			File file = new File("D:/StrinInt.txt"); //creates a new file 


			FileWriter writer = new FileWriter(file);

			writer.write(text1 + System.getProperty("line.separator"));   
			writer.write(new Integer(56).toString() + System.getProperty("line.separator")); 
			writer.write(String.valueOf(156)); 
			writer.close();  
		}
		catch (IOException e) { 
			e.printStackTrace(); 

		}
	}
}





















