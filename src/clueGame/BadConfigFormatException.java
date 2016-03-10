package clueGame;

import java.io.IOException;
import java.io.PrintWriter;

public class BadConfigFormatException extends Exception {
	public static final String outputFile = "exceptionLogForClue.txt";
	
	public BadConfigFormatException() {
		super();
		writeException();
	}

	public BadConfigFormatException(String string) {
		super(string);
		writeException();
	}
	
	public void writeException() {
		PrintWriter out;
		try {
			out = new PrintWriter(outputFile);
			out.append(this.getMessage());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
}
