import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataWriter {
	public static void writeLine(String fileName, String line) {
		try {
			// Assume default encoding.
			FileWriter fileWriter = new FileWriter(fileName, true);

			// Always wrap FileWriter in BufferedWriter.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// Note that write() does not automatically
			// append a newline character.
			bufferedWriter.write(line);
			bufferedWriter.newLine();
			// Always close files.
			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file '" + fileName + "'");
		}
	}

	// For unit test
	public static void main(String[] args) {

		// The name of the file to open.
		String fileName = "temp.txt";

		// Note that write() does not automatically
		// append a newline character.
		writeLine(fileName, "Hello there, here is some text.");
		writeLine(fileName, "We are writing the text to the file.");

	}

}
