import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;


public class Histogram { 

	public static void main(String[] args) throws IOException {  

		int ch = 0; 
		char c, cha;  
		int i, j, k = 0;  
		String str = ""; 
		String star = ""; 
		String file_name = "D:/test.txt";  //file to be read of   
		FileReader reader = new FileReader(file_name); 
		File file = new File("D:/frequency.txt"); //destination file for the frequency statistic  
		PrintStream out = new PrintStream(new FileOutputStream(file)); 
		System.setOut(out); 
		//reads the characters and concatenate them into a string  
		while ((ch = reader.read()) != -1) {  
			str += Character.toString((char) ch);  
		}  
		i = str.length();   //Compare the characters from the file to char primitive type 
		for (c = 'A'; c <= 'z'; c++) {  
			k = 0;  //stores the number of repetition of a character  
			star = ""; //string for the stars used to draw a  histogram  
			for (j = 0; j < i; j++) {  
				cha = str.charAt(j);    
				if (cha == c) {   
					star += "*";    
					k++;     
				}   
			}    
			if (k > 0) {   
				System.out.println("" + ((char) c) + ": " + star + "\n");   
			}  
		}   
		reader.close(); 
	}
} 

