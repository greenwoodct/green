import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class DataReader {
	public static Map<String, Boolean> readAll(String fileName) {
		Map<String, Boolean> outMap = new HashMap<String, Boolean>();
		

        try {
        	// This will reference one line at a time.
            String line = null;
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	if (line.trim().length() > 0) {
            		outMap.put(line, true);
            	}
            }   
            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            // Ignore when the file does not exist              
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");
        }
        return outMap;
		
	}
	// Simple unit tests
	public static void main(String [] args) {
        // The name of the file to open.
		Map<String, Boolean> readMap = readAll( "temp.txt");
		for (String line : readMap.keySet()) {
			System.out.println(line);
		}
    }
}
