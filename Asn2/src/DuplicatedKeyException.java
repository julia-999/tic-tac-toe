// Julia Anantchenko
// CS2210A 
// Student number: 251097696
// Oct 18th, 2020

// Class for a Duplicated Key exception
public class DuplicatedKeyException extends RuntimeException {

	// creates the error message
	public DuplicatedKeyException (String s) {
		super("The key is duplicated: " + s);
	}
	
}
