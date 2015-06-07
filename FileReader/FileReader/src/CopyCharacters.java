
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyCharacters {
	public static void main(String[] args) throws IOException {

		FileReader inputStream = null;
		FileWriter outputStream = null;

		try {
			inputStream = new FileReader("D:/test.txt");
			outputStream = new FileWriter("D:/frequency.txt");

			int c;
			while ((c = inputStream.read()) != -1) {
				outputStream.write(c);


				char [] a = new char[1];
				outputStream.write(a); // reads the content to the array
				// for(char b : a)
				System.out.print(a); //prints the characters one by one


			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}
}