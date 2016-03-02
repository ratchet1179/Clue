package clueGame;

public class BadConfigFormatException extends Exception{

	private static String returnMessage = "Config file not formatted correctly";
	private String detail;


	BadConfigFormatException(String detail) {
		super(returnMessage + detail);
		this.detail = detail;
	}
}
