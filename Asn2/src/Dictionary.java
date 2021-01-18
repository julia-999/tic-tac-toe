// Julia Anantchenko
// CS2210A 
// Student number: 251097696
// Oct 18th, 2020

// This class implements a dictionary using a hash table, collisions are solved using separate chaining. 
public class Dictionary implements DictionaryADT {
	
	private Node[] dict; // the array that stores the nodes/linked lists
	private final int X = 39; // the X variable for the polynomial hash function
	private final int SIZE; // the size of the dictionary
	
	// The constructor, creates a dictionary of a specified size
	public Dictionary(int size) {
		SIZE = size;
		dict = new Node[SIZE];
	}

	// Inserts the data object into the dictionary
	@Override
	public int put(Data record) throws DuplicatedKeyException {
		
		int index = h(record.getKey()); // uses the hash function to get the index
		Node head = dict[index]; // the first node in the specified index, if any
		Node newNode = new Node(record); // the node to place in the dictionary
		
		// if the index has nothing stored in it
		if (head == null) {
			dict[index] = newNode;
			return 0; // no collision
		}
		
		// in case of a collision, adds a node to the linked list
		else {
			
			// goes through the linked list checking if the key exists in it already 
			Node current = head;
			while (current != null) {
				if (current.getData().getKey().equals(record.getKey())) {
					throw new DuplicatedKeyException(record.getKey());
				}
				current = current.getNext();
			}
			
			// if no exceptions were thrown
			dict[index] = newNode; // sets the head of the linked list to the new node
			newNode.setNext(head); // sets the old head to be next after the new head
		}
		
		return 1; // collision
		
	}

	// Removes the node with data that contains the specified key from the dictionary
	@Override
	public void remove(String config) throws InexistentKeyException {
		
		int index = h(config); // uses the hash function to get the index
		
		// checks of the key doesn't exist in the dictionary
		if (dict[index] == null)
			throw new InexistentKeyException("idk");
		
		// checks if the first(or only) node in the index has the key to remove 
		if (dict[index].getData().getKey().equals(config)) {
			dict[index] = dict[index].getNext(); // sets the head of the linked list to the next node, removing the original head
			return;
		}
		
		// if the key is not in the first node, checks the rest
		Node current = dict[index].getNext();
		Node previous = dict[index];
		
		while (current != null) { // goes through the linked list
			if (current.getData().getKey().equals(config)) {
				previous.setNext(current.getNext()); // if it is found, makes the previous node point to the next node, removing the current node
			}
			previous = current;
			current = current.getNext();
		}
		
	}

	// Returns the data object stored in the hash table given a key
	@Override
	public Data get(String config) {
		
		int index = h(config); // uses the hash function to get the index
		
		// if there in nothing in that index returns null
		if (dict[index] == null)
			return null;
		
		// goes through the linked list to check for the key
		Node current = dict[index];
	
		while (current != null) {
			if (current.getData().getKey().equals(config)) {
				return current.getData(); // returns the data object if the key is found
			}
			current = current.getNext();
		}
		
		return null; // returns null if there was no data object with that key
	}

	@Override
	public int numDataItems() {
		// NEEED TO DOOOOOOO
		return 0;
	}
	
	// the hash function
	private int h(String key) {
		
		// casts the last char as an int and stores it in value
		int value = (int) key.charAt(key.length()-1);
		
		// goes through the rest of the string, multiplying the value by a constant X, adding the next char and modding by the size of the dictionary
		for (int i = key.length()-2; i >= 0; i--) {
			value = (value * X + (int) key.charAt(i)) % SIZE;
		}
		
		// returns the index of where the key should go
		return value;
	}

}
