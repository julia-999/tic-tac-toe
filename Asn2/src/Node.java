// Julia Anantchenko
// CS2210A 
// Student number: 251097696
// Oct 18th, 2020

// Creates a Node object to be used in the linked list
public class Node {

	// variables for the next node and the data within
	private Node next;
	private Data data;
	
	// Constructs an empty node
	public Node() {
		next = null;
		data = null;
	}
	
	// Constructs a node with data
	public Node(Data d) {
		next = null; 
		data = d;
	}
	
	// returns the next node in the list
	public Node getNext() {
		return next;
	}
	
	// sets the next node in the list
	public void setNext(Node node) {
		next = node;
	}
	
	// returns the data stored in the node
	public Data getData() {
		return data;
	}
	
	// sets the data of the node
	public void setData(Data d) {
		data = d;
	}
	
}
