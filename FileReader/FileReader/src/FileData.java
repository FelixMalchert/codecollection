import java.io.IOException;


public class FileData {

	public static void main(String[] args) throws IOException {

		String file_name ="D:/test.txt";

		try {
			ReadFile file = new ReadFile(file_name);
			String[] aryLines = file.OpenFile();

			int i;
			for(i=0; i < aryLines.length; i++){
				System.out.println(aryLines[i]);
			}
		}
		catch(IOException e){
			System.out.println(e.getMessage() );
		}

		//Write to a file
		try{		
			WriteFile data = new WriteFile(file_name, true);
			data.writeToFile("\n"+"This is another line of text");
		}
		catch(IOException e){
			System.out.println("Text File Written To");
		}
	}

}
