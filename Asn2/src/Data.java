// Julia Anantchenko
// CS2210A 
// Student number: 251097696
// Oct 18th, 2020

// Represents a data object containing a key, score and level
public class Data {
	
	// variables
	private String k;
	private int s, l;
	
	// The constructor, creates the Data object
	public Data(String key, int score, int level) {
		k = key;
		s = score;
		l = level;
	}
	
	// returns the key
	public String getKey() {
		return k;
	}
	
	// returns the score
	public int getScore() {
		return s;
	}
	
	// returns the level
	public int getLevel() {
		return l;
	}

}
