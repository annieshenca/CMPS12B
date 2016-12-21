/*
 * Annie Shen (ashen7@ucsc.edu)
 * CMPS 12B/M Lab7
 * Due Dec 2
 * Dictionary.java
 */

public class Dictionary implements DictionaryInterface {
	
    //private inner Node class
    private class Node {
    	String key;
        String value;
        Node left;
        Node right;
        
        Node(String key, String value){
        	this.key = key; 	//set the new key as the class's key
        	this.value = value; //set the new value as the class's value
        	left = right = null;
        }
    }

    //fields for the Dictionary class
    private Node root;		//reference to first Node in List
    private int numItems;   //number of items in the Dictionary

    //constructor for the Dictionary class
    public Dictionary(){
    	root = null;
        numItems = 0;
    }


    //private helper function -----------------------------------------

    //findKey()
    //return a reference to Node containing its argument key,
    //or return null if no such Node exit.
    private Node findKey(Node R, String key){
    	if(R == null || R.key.equals(key)){
    		return R;
    	}
    	
    	if(R.key.compareTo(key) > 0){
    		return findKey(R.left, key);
    	} else{
    		return findKey(R.right, key);
    	} //Exit if-else
    } //Exit findKey
    
    //findParent()
    //Return parent of N in the subtree rooted at R, or return null
    //if N is equal to R. (pre: R != null)
    private Node findParent(Node N, Node R){
    	Node P = null;
    	if(N != R){
    		P = R;
    		while(P.left != N && P.right != N){
    			if(N.key.compareTo(P.key) < 0){
    				P = P.left;
    			} else{
    				P = P.right;
    			} //Exit if-else
    		} //Exit while
    	} //Exit if
    	return P;
    } //Exit findParent
    
    Node findLeftmost(Node R){
    	Node L = R;
    	if(L != null)
    		for( ; L.left != null; L = L.left);
    	return L;
    }
    
    //printInOrder()
    //Prints the (key, value) pairs belonging to the subtree rooted at R in order
    //of increasing keys to file pointed to by out.
    void printInOrder(Node R){
    	if(R != null){
    		printInOrder(R.left);
    		System.out.println(R.key + " " + R.value);
    		printInOrder(R.right);
    	} //Exit if
    } //Exit printInOrder
    
    //deleteAll()
    //Deletes all Nodes in the subtree rooted at N.
    void deleteAll(Node N){
    	if(N != null){
    		deleteAll(N.left);
    		deleteAll(N.right);
    	}
    }
    //ADT operations --------------------------------------------------
    
    //isEmpty()
    //Returns true if the Dictionary contains no pairs, false otherwise
    public boolean isEmpty(){
    	return(numItems == 0);
    }

    //size()
    //Returns the number of (key, value) pairs in the Dictionary
    public int size() {
    	return numItems;
    }
    
    //lookup()
    //Returns the value field when the Dictionary contains a matching pair of key and argument key
    public String lookup(String key){
    	Node N = findKey(root, key);
    	return(N == null ? null : N.value);
    }
    
    //insert()
    //pre: Dictionary does NOT currently contain the argument key
    public void insert(String key, String value) throws DuplicateKeyException{
    	if(findKey(root, key) != null){
    		throw new DuplicateKeyException("Dictionary Error: insert() cannot insert duplicate keys");
    	}
    	
    	Node N = new Node(key, value);
    	Node B = null;
    	Node A = root;
    	while(A != null){
    		B = A;
    		if(key.compareTo(A.key) < 0)	A = A.left;
    		else	A = A.right;
    	} //Exit while
    	
    	if(B == null)	root = N;
    	else if(key.compareTo(B.key) < 0)	B.left = N;
    	else	B.right = N;
    	numItems++;
    }
    
    //delete()
    //Deletes pair with the key key
    //pre: lookup(key) != null
    public void delete(String key) throws KeyNotFoundException{
    	Node N, P, S;
    	if(findKey(root, key) == null){
    		throw new KeyNotFoundException("Dictionary Error: cannot delete non-existing key");
    	}
    	N = findKey(root, key);
    	if(N.left == null && N.right == null){ //Case#1: no children
    		if(N == root)	root = null; //Set root to null
    	    else{
    	    	P = findParent(N, root);
    	    	if(P.right == N)	P.right = null;
    	    	else	P.left = null;
    	    } //Exit if-else
    	}else if(N.right == null){ //Case#2.1: left but no right child
    		if(N == root)	root = N.left; //If deleting root key, set N.left as new root
    		else{
    			P = findParent(N, root);
    			if(P.right == N)	P.right = N.left;
    			else	P.left = N.left;
    		}
    	}else if(N.left == null){ //Case#2.2: right but no left child
    		if(N == root)	root = N.right;
    		else{
    			P = findParent(N, root);
    			if(P.right == N)	P.right = N.right;
    			else	P.left = N.right;
    		}
    	} else{ //Case#3: two children: N.left!=null && N.right != null)
    		S = findLeftmost(N.right);
    		N.key = S.key;
    		N.value = S.value;
    		P = findParent(S, N);
    		
    		if(P.right == S)	P.right = S.right;
    	    else	P.left = S.right;
    	}
    	numItems--;
    }

    //makeEmpty()
    //Resets the Dictionary to the empty state 
    public void makeEmpty(){
    	deleteAll(root);
    	root = null;
    	numItems = 0;
    }

    //toString()
    public String toString(){
    	printInOrder(root);
    	return "";
    }
}
