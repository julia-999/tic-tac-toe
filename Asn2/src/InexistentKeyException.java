// Julia Anantchenko
// CS2210A 
// Student number: 251097696
// Oct 18th, 2020

// Class for the Inexistent Key exception
public class InexistentKeyException extends RuntimeException {

	// creates the error message
	public InexistentKeyException(String s) {
		super("Inexistent key: " + s);
	}
	
}
