//-----------------------------------------------------------------------------
// Annie Shen (ashen7@ucsc.edu)
// CMPS 12B/M pa4
// Queue.java
// Queue ADT
//-----------------------------------------------------------------------------

public class Queue implements QueueInterface {
	
    // Private inner Node class
    private class Node {
    	Object item;
    	Node next;
      
    	Node(Object item){
    		this.item = item;
    		next = null;
    	}
    }

    // Fields for Queue class
    private Node head;     // Reference to first Node
    private int numItems;  // Number of items in this Queue

    // Queue()
    // Constructor for the Queue class
    public Queue(){
    	head = null;
    	numItems = 0;
    }

    // isEmpty()
    // pre: none
    // post: returns true if this Queue is empty, false otherwise
    public boolean isEmpty(){
    	return(numItems == 0);
    }

    // length()
    // pre: none
    // post: returns the length of this Queue.
    public int length() {
    	return numItems;
    }

    // enqueue(): adds newItem to back of this Queue
    // pre: none
    // post: !isEmpty()
    public void enqueue(Object newItem){
    	Node N = new Node(newItem);
    	Node H = head;
    	
    	if(head == null) { // if the Queue is empty from the beginning
    		head = N;
    	} else { // If not empty
    		while(H.next != null) { //loop until the last Node
    			H = H.next; 
    		} // End while
    		H.next = N; // Add the new Node at end
    	} // End if
    	
    	numItems++; // One more item
    }

    // dequeue(): deletes and returns item from front of this Queue
    // pre: !isEmpty()
    // post: this Queue will have one fewer element
    public Object dequeue() throws QueueEmptyException{
    	if(head == null) { //if Queue is empty
    		throw new QueueEmptyException("Cannot dequeue() an empty Queue");
    	}
    	
    	Node H = head;
    	head = H.next; // Set head as it's next. Abandon the first Node
    	numItems--; // One fewer item
    	
    	return H.item;
    }
   
    // peek()
    // pre: !isEmpty()
    // post: returns item at front of Queue
    public Object peek() throws QueueEmptyException {
    	if(head == null) { //if Queue is empty
    		throw new QueueEmptyException("Cannot peek() an empty Queue");
    	}
    	
    	return head.item;
    }
   
    // dequeueAll(): sets this Queue to the empty state
    // pre: !isEmpty()
    // post: isEmpty()
    public void dequeueAll() throws QueueEmptyException{
    	if(head == null) { //if Queue is already empty
    		throw new QueueEmptyException("Cannot dequeueAll() an empty Queue");
    	}
    	
    	head = null;
    	numItems = 0;
    }
    
    // toString(): overrides Object's toString() method
    public String toString() {
    	String s = "";
	    Node H = head;
	    
//	    for(Node N = head; N!=null; N=N.next) {
//	    	s += N.item + " ";
//	    }
    	while(H != null) {
    		s += H.item + " ";
    		H = H.next;
    	} //End while
    	
	    return s;
    }
    
}