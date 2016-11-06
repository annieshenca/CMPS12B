//----------------------------------------------------------------------
// Dictionary.java
//----------------------------------------------------------------------

public class Dictionary implements DictionaryInterface {
	
    //private inner Node class
    private class Node {
    	String key;
        String value;
        Node next;
        
        Node(String key, String value){
        	this.key = key; 	//set the new key as the class's key
        	this.value = value; //set the new value as the class's value
        	next = null;
        }
    }

    //fields for the Dictionary class
    private Node head;		//reference to first Node in List
    private int numItems;   //number of items in the Dictionary

    //constructor for the Dictionary class
    public Dictionary(){
    	head = null;
        numItems = 0;
    }


    //private helper function -----------------------------------------

    //findKey()
    //return a reference to Node containing its argument key,
    //or return null if no such Node exit.
    private Node findKey(String key){
    	Node n = head;
    	
        while(n != null){
        	if(n.key != key){
        		n = n.next;
    	    } else{
    	    	//System.out.println("in findKey");
    	    	return n;
    	    }
        }
        return null; //no such Node exist
    }

    
    //ADT operations --------------------------------------------------
    
    //isEmpty()
    //returns true if the Dictionary contains no pairs, false otherwise
    public boolean isEmpty(){
    	return(numItems == 0);
    }

    //size()
    //returns the number of (key, value) pairs in the Dictionary
    public int size() {
    	return numItems;
    }
    
    //lookup()
    //returns the value field when the Dictionary contains a matching pair of key and argument key
    public String lookup(String key){
    	Node n = findKey(key);
	    while(n != null){
	    	if(n.key == key){
	    		//System.out.println("in lookup");
	    		return n.value;
	    	}

		    n = n.next;
	    }
	    //System.out.println("out of lookup if loop, returning null");
	    return null; //return null if can't find it
    }
    
    //insert()
    //precondition: Dictionary does NOT currently contain the argument key
    public void insert(String key, String value) throws DuplicateKeyException{
    	if(lookup(key) == null){ //new unused key
    		if(head == null){ //adding the new (key, value) as the head when the Node is empty
    			head = new Node(key, value);
    			numItems++;
    			
    		}else{ //(head != null)
    			Node n = head;//start at head
    			
    			while(n.next != null){//while loop until n.next is going to be null
    				n = n.next; 
    			}
    			n.next = new Node(key, value);//inserting the (key, value) at the end
    			numItems++;
    		}
    	} else{ //throw exception when a duplicate key is being used
    		throw new DuplicateKeyException("cannot insert duplicate keys");
    	}
    }
    
    //delete()
    //precondition: Dictionary currently contains the argument key
    public void delete(String key) throws KeyNotFoundException{
    	if(lookup(key) == null){ //throw exception when trying to delete a non-existing key
    		throw new KeyNotFoundException("cannot delete non-existent key");
    	} else{ //found the key that is going to be deleted
    		Node n = head;
    		boolean keyFound = false; //exit the loop when key is found
    		while(n != null && !keyFound){ //while the key is NOT found
    			if(numItems == 1 && head.key==key){//when there's only 1 item
    				head = null;
    				numItems--; //decrease numItems to 0
    				keyFound = true;//exit
    			} else if(n.next.key == key){
    				n.next = n.next.next;
    				numItems--;
    				keyFound = true; //exit
    			} else if(n.key == key){
    				head = n.next;
    				keyFound = true;
    				numItems--;
    			} else n = n.next;
    		}
    	}
    }

    //makeEmpty()
    //Resets the Dictionary to the empty state 
    public void makeEmpty(){
    	head = null;
    	numItems = 0;
    }

    //toString()
    public String toString(){
    	Node n = head; //set n as the head of the Node
    	String s = ""; //initialize string s
    	
    	while(n != null){
    		s += n.key + " " + n.value + "\n"; //adding key and value to the string
    		n = n.next;
    	}
    	return s; //returning the current state of the Dictionary
    }
}
