package app;

// Custom exception class for handling file I/O and JSON serialization errors.
public class CustomException extends Exception {
	public CustomException(String message) {
		super(message);
	}
}
